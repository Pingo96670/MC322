package Lab02;

public class AerialRobot extends BaseRobot {
    private int posY;
    private final int maxPosY;

    public AerialRobot(String name, int startX, int startZ, int maxPosY) {
        super(name, startX, startZ);
        this.setType("Aerial Bot");
        this.maxPosY = maxPosY;
        this.posY = 0;
    }

    @Override
    // Method to print a robot's coordinates
    public void printPos() {
        System.out.printf("- The robot \"%s\" (type \"%s\") is currently in the position (%d, %d, %d).\n", this.getName(), this.getType(), this.getPosX(), this.posY, this.getPosZ());
    }

    public void moveY(int dY) {
        if ((this.posY + dY) <= this.maxPosY) {
            this.posY += dY;
        }
    }

    public void move(int dX, int dY, int dZ) {
        if (!BaseRobot.getEnvironment().isWithinBounds(this.getPosX() + dX, this.posY + dY, this.getPosZ() + dZ)) {
            System.out.println("Position out of bounds. Position unchanged.");
        } else if (checkObstacles(this.getPosX() + dX, this.posY + dY, this.getPosZ() + dZ)) {
            System.out.println("Selected position is occupied by another robot. Position unchanged.");
        } else {
            if ((this.posY + dY) > this.maxPosY) {
                System.out.printf("The robot \"%s\" cannot reach that altitude. Position unchanged.\n", this.getName());
            } else {
                if (AerialRobot.getEnvironment().isWithinBounds(this.getPosX() + dX, posY + dY, this.getPosZ() + dZ)) {
                    BaseRobot.getEnvironment().getObstacleMatrix()[this.getPosX()][this.posY][this.getPosZ()] = 0;
                    moveY(dY);
                    super.move(dX, dZ);
                    BaseRobot.getEnvironment().getObstacleMatrix()[this.getPosX()][this.posY][this.getPosZ()] = 1;
                } else {
                    System.out.println("Position out of bounds.");
                }
            }
        }
    }

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