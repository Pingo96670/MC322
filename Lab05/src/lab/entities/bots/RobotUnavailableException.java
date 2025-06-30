package lab.entities.bots;

// Thrown when attempting to use an action of a robot that is turned off
public class RobotUnavailableException extends RuntimeException {
    public RobotUnavailableException() {}

    public RobotUnavailableException(String message) {
        super(message);
    }

    public RobotUnavailableException(Throwable cause) {
        super (cause);
    }

    public RobotUnavailableException(String message, Throwable cause) {
        super (message, cause);
    }
}
