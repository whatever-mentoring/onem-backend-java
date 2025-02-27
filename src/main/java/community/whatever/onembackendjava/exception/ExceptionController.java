package community.whatever.onembackendjava.exception;

import java.util.HashMap;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import community.whatever.onembackendjava.exception.res.BusinessExceptionResponse;
import community.whatever.onembackendjava.exception.res.ValidationExceptionResponse;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

@Slf4j
@RestControllerAdvice
public class ExceptionController {

	@ExceptionHandler(BusinessLogicException.class)
	public ResponseEntity<BusinessExceptionResponse> handleBusinessLogicException(BusinessLogicException e) {
		BusinessExceptionCode ec = e.getExceptionCode();
		BusinessExceptionResponse res = new BusinessExceptionResponse(ec.getErrorMessage());
		log.error("error =", e);
		return ResponseEntity.status(ec.getStatus()).body(res);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ValidationExceptionResponse> handleMethodArgumentNotValidException(
		MethodArgumentNotValidException e) {
		HashMap<String, String> validations = new HashMap<>();
		for (val fieldError : e.getFieldErrors()) {
			validations.put(fieldError.getField(), fieldError.getDefaultMessage());
		}

		return ResponseEntity.badRequest().body(new ValidationExceptionResponse(validations));
	}
}
