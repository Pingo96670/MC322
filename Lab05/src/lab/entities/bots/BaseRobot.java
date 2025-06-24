package lab.entities.bots;/*
The BaseRobot class is the basic class meant to be inherited by others.
 */

import lab.entities.bots.interfaces.Sensorable;
import lab.entities.Entity;
import lab.entities.EntityType;
import lab.environment.Environment;
import lab.sensors.ObstacleSensor;

public abstract class BaseRobot implements Entity, Sensorable {
    private final String name;    // Robot's name
    private int posX;    // Robot's x coordinate (environment's length)
    private int posY = 0;    // Robot's y coordinate (environment's height)
    private int posZ;    // Robot's z coordinate (environment's width)
    private String type;    // Robot's type
    private String direction = "North";     // Robot's direction
    // North: +z
    // South: -z
    // East: +x
    // West: -x
    private final ObstacleSensor obstacleSensor;
    private static Environment environment;     // The environment the robot is in || Set directly in main
    private static final EntityType entityType = EntityType.ROBOT;
    private RobotState state = RobotState.ON;

    // BaseRobot constructor
    public BaseRobot(String name, int startX, int startZ, double sensorRadius) {
        this.name = name;
        this.type = "Base Bot";
        this.posX = startX;
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
    public void move(int dX, int dZ, boolean doPrintPos) throws RobotUnavailableException {
        // Checks if the robot is ON
        // Throws RobotUnavailableException otherwise
        if (!isOn()) {
            throw new RobotUnavailableException("The robot %s is currently OFF. Please turn it on to proceed.".formatted(getName()));
        }

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
                    int step = Math.min(Math.abs(remainingDZ), (int)obstacleSensor.getSensorRadius());
                    
                    // Search for obstacles
                    if(obstacleSensor.isObstacleAhead(direction, step)) {
                        System.out.printf("Obstacle detected in direction %s. Movement stopped.\n", direction);
                        System.out.printf("The robot \"%s\" is currently in the position (%d, %d, %d).\n", this.getName(), this.getPosX(), this.getPosY(), this.getPosZ());

                        return;
                    }

                    // Updates environment's obstacleMatrix and robot's position
                    BaseRobot.environment.getEntityTypeMatrix()[posX][posY][posZ] = EntityType.EMPTY;
                    posZ += direction.equals("South") ? -step : step;
                    BaseRobot.environment.getEntityTypeMatrix()[posX][posY][posZ] = EntityType.ROBOT;
                    remainingDZ -= direction.equals("South") ? -step : step;
                }

                // Moves in X axis
                if(remainingDX!=0) {
                    direction = remainingDX > 0 ? "East" : "West";
                }
                while(remainingDX!=0) {
                    int step = Math.min(Math.abs(remainingDX), (int)obstacleSensor.getSensorRadius());

                    // Search for obstacles
                    if(obstacleSensor.isObstacleAhead(direction, step)) {
                        System.out.printf("Obstacle detected in direction %s. Movement stopped.\n", direction);
                        System.out.printf("The robot \"%s\" is currently in the position (%d, %d, %d).\n", this.getName(), this.getPosX(), this.getPosY(), this.getPosZ());

                        return;
                    }

                    // Updates environment's obstacleMatrix and robot's position
                    BaseRobot.environment.getEntityTypeMatrix()[posX][posY][posZ] = EntityType.EMPTY;
                    posX += direction.equals("East") ? step : -step;
                    BaseRobot.environment.getEntityTypeMatrix()[posX][posY][posZ] = EntityType.ROBOT;
                    remainingDX -= direction.equals("East") ? step : -step;
                }

            }
            else {
                
                // Moves in X axis first
                if(remainingDX!=0) {
                    direction = remainingDX > 0 ? "East" : "West";
                }
                while(remainingDX!=0) {
                    int step = Math.min(Math.abs(remainingDX), (int)obstacleSensor.getSensorRadius());

                    // Search for obstacles
                    if(obstacleSensor.isObstacleAhead(direction, step)) {
                        System.out.printf("Obstacle detected in direction %s. Movement stopped.\n", direction);
                        System.out.printf("The robot \"%s\" is currently in the position (%d, %d, %d).\n", this.getName(), this.getPosX(), this.getPosY(), this.getPosZ());

                        return;
                    }

                    // Updates environment's obstacleMatrix and robot's position
                    BaseRobot.environment.getEntityTypeMatrix()[posX][posY][posZ] = EntityType.EMPTY;
                    posX += direction.equals("East") ? step : -step;
                    BaseRobot.environment.getEntityTypeMatrix()[posX][posY][posZ] = EntityType.ROBOT;
                    remainingDX -= direction.equals("East") ? step : -step;
                }

                //Moves in Z axis
                if(remainingDZ!=0) {
                    direction = remainingDZ > 0 ? "North" : "South";
                }
                while(remainingDZ!=0) {
                    int step = Math.min(Math.abs(remainingDZ), (int)obstacleSensor.getSensorRadius());
                    
                    // Search for obstacles
                    if(obstacleSensor.isObstacleAhead(direction, step)) {
                        System.out.printf("Obstacle detected in direction %s. Movement stopped.\n", direction);
                        System.out.printf("The robot \"%s\" is currently in the position (%d, %d, %d).\n", this.getName(), this.getPosX(), this.getPosY(), this.getPosZ());

                        return;
                    }

                    // Updates environment's obstacleMatrix and robot's position
                    BaseRobot.environment.getEntityTypeMatrix()[posX][posY][posZ] = EntityType.EMPTY;
                    posZ += direction.equals("South") ? -step : step;
                    BaseRobot.environment.getEntityTypeMatrix()[posX][posY][posZ] = EntityType.ROBOT;
                    remainingDZ -= direction.equals("South") ? -step : step;
                }
            }

            if (doPrintPos) {
                System.out.printf("The robot \"%s\" is currently in the position (%d, %d, %d).\n", this.getName(), this.getPosX(), this.getPosY(), this.getPosZ());
            }
        }
    }

    public void move(int dX, int dZ) {
        move(dX, dZ, true);
    }

    // Robot uses obstacleSensor looking at all directions
    public void lookAllDirections() throws RobotUnavailableException {
        // Checks if the robot is ON
        // Throws RobotUnavailableException otherwise
        if (!isOn()) {
            throw new RobotUnavailableException("The robot %s is currently OFF. Please turn it on to proceed.".formatted(getName()));
        }

        obstacleSensor.monitor();
    }

    // Checks if an obstacle is in position (x, y, z)
    public boolean checkObstacles(int x, int y, int z) throws RobotUnavailableException {
        // Checks if the robot is ON
        // Throws RobotUnavailableException otherwise
        if (!isOn()) {
            throw new RobotUnavailableException("The robot %s is currently OFF. Please turn it on to proceed.".formatted(getName()));
        }

        if (x > BaseRobot.environment.getSizeX() || x < 0
            || y > BaseRobot.environment.getSizeY() || y < 0
            || z > BaseRobot.environment.getSizeZ() || z < 0) {

            return true;
        }

        return BaseRobot.environment.getEntityTypeMatrix()[x][y][z] != EntityType.EMPTY;
    }

    // Method to print a robot's coordinates
    public void printPos() {
        System.out.printf("- The robot \"%s\" (type \"%s\") is currently in the position (%d, %d, %d).\n", this.name, this.type, this.posX, this.posY, this.posZ);
    }

    // Method to print the direction a robot is facing
    public void printDir() {
        System.out.printf("- The robot \"%s\" is currently facing %s.\n", this.name, this.direction);
    }

    public void turnOn() {
        if(state != RobotState.ON) {
            state = RobotState.ON;
            System.out.printf("%s is now ON.%n", name);
        }
        else {
            System.out.printf("%s is already ON.%n", name);
        }
    }

    public void turnOff() {
        if (state != RobotState.OFF) {
            state = RobotState.OFF;
            System.out.printf("%s is now OFF.%n", name);
        }
        else {
            System.out.printf("%s is already OFF.%n", name);
        }
    }

    public void switchState() {
        if (state == RobotState.ON) {
            state = RobotState.OFF;
            System.out.printf("%s is now OFF.%n", name);
        } else {
            state = RobotState.ON;
            System.out.printf("%s is now ON.%n", name);
        }
    }

    public boolean isOn() {
        return state == RobotState.ON;
    }

    // Uses all sensors
    public void activateSensors() throws RobotUnavailableException{
        // Checks if the robot is ON
        // Throws RobotUnavailableException otherwise
        if (!isOn()) {
            throw new RobotUnavailableException("The robot %s is currently OFF. Please turn it on to proceed.".formatted(getName()));
        }

        lookAllDirections();
    }

    public abstract void specificTask();

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

    public String getRobotType() {
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

    @Override
    public EntityType getEntityType() {
        return EntityType.ROBOT;
    }

    public RobotState getState() {
        return state;
    }

    @Override
    public char getRepChar() {
        return entityType.getEntityChar();
    }


}
