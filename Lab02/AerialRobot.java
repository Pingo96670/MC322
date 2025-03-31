/*
The AerialRobot class can also move upwards and downwards.
 */

public class AerialRobot extends BaseRobot {
    private int posY;
    private final int maxPosY;

    // AerialRobot constructor
    public AerialRobot(String name, int startX, int startZ, int maxPosY) {
        super(name, startX, startZ);
        this.setType("Aerial Bot");
        this.maxPosY = maxPosY;
        this.posY = 0;
    }

    // Adapted printPos method
    // Takes into account the robot's y position instead of the default value of 0
    @Override
    // Method to print a robot's coordinates
    public void printPos() {
        System.out.printf("- The robot \"%s\" (type \"%s\") is currently in the position (%d, %d, %d).\n", this.getName(), this.getType(), this.getPosX(), this.posY, this.getPosZ());
    }

    // Auxiliary method to update the robot's y position
    public void moveY(int dY) {
        if ((this.posY + dY) <= this.maxPosY) {
            this.posY += dY;
        }
    }

    // Overload of move method
    // Takes into account the fact that the
    public void move(int dX, int dY, int dZ) {
        // Out of bounds check
        if (!BaseRobot.getEnvironment().isWithinBounds(this.getPosX() + dX, this.posY + dY, this.getPosZ() + dZ)) {
            System.out.println("Position out of bounds. Position unchanged.");
        // Collision check
        } else if (checkObstacles(this.getPosX() + dX, this.posY + dY, this.getPosZ() + dZ)) {
            System.out.println("Selected position is occupied by another robot. Position unchanged.");
        } else {
            // Check if the robot can reach the endpoint's altitude
            if ((this.posY + dY) > this.maxPosY) {
                System.out.printf("The robot \"%s\" cannot reach that altitude. Position unchanged.\n", this.getName());
            // Movement successful || Update to the environment's obstacle matrix and the robot's position
            } else {
                    BaseRobot.getEnvironment().getObstacleMatrix()[this.getPosX()][this.posY][this.getPosZ()] = 0;
                    moveY(dY);
                    super.move(dX, dZ);
                    BaseRobot.getEnvironment().getObstacleMatrix()[this.getPosX()][this.posY][this.getPosZ()] = 1;
            }
        }
    }

    // Getters and setters
    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getMaxPosY() {
        return maxPosY;
    }
}