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

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(BusinessLogicException.class)
	public BusinessExceptionResponse handleBusinessLogicException(BusinessLogicException e) {
        log.error("error =", e);
		return new BusinessExceptionResponse(e.getExceptionCode().getErrorMessage());
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
