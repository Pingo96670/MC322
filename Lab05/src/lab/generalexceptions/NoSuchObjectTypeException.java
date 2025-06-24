package lab.generalexceptions;

import java.util.NoSuchElementException;

public class NoSuchObjectTypeException extends NoSuchElementException {
    public NoSuchObjectTypeException() {}

    public NoSuchObjectTypeException(String message) {
        super(message);
    }

    public NoSuchObjectTypeException(Throwable cause) {
        super (cause);
    }

    public NoSuchObjectTypeException(String message, Throwable cause) {
        super (message, cause);
    }
}
