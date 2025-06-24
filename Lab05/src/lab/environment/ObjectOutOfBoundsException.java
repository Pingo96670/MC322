package lab.environment;

public class ObjectOutOfBoundsException extends IndexOutOfBoundsException {
    public ObjectOutOfBoundsException() {}

    public ObjectOutOfBoundsException(String message) {
        super(message);
    }
}
