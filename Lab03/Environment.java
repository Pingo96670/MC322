import java.util.ArrayList;

public class Environment {
    private final int sizeX;    // Environment's x dimension (width)
    private final int sizeY;    // Environment's y dimension (height)
    private final int sizeZ;    // Environment's z dimension (depth)
    private final ArrayList<BaseRobot> robotList = new ArrayList<>();   // List of robots in the environment
    private final ArrayList<Obstacle> obstacleList = new ArrayList<>(); // List of obstacles in the environment
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
        if (obstacleMatrix[bot.getPosX()][bot.getPosY()][bot.getPosZ()] == 0) {
            robotList.add(bot);
            obstacleMatrix[bot.getPosX()][bot.getPosY()][bot.getPosZ()] = 1;
        } else {
            throw new RuntimeException("Tried to add a robot to an already occupied position.");
        }
    }

    // Removes a robot from robotList
    // Not called by client
    public void removeRobot(BaseRobot bot) {
        // Removes robot from the obstacleMatrix first
        obstacleMatrix[bot.getPosX()][bot.getPosY()][bot.getPosZ()] = 0;
        // Removes robot from robotList
        robotList.remove(bot);
        obstacleMatrix[bot.getPosX()][bot.getPosY()][bot.getPosZ()] = 0;
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
                System.out.printf("Failed o add. There is another obstacle in this position.\n");
                System.out.println();
            }
        }

        else{
            System.out.printf("Failed to add. Obstacle's dimensions out of environment's bounds.\n");
            System.out.println();
        }
    }

    // Verifies if the obstacleMatrix positions are empty
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

    public void printFlatMap(boolean doAnsi) {
        String ANSI_RESET = "";
        String ANSI_RED = "";
        String ANSI_GREEN = "";
        String ANSI_YELLOW = "";
        String ANSI_BLUE = "";
        String ANSI_PURPLE = "";

        String MAP_INFO = """
                Flat map legend:
                - 0: Empty
                - B: Bot (height: 1)
                 - Aerial bots are omitted when above something else
                - W: Water (height: 0)
                - R: Rock (height: 1)
                - M: Mountain (height: 5)
                - T: Tree (height: 2)
                """;

        int[][][] tempMap = obstacleMatrix.clone();

        // Making a copy of obstacleMatrix with aerial robots grounded
        for (BaseRobot bot: robotList) {
            if (tempMap[bot.getPosX()][0][bot.getPosZ()] == 0) {
                tempMap[bot.getPosX()][0][bot.getPosZ()] = 1;
            }
        }

        if (doAnsi) {
            ANSI_RESET = "\u001B[0m";
            ANSI_RED = "\u001B[31m";
            ANSI_GREEN = "\u001B[32m";
            ANSI_YELLOW = "\u001B[33m";
            ANSI_BLUE = "\u001B[34m";
            ANSI_PURPLE = "\u001B[35m";
        }

        System.out.println(MAP_INFO);

        System.out.println("-Z");

        for (int z = 0; z <= sizeZ; z++) {
            System.out.print("[ ");

            for (int x = 0; x <= sizeX; x++) {
                switch (tempMap[x][0][z]) {
                    case 0:
                        System.out.print("0 ");
                        break;
                    case 1:
                        System.out.print(ANSI_RED + "B " + ANSI_RESET);
                        break;
                    case 2:
                        System.out.print(ANSI_BLUE + "W " + ANSI_RESET);
                        break;
                    case 3:
                        System.out.print(ANSI_PURPLE + "R " + ANSI_RESET);
                        break;
                    case 4:
                        System.out.print(ANSI_YELLOW + "M " + ANSI_RESET);
                        break;
                    case 5:
                        System.out.print(ANSI_GREEN + "T " + ANSI_RESET);
                        break;
                    default:
                        throw new RuntimeException("Unrecognized object id identified when parsing obstacle map.");
                }

            }

            if (z < sizeX) {
                System.out.println("]");
            } else {
                System.out.println("] +X");
            }

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