package community.whatever.onembackendjava.common.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {


    // Expired
    EXPIRED_SHORTEN_URL(HttpStatus.GONE, "만료된 단축 URL입니다."),

    // Validation
    INVALID_URL_FORMAT(HttpStatus.BAD_REQUEST, "잘못된 URL 형식입니다."),
    BLOCKED_URL(HttpStatus.FORBIDDEN, "사용 불가능한 URL입니다."),

    // NotFound
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
