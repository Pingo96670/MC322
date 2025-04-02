package Lab02;

public class BaseRobot {
    private final String name;    // Robot's name
    private int posX;    // Robot's x coordinate (environment's length)
    private int posZ;    // Robot's y coordinate (environment's width)
    private String type;
    private String direction = "North";
    private static Environment environment;

    // Robot constructor
    public BaseRobot(String name, int startX, int startZ) {
        BaseRobot.environment.addRobot(this);
        BaseRobot.environment.getObstacleMatrix()[startX][0][startZ] = 1;

        this.name = name;
        this.type = "Base Bot";
        this.posX = startX;
        this.posZ = startZ;
    }

    // Method to update a robot's position and direction
    public void move(int dX, int dZ) {
        if (!BaseRobot.environment.isWithinBounds(posX + dX, 0, posZ + dZ)) {
            System.out.println("Position out of bounds. Position unchanged.");
        } else if (checkObstacles(this.posX + dX, 0, this.posZ + dZ)) {
            System.out.println("Selected position is occupied by another robot. Position unchanged.");
        } else {
            BaseRobot.environment.getObstacleMatrix()[posX][0][posZ] = 0;

            this.posX += dX;
            this.posZ += dZ;

            BaseRobot.environment.getObstacleMatrix()[posX][0][posZ] = 1;

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
    }

    public boolean checkObstacles(int x, int y, int z) {
        return BaseRobot.environment.getObstacleMatrix()[x][y][z] == 1;
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
