package community.whatever.onembackendjava.exception;

public class ExpiredException  extends RuntimeException {
    public ExpiredException() {
    }

    // 메시지를 받을 수 있는 생성자
    public ExpiredException(String message) {
        super(message);
    }
    // 예외의 원인을 포함하는 생성자
    public ExpiredException(String message, Throwable cause) {
        super(message, cause);
    }
    // Throwable을 직접 받는 생성자
    public ExpiredException(Throwable cause) {
        super(cause);
    }
}
