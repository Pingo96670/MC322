public class AerialRobot extends BaseRobot {
    protected int posY;
    protected final int maxPosY;

    public AerialRobot(String name, int startX, int startZ, int maxPosY) {
        super(name, startX, startZ);
        this.type = "Base Aerial";
        this.maxPosY = maxPosY;
        this.posY = 0;
    }

    @Override
    // Method to print a robot's coordinates
    public void printPos() {
        System.out.printf("- The robot \"%s\" (type \"%s\") is currently in the position (%d, %d, %d).\n", this.name, this.type, this.posX, this.posY, this.posZ);
    }
    

    public void moveY(int dY) {
        if ((this.posY + dY) <= this.maxPosY) {
            this.posY += dY;
        }
    }

    public void move(int dX, int dY, int dZ) {
        if ((this.posY + dY) > this.maxPosY) {
            System.out.printf("The robot \"%s\" cannot reach that altitude.\n", this.name);
        } else {
            if (AerialRobot.getEnvironment().isWithinBounds(posX + dX, posY + dY, posZ + dZ)) {
                moveY(dY);
                super.move(dX, dZ);
            } else {
                System.out.println("Position out of bounds.");
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