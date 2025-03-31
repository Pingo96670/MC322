/*
The BaseRobot class is the basic class meant to be inherited by others.
 */

public class BaseRobot {
    private final String name;    // Robot's name
    private int posX;    // Robot's x coordinate (environment's length)
    private int posZ;    // Robot's y coordinate (environment's width)
    private String type;    // Robot's type
    private String direction = "North";     // Robot's direction
    // North: +x
    // South: -x
    // East: +z
    // West: -z
    private static Environment environment;     // The environment the robot is in || Set directly in main

    // BaseRobot constructor
    public BaseRobot(String name, int startX, int startZ) {
        BaseRobot.environment.addRobot(this);
        BaseRobot.environment.getObstacleMatrix()[startX][0][startZ] = 1;

        this.name = name;
        this.type = "Base Bot";
        this.posX = startX;
        this.posZ = startZ;
    }

    /*
    Method to update a robot's position and direction
    A robot first moves to the direction it's facing or its opposite, then to the other direction (if not 0)
    Ex: If a robot is facing North, then it would first move North or South before going East or West
    */
    public void move(int dX, int dZ) {
        // Out of bounds check
        if (!BaseRobot.environment.isWithinBounds(posX + dX, 0, posZ + dZ)) {
            System.out.println("Position out of bounds. Position unchanged.");
        // Collision check
        } else if (checkObstacles(this.posX + dX, 0, this.posZ + dZ)) {
            System.out.println("Selected position is occupied by another robot. Position unchanged.");
        // Movement logic
        } else {
            // Updating the environment's obstacle matrix
            BaseRobot.environment.getObstacleMatrix()[posX][0][posZ] = 0;
            this.posX += dX;
            this.posZ += dZ;
            BaseRobot.environment.getObstacleMatrix()[posX][0][posZ] = 1;

            // Facing x direction
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
            // Facing z direction
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

    // Checks if an obstacle is in position (x, y, z)
    public boolean checkObstacles(int x, int y, int z) {
        return BaseRobot.environment.getObstacleMatrix()[x][y][z] == 1;
    }

    // Method to print a robot's coordinates
    // Defaults to y = 0 for general case
    public void printPos() {
        System.out.printf("- The robot \"%s\" (type \"%s\") is currently in the position (%d, 0, %d).\n", this.name, this.type, this.posX, this.posZ);
    }

    // Method to print the direction a robot is facing
    public void printDir() {
        System.out.printf("- The robot \"%s\" is currently facing %s.\n", this.name, this.direction);
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
