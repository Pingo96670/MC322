package lab.environment;

public class UnsuccessfulRemovalException extends RuntimeException {
    public UnsuccessfulRemovalException() {}

    public UnsuccessfulRemovalException(String message) {
        super(message);
    }

    public UnsuccessfulRemovalException(Throwable cause) {
        super (cause);
    }

    public UnsuccessfulRemovalException(String message, Throwable cause) {
        super (message, cause);
    }
}
