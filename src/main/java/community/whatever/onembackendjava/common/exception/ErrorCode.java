package community.whatever.onembackendjava.common.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {

    NOT_FOUND_EXCEPTION("E001", HttpStatus.NOT_FOUND, "찾을 수 없음");

    private final String code;
    private final HttpStatus status;
    private final String message;

    ErrorCode(String code, HttpStatus status, String message) {
        this.code = code;
        this.status = status;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
