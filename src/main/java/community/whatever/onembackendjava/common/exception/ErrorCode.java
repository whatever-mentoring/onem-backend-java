package community.whatever.onembackendjava.common.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {

    NOT_FOUND(HttpStatus.NOT_FOUND, "찾을 수 없음");

    private final HttpStatus httpStatus;
    private final String message;

    ErrorCode(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }


    public HttpStatus getStatus() {
        return httpStatus;
    }

    public String getMessage() {
        return message;
    }
}
