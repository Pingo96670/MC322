public abstract class Sensor {
    private BaseRobot ownerBot;
    private final double distanceRadius;

    public Sensor(double distanceRadius) {
        this.distanceRadius = distanceRadius;
    }

    public abstract void monitor();

    public BaseRobot getOwnerBot() {
        return ownerBot;
    }

    public void setOwnerBot(BaseRobot bot) {
        ownerBot = bot;
    }

    public double getDistanceRadius() {
        return distanceRadius;
    }
}
