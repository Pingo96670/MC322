/*
The BaseRobot class is the basic class meant to be inherited by others.
 */

public class BaseRobot {
    private final String name;    // Robot's name
    private int posX;    // Robot's x coordinate (environment's length)
    private int posY;    // Robot's y coordinate (environment's height)
    private int posZ;    // Robot's z coordinate (environment's width)
    private String type;    // Robot's type
    private String direction = "North";     // Robot's direction
    // North: +z
    // South: -z
    // East: +x
    // West: -x
    private final ObstacleSensor obstacleSensor;
    private static Environment environment;     // The environment the robot is in || Set directly in main

    // BaseRobot constructor
    public BaseRobot(String name, int startX, int startZ, double sensorRadius) {
        this.name = name;
        this.type = "Base Bot";
        this.posX = startX;
        this.posY = 0;
        this.posZ = startZ;
        this.obstacleSensor = new ObstacleSensor(sensorRadius);
    }

    public void joinSensor() {
        this.obstacleSensor.setOwnerBot(this);
    }

    /*
    Method to update a robot's position and direction
    A robot first moves to the direction it's facing or its opposite, then to the other direction (if not 0)
    Ex: If a robot is facing North, then it would first move North or South before going East or West
    */
    public void move(int dX, int dZ) {
        // Out of bounds check
        if (!BaseRobot.environment.isWithinBounds(posX + dX, posY, posZ + dZ)) {
            System.out.println("Target position out of bounds. Position unchanged.");
        }
        // Movement logic
        else {
            int remainingDX = dX;
            int remainingDZ = dZ;

            if(this.direction.equals("North") || this.direction.equals("South")) {
                
                // Moves in Z axis first
                if(remainingDZ!=0) {
                    direction = remainingDZ > 0 ? "North" : "South";
                }
                while(remainingDZ!=0) {
                    int step = Math.min(Math.abs(remainingDZ), (int)obstacleSensor.getDistanceRadius());
                    
                    // Search for obstacles
                    if(obstacleSensor.isObstacleAhead(environment, this, direction, step)) {
                        System.out.printf("Obstacle detected in direction %s. Movement stopped.\n", direction);
                        System.out.printf("The robot \"%s\" is currently in the position (%d, %d, %d).\n", this.getName(), this.getPosX(), this.getPosY(), this.getPosZ());
                        System.out.println();
                        return;
                    }

                    // Updates environment's obstacleMatrix and robot's position
                    BaseRobot.environment.getObstacleMatrix()[posX][posY][posZ] = 0;
                    posZ += direction.equals("South") ? -step : step;
                    BaseRobot.environment.getObstacleMatrix()[posX][posY][posZ] = 1;
                    remainingDZ -= direction.equals("South") ? -step : step;
                }

                // Moves in X axis
                if(remainingDX!=0) {
                    direction = remainingDX > 0 ? "East" : "West";
                }
                while(remainingDX!=0) {
                    int step = Math.min(Math.abs(remainingDX), (int)obstacleSensor.getDistanceRadius());

                    // Search for obstacles
                    if(obstacleSensor.isObstacleAhead(environment, this, direction, step)) {
                        System.out.printf("Obstacle detected in direction %s. Movement stopped.\n", direction);
                        System.out.printf("The robot \"%s\" is currently in the position (%d, %d, %d).\n", this.getName(), this.getPosX(), this.getPosY(), this.getPosZ());
                        System.out.println();
                        return;
                    }

                    // Updates environment's obstacleMatrix and robot's position
                    BaseRobot.environment.getObstacleMatrix()[posX][posY][posZ] = 0;
                    posX += direction.equals("East") ? step : -step;
                    BaseRobot.environment.getObstacleMatrix()[posX][posY][posZ] = 1;
                    remainingDX -= direction.equals("East") ? step : -step;
                }

            }
            else {
                
                // Moves in X axis first
                if(remainingDX!=0) {
                    direction = remainingDX > 0 ? "East" : "West";
                }
                while(remainingDX!=0) {
                    int step = Math.min(Math.abs(remainingDX), (int)obstacleSensor.getDistanceRadius());

                    // Search for obstacles
                    if(obstacleSensor.isObstacleAhead(environment, this, direction, step)) {
                        System.out.printf("Obstacle detected in direction %s. Movement stopped.\n", direction);
                        System.out.printf("The robot \"%s\" is currently in the position (%d, %d, %d).\n", this.getName(), this.getPosX(), this.getPosY(), this.getPosZ());
                        System.out.println();
                        return;
                    }

                    // Updates environment's obstacleMatrix and robot's position
                    BaseRobot.environment.getObstacleMatrix()[posX][posY][posZ] = 0;
                    posX += direction.equals("East") ? step : -step;
                    BaseRobot.environment.getObstacleMatrix()[posX][posY][posZ] = 1;
                    remainingDX -= direction.equals("East") ? step : -step;
                }

                //Moves in Z axis
                if(remainingDZ!=0) {
                    direction = remainingDZ > 0 ? "North" : "South";
                }
                while(remainingDZ!=0) {
                    int step = Math.min(Math.abs(remainingDZ), (int)obstacleSensor.getDistanceRadius());
                    
                    // Search for obstacles
                    if(obstacleSensor.isObstacleAhead(environment, this, direction, step)) {
                        System.out.printf("Obstacle detected in direction %s. Movement stopped.\n", direction);
                        System.out.printf("The robot \"%s\" is currently in the position (%d, %d, %d).\n", this.getName(), this.getPosX(), this.getPosY(), this.getPosZ());
                        System.out.println();
                        return;
                    }

                    // Updates environment's obstacleMatrix and robot's position
                    BaseRobot.environment.getObstacleMatrix()[posX][posY][posZ] = 0;
                    posZ += direction.equals("South") ? -step : step;
                    BaseRobot.environment.getObstacleMatrix()[posX][posY][posZ] = 1;
                    remainingDZ -= direction.equals("South") ? -step : step;
                }
            }

            System.out.printf("The robot \"%s\" is currently in the position (%d, %d, %d).", this.getName(), this.getPosX(), this.getPosY(), this.getPosZ());
            System.out.println();
        }

    }

    // Robot uses obstacleSensor looking at all directions
    public void lookAllDirections() {
        obstacleSensor.monitor();
    }

    // Checks if an obstacle is in position (x, y, z)
    public boolean checkObstacles(int x, int y, int z) {
        return BaseRobot.environment.getObstacleMatrix()[x][y][z] != 0;
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

    public int getPosY() {
        return this.posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
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

    public void setType(String type){
        this.type = type;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public ObstacleSensor getObstacleSensor() {
        return obstacleSensor;
    }

    public static Environment getEnvironment() {
        return environment;
    }

    public static void setEnvironment(Environment environment) {
        BaseRobot.environment = environment;
    }

}
