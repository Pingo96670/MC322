public abstract class Sensor {
    private BaseRobot ownerBot;
    private final double sensorRadius;

    public Sensor(double sensorRadius) {
        this.sensorRadius = sensorRadius;
    }

    public abstract void monitor();

    public BaseRobot getOwnerBot() {
        return ownerBot;
    }

    public void setOwnerBot(BaseRobot bot) {
        ownerBot = bot;
    }

    public double getSensorRadius() {
        return sensorRadius;
    }
}
