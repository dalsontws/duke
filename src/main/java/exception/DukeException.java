package exception;

// Custom exception class for Duke
public class DukeException extends Exception {
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}