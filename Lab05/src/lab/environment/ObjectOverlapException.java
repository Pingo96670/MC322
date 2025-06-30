package lab.environment;

// Thrown when attempting to add an object to an already occupied position
public class ObjectOverlapException extends RuntimeException {
    public ObjectOverlapException() {}

    public ObjectOverlapException(String message) {
        super(message);
    }

    public ObjectOverlapException(Throwable cause) {
        super (cause);
    }

    public ObjectOverlapException(String message, Throwable cause) {
        super (message, cause);
    }
}
