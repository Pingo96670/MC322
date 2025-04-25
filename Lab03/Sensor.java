public abstract class Sensor {
    private final double distanceRadius;

    public Sensor(double distanceRadius) {
        this.distanceRadius = distanceRadius;
    }

    public abstract void monitor(Environment environment, BaseRobot robot);

    public double getDistanceRadius() {
        return distanceRadius;
    }
}
