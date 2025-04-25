/*
The AerialRobot class can also move upwards and downwards.
 */

public class AerialRobot extends BaseRobot {
    private final int maxPosY;

    // AerialRobot constructor
    public AerialRobot(String name, int startX, int startZ, int maxPosY, double distanceRadius) {
        super(name, startX, startZ, distanceRadius);
        this.setType("Aerial Bot");
        this.maxPosY = maxPosY;
    }

    // Adapted printPos method
    // Takes into account the robot's y position instead of the default value of 0
    @Override
    // Method to print a robot's coordinates
    public void printPos() {
        System.out.printf("- The robot \"%s\" (type \"%s\") is currently in the position (%d, %d, %d).\n", this.getName(), this.getType(), this.getPosX(), this.getPosY(), this.getPosZ());
    }

    // Overload of move method
    // Takes into account the fact that the robot can move in the Y axis
    public void move(int dX, int dY, int dZ) {
        // Out of bounds check
        if (!BaseRobot.getEnvironment().isWithinBounds(this.getPosX() + dX, this.getPosY() + dY, this.getPosZ() + dZ)) {
            System.out.println("Position out of bounds. Position unchanged.\n");
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
                    int step = Math.min(Math.abs(remainingDY), (int)this.getObstacleSensor().getDistanceRadius());
                    if(this.getObstacleSensor().isObstacleAhead(getEnvironment(), this, dir, step)) {
                        System.out.printf("Obstacle detected %s. Movement stopped.\n", dir);
                        return;
                    }

                    // Updates environment's obstacleMatrix and robot's position
                    BaseRobot.getEnvironment().getObstacleMatrix()[getPosX()][getPosY()][getPosZ()] = 0;
                    y += dir.equals("Above") ? step : -step;
                    setPosY(y);
                    BaseRobot.getEnvironment().getObstacleMatrix()[getPosX()][getPosY()][getPosZ()] = 1;
                    remainingDY -= dir.equals("Above") ? step : -step;
                }

                // Moves in other axis
                super.move(dX, dZ);
            }
        }
    }

    // Getters
    public int getMaxPosY() {
        return maxPosY;
    }
}