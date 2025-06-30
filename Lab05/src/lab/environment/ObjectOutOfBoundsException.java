package lab.environment;

// Thrown when attempting to add an object out of environment boundaries
public class ObjectOutOfBoundsException extends IndexOutOfBoundsException {
    public ObjectOutOfBoundsException() {}

    public ObjectOutOfBoundsException(String message) {
        super(message);
    }
}
