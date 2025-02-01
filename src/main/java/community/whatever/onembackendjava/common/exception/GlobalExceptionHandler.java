package community.whatever.onembackendjava.common.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    protected ErrorResponse handleBusinessException(BusinessException ex) {
        return ErrorResponse.of(ex.getErrorCode());
    }

}
