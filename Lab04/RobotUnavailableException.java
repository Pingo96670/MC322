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
