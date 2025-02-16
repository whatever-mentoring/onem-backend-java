package community.whatever.onembackendjava.exception;

import community.whatever.onembackendjava.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({IllegalArgumentException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseDto illegalArgumentException(IllegalArgumentException ex) {
        return new ErrorResponseDto(ex.getMessage());
    }

    @ExceptionHandler({ExpiredException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseDto expiredException(ExpiredException ex) {
        return new ErrorResponseDto(ex.getMessage());
    }

}