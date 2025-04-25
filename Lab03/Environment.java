import java.util.ArrayList;

public class Environment {
    private final int sizeX;    // Environment's x dimension (width)
    private final int sizeY;    // Environment's y dimension (height)
    private final int sizeZ;    // Environment's z dimension (depth)
    private final ArrayList<BaseRobot> robotList = new ArrayList<>();   // List of robots in the environment
    private final ArrayList<Obstacle> obstacleList = new ArrayList<>(); // List of ostacles in the environment
    private final int[][][] obstacleMatrix;     // int boolean multidimensional array of obstacles (0 = empty, 1 = robot, 2 and up = obstacle)

    // Environment constructor
    public Environment(int sizeX, int sizeY, int sizeZ) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.sizeZ = sizeZ;

        this.obstacleMatrix = new int[sizeX + 1][sizeY + 1][sizeZ + 1];
    }

    // Adds a robot to robotList
    public void addRobot(BaseRobot bot) {
        robotList.add(bot);
    }

    // Removes a robot from robotList
    public void removeRobot(BaseRobot bot) {
        robotList.remove(bot);
    }

    // Adds an obstacle to obstacleList
    public void addObstacle(int x1, int y1, ObstacleType type) {

        // Verifies if the obstacle's posX1, posX2, posY1, posY2 and height fit within environment's bounds
        if(isWithinBounds(x1, type.getHeight(), y1) && isWithinBounds(x1+type.getWidth(), type.getHeight(), y1+type.getDepth())) {

            // Verifies if the positions are empty
            if(isFree(x1, x1+type.getWidth(), y1, y1+type.getDepth(), type.getHeight())) {
                Obstacle obstacle = new Obstacle(type, x1, y1);
                obstacleList.add(obstacle);

                // Marks the obstacle's positions in obstacleMatrix
                for(int x = x1; x <= x1+type.getWidth(); x++) {
                    for(int y = y1; y <= y1+type.getDepth(); y++) {
                        for(int z = 0; z <= type.getHeight(); z++) {
                            obstacleMatrix[x][z][y] = type.getCode();
                        }
                    }
                }
            }

            else{
                System.out.printf("There is another obstacle in this position.\n");
            }
        }

        else{
            System.out.printf("Obstacle's dimensions out of environment's bounds.\n");
        }
    }

    // Verifies if the obstacleMatrix positions are empty berfore placing an obstacle
    public boolean isFree(int x1, int x2, int y1, int y2, int height) {
        for(int x = x1; x <= x2; x++) {
            for(int y = y1; y <= y2; y++) {
                for(int z = 0; z <= height; z++) {
                    if(obstacleMatrix[x][z][y] != 0) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    // Prints all robots in robotList, with their names, types and positions
    public void printRobotList() {
        System.out.println("List of robots:");

        for (int i = 0; i < this.robotList.size(); i++) {
            robotList.get(i).printPos();
        }
    }

    // Checks if a position is within environment bounds ([0, size])
    public boolean isWithinBounds(int posX, int posY, int posZ) {
        return posX <= sizeX && posY <= sizeY && posZ <= sizeZ &&
                posX >=0 && posY >= 0 && posZ >=0;
    }

    // Getters
    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public int getSizeZ() {
        return sizeZ;
    }

    public ArrayList<BaseRobot> getRobotList() {
        return robotList;
    }

    public ArrayList<Obstacle> getObstacleList() {
        return obstacleList;
    }

    public int[][][] getObstacleMatrix() {
        return obstacleMatrix;
    }
}