package community.whatever.onembackendjava.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({IllegalArgumentException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Object IllegalArgumentException(IllegalArgumentException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler({ExpiredException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Object ExpiredException(ExpiredException ex) {
        return ex.getMessage();
    }

}