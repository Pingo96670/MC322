package lab.sensors;

public class InvalidMinSpeedSensorRadiusException extends IllegalArgumentException {
    public InvalidMinSpeedSensorRadiusException() {}

    public InvalidMinSpeedSensorRadiusException(String message) {
        super(message);
    }

    public InvalidMinSpeedSensorRadiusException(Throwable cause) {
        super (cause);
    }

    public InvalidMinSpeedSensorRadiusException(String message, Throwable cause) {
        super (message, cause);
    }
}
