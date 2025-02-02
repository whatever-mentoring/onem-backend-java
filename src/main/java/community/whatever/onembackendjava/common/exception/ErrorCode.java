package community.whatever.onembackendjava.common.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {

    NOT_FOUND_SHORTEN_URL(HttpStatus.NOT_FOUND, "단축 URL을 찾을 수 없습니다.");

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
