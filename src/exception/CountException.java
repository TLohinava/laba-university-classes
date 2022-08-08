package exception;

public class CountException extends RuntimeException {

    public CountException(String message) {
        super(message);
    }

    public CountException(String message, Throwable cause) {
        super(message, cause);
    }

}
