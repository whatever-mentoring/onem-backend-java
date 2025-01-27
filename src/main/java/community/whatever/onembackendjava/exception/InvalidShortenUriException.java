package community.whatever.onembackendjava.exception;

public class InvalidShortenUriException extends RuntimeException {

    public InvalidShortenUriException(String message) {
        super(message);
    }

    public InvalidShortenUriException(String message, Throwable cause) {
        super(message, cause);
    }
}
