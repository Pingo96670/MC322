public class BaseRobot {
    protected final String name;    // Robot's name
    protected int posX;    // Robot's x coordinate (environment's length)
    protected int posZ;    // Robot's y coordinate (environment's width)
    protected String type;
    protected String direction = "North";
    private static Environment environment;

    // Robot constructor
    public BaseRobot(String name, int startX, int startZ) {
        this.name = name;
        this.type = "Base Robot";
        this.posX = startX;
        this.posZ = startZ;
    }

    // Method to update a robot's position and direction
    public void move(int dX, int dZ) {
        this.posX += dX;
        this.posZ += dZ;

        if ((this.direction.equals("North") || this.direction.equals("South"))) {
            if (dZ != 0) {
                if (dZ > 0) {
                    this.setDirection("East");
                } else {
                    this.setDirection("West");
                }
            } else {
                if (dX > 0) {
                    this.setDirection("North");
                } else {
                    this.setDirection("South");
                }
            }
        } else {
            if (dX != 0) {
                if (dX > 0) {
                    this.setDirection("North");
                } else {
                    this.setDirection("South");
                }
            } else {
                if (dZ > 0) {
                    this.setDirection("East");
                } else {
                    this.setDirection("West");
                }
            }
        }
    }

    public void checkObstacles() {

    }

    // Method to print a robot's coordinates
    public void printPos() {
        System.out.printf("- The robot \"%s\" (type \"%s\") is currently in the position (%d, 0, %d).\n", this.name, this.type, this.posX, this.posZ);
    }

    
    // Getters and setters
    public String getName() {
        return this.name;
    }

    public int getPosX() {
        return this.posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosZ() {
        return this.posZ;
    }

    public void setPosZ(int posZ) {
        this.posZ = posZ;
    }

    public String getType() {
        return type;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public static Environment getEnvironment() {
        return environment;
    }

    public static void setEnvironment(Environment environment) {
        BaseRobot.environment = environment;
    }

    public void setType(String type){
        this.type = type;
    }
}
