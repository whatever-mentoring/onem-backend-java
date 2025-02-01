package community.whatever.onembackendjava.common.exception;

public record ErrorResponse(
    int code,
    String message
) {
    public static ErrorResponse of(ErrorCode errorCode) {
        return new ErrorResponse(
            errorCode.getStatus().value(),
            errorCode.getMessage()
        );
    }
}
