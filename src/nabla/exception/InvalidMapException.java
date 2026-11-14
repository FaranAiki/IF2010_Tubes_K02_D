package nabla.exception;

/**
 * Dilemparkan ketika map tidak memenuhi validasi F00.3.
 */
public class InvalidMapException extends RuntimeException {
    public InvalidMapException(String message) {
        super(message);
    }
}