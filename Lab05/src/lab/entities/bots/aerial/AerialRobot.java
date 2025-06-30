package lab.entities.bots.aerial;/*
The AerialRobot class can also move upwards and downwards.
 */

import lab.entities.EntityType;
import lab.entities.bots.RobotUnavailableException;
import lab.entities.bots.BaseRobot;

public abstract class AerialRobot extends BaseRobot {
    private final int maxPosY;

    // AerialRobot constructor
    public AerialRobot(String name, int startX, int startZ, int maxPosY, double sensorRadius) {
        super(name, startX, startZ, sensorRadius);
        this.setType("Aerial Bot");
        this.maxPosY = maxPosY;
    }

    // Overload of move method
    // Takes into account the fact that the robot can move in the Y axis
    public void move(int dX, int dY, int dZ, boolean doPrintPos) throws RobotUnavailableException {
        // Checks if the robot is ON
        // Throws RobotUnavailableException otherwise
        if (!isOn()) {
            throw new RobotUnavailableException("The robot %s is currently OFF. Please turn it on to proceed.".formatted(getName()));
        }

        // Out of bounds check
        if (!BaseRobot.getEnvironment().isWithinBounds(this.getPosX() + dX, this.getPosY() + dY, this.getPosZ() + dZ)) {
            System.out.println("Target position out of bounds. Position unchanged.");
        } else {
            // Check if the robot can reach the endpoint's altitude
            if ((this.getPosY() + dY) > this.maxPosY) {
                System.out.printf("The robot \"%s\" cannot reach that altitude. Position unchanged.\n", this.getName());
            }
            else {
                // Moves in Y axis first
                int y = getPosY();
                int remainingDY = dY;
                String dir; // Pseudo direction (above or below) to move in Y axis

                dir = remainingDY > 0 ? "Above" : "Below";
                while(remainingDY != 0) {
                    int step = Math.min(Math.abs(remainingDY), (int)this.getObstacleSensor().getSensorRadius());
                    if(this.getObstacleSensor().isObstacleAhead(dir, step)) {
                        System.out.printf("Obstacle detected %s. Movement stopped.\n", dir.toLowerCase());
                        System.out.printf("The robot \"%s\" is currently in the position (%d, %d, %d).\n", this.getName(), this.getPosX(), this.getPosY(), this.getPosZ());
                        return;
                    }

                    // Updates environment's obstacleMatrix and robot's position
                    BaseRobot.getEnvironment().getEntityTypeMatrix()[getPosX()][getPosY()][getPosZ()] = EntityType.EMPTY;
                    y += dir.equals("Above") ? step : -step;
                    setPosY(y);
                    BaseRobot.getEnvironment().getEntityTypeMatrix()[getPosX()][getPosY()][getPosZ()] = EntityType.ROBOT;
                    remainingDY -= dir.equals("Above") ? step : -step;
                }

                // Moves in other axis
                if(dX!=0 || dZ!=0){ 
                    super.move(dX, dZ);
                } else {
                    if (doPrintPos) {
                        System.out.printf("The robot \"%s\" is currently in the position (%d, %d, %d).\n", this.getName(), this.getPosX(), this.getPosY(), this.getPosZ());
                    }
                }
            }
        }
    }

    public void move(int dX, int dY, int dZ) {
        move(dX, dY, dZ, true);
    }

    // Getters
    public int getMaxPosY() {
        return maxPosY;
    }
}