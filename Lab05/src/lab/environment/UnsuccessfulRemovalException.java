package lab.environment;

// Thrown on failure to remove an object from the environment
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
