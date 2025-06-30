package lab.entities.bots.ground;
/*
The FastBot class is a subclass of GroundBot can only move if the total distance is:
 - Greater than its minimum speed AND;
 - Lesser than its maximum speed (inherited movement property of GroundRobot).
 */


import lab.sensors.InvalidMinSpeedSensorRadiusException;
import lab.entities.bots.RobotUnavailableException;

public class FastBot extends GroundRobot {
    private final int minSpeed;

    // FastRobot constructor
    public FastBot(String name, int posX, int posZ, int minSpeed, int maxSpeed, double sensorRadius) throws InvalidMinSpeedSensorRadiusException{
        super(name, posX, posZ, maxSpeed, sensorRadius);

        if (minSpeed > sensorRadius) {
            throw new InvalidMinSpeedSensorRadiusException("Minimum speed can't be greater than the sensor radius.");
        }

        this.setType("Fast Bot");
        this.minSpeed = minSpeed;
    }

    // Adapted move method
    // Moves the robot if minSpeed <= totalDist <= maxSpeed
    @Override
    public void move(int dX, int dZ) throws RobotUnavailableException {
        // Checks if the robot is ON
        // Throws RobotUnavailableException otherwise
        if (!isOn()) {
            throw new RobotUnavailableException("The robot %s is currently OFF. Please turn it on to proceed.".formatted(getName()));
        }

        if (Math.abs(dX) + Math.abs(dZ) >= this.minSpeed) {
            super.move(dX, dZ);
        } else {
            System.out.println("Movement speed is too low for this robot. Position unchanged.");
        }
    }

    // Performs FastBot's specific task
    // Checks how many units, at least, FastBot can move in each orthogonal direction
    public void specificTask() throws RobotUnavailableException {
        // Checks if the robot is ON
        // Throws RobotUnavailableException otherwise
        if (!isOn()) {
            throw new RobotUnavailableException("The robot %s is currently OFF. Please turn it on to proceed.".formatted(getName()));
        }

        int maxN = 0;
        int maxS = 0;
        int maxE = 0;
        int maxW = 0;

        while (!getObstacleSensor().isObstacleAhead("North", getObstacleSensor().getSensorRadius())) {
            move(0, minSpeed, false);
            maxN += minSpeed;
        }

        for (int i = maxN/minSpeed; i > 0; i--) {
            move(0, -minSpeed, false);
        }

        while (!getObstacleSensor().isObstacleAhead("South", getObstacleSensor().getSensorRadius())) {
            move(0, -minSpeed, false);
            maxS += minSpeed;
        }

        for (int i = maxS/minSpeed; i > 0; i--) {
            move(0, minSpeed, false);
        }

        while (!getObstacleSensor().isObstacleAhead("East", getObstacleSensor().getSensorRadius())) {
            move(minSpeed, 0, false);
            maxE += minSpeed;
        }

        for (int i = maxE/minSpeed; i > 0; i--) {
            move(-minSpeed, 0, false);
        }

        while (!getObstacleSensor().isObstacleAhead("West", getObstacleSensor().getSensorRadius())) {
            move(-minSpeed, 0, false);
            maxN += minSpeed;
        }

        for (int i = maxW/minSpeed; i > 0; i--) {
            move(minSpeed, 0, false);
        }

        System.out.printf("""
                The robot %s can move at least:
                - %d unit(s) North
                - %d unit(s) South
                - %d unit(s) East
                - %d unit(s) West
                """
        , getName(), maxN, maxS, maxE, maxW);

        System.out.println();

    }

    @Override
    public String getDescription() {
        return """
                The FastBot class is a subclass of GroundBot can only move if Manhattan distance moved is:
                 - Greater than its minimum speed AND;
                 - Lesser than its maximum speed (inherited movement property of GroundRobot).
                """;
    }

    // Getters
    public int getMinSpeed() {
        return minSpeed;
    }
}