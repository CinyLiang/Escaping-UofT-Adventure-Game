package error;

/**
 * Represents an error caused by an invalid player input.
 */
public class InvalidInputException extends RuntimeException {
    public InvalidInputException(String message) {
        super(message);
    }
}