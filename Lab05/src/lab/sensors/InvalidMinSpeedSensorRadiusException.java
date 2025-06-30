package lab.sensors;

// Throw when attempting to instance a Fast Bot robot type with minSpeed > sensorRadius
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
