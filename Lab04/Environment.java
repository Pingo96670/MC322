import java.util.ArrayList;

public class Environment {
    private final int sizeX;    // Environment's x dimension (width)
    private final int sizeY;    // Environment's y dimension (height)
    private final int sizeZ;    // Environment's z dimension (depth)
    private final ArrayList<BaseRobot> robotList = new ArrayList<>();   // List of robots in the environment
    private final ArrayList<Obstacle> obstacleList = new ArrayList<>(); // List of obstacles in the environment
    private final ArrayList<Entity> entityList = new ArrayList<>(); // List of entities in the environment
    private final EntityType[][][] entityTypeMatrix;     // int boolean multidimensional array of EntityType elements
    private final CommunicationCenter commCenter;   // Communication center

    // Environment constructor
    public Environment(int sizeX, int sizeY, int sizeZ) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.sizeZ = sizeZ;

        this.entityTypeMatrix = new EntityType[sizeX + 1][sizeY + 1][sizeZ + 1];

        for (int i = 0; i <= sizeX; i++) {
            for (int j = 0; j <= sizeX; j++) {
                for (int k = 0; k <= sizeX; k++) {
                    entityTypeMatrix[i][j][k] = EntityType.EMPTY;
                }
            }
        }

        this.commCenter = new CommunicationCenter();
    }

    // Adds a robot to robotList
    public void addRobot(BaseRobot bot) throws ObjectOverlapException {
        if (entityTypeMatrix[bot.getPosX()][bot.getPosY()][bot.getPosZ()] == EntityType.EMPTY && !robotList.contains(bot)) {
            robotList.add(bot);
            entityList.add(bot);
            entityTypeMatrix[bot.getPosX()][bot.getPosY()][bot.getPosZ()] = EntityType.ROBOT;
            System.out.printf("Successfully added robot \"%s\" to environment.\n", bot.getName());
        } else {
            throw new ObjectOverlapException("Operation unsuccessful. Tried to add a robot to an already occupied position or robot is already in environment.");
        }
    }

    // Removes a robot from robotList
    // Not supposed to be called by client
    public void removeRobot(BaseRobot bot) throws UnsuccessfulRemovalException {
        int sizeBefore = robotList.size();

        robotList.remove(bot);
        entityList.remove(bot);

        if (robotList.size() < sizeBefore) {
            entityTypeMatrix[bot.getPosX()][bot.getPosY()][bot.getPosZ()] = EntityType.EMPTY;
            System.out.printf("Successfully removed robot \"%s\" from environment.\n", bot.getName());
        } else {
            throw new UnsuccessfulRemovalException("Failed to remove robot from environment.");
        }
    }

    // Adds an obstacle to obstacleList
    public void addObstacle(int x1, int z1, ObstacleType type) throws ObjectOverlapException, ObjectOutOfBoundsException {

        // Verifies if the obstacle's posX1, posX2, posY1, posY2 and height fit within environment's bounds
        if(isWithinBounds(x1, type.getHeight(), z1) && isWithinBounds(x1+type.getWidth(), type.getHeight(), z1+type.getDepth())) {

            // Verifies if the positions are empty
            if(isFree(x1, x1+type.getWidth(), z1, z1+type.getDepth(), type.getHeight())) {
                Obstacle obstacle = new Obstacle(type, x1, z1);
                obstacleList.add(obstacle);
                entityList.add(obstacle);

                // Marks the obstacle's positions in obstacleMatrix
                for(int x = x1; x <= x1+type.getWidth(); x++) {
                    for(int y = z1; y <= z1+type.getDepth(); y++) {
                        for(int z = 0; z <= type.getHeight(); z++) {
                            entityTypeMatrix[x][z][y] = obstacle.getEntityType();
                        }
                    }
                }

                System.out.printf("Successfully added obstacle of type \"%s\" to environment.\n", obstacle.getType());
            }

            else {
                throw new ObjectOverlapException("Operation unsuccessful. Tried to add an obstacle to an already occupied position.");
            }
        }

        else {
            throw new ObjectOutOfBoundsException("Position of object out of bounds.");
        }
    }

    // Verifies if the obstacleMatrix positions are empty
    public boolean isFree(int x1, int x2, int y1, int y2, int height) {
        for(int x = x1; x <= x2; x++) {
            for(int y = y1; y <= y2; y++) {
                for(int z = 0; z <= height; z++) {
                    if(entityTypeMatrix[x][z][y] != EntityType.EMPTY) {
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

    // Prints a flat view of the environment
    // Can use ANSI colors for ease of reading
    public void printFlatMap(boolean doAnsi) {
        // Default - No ANSI colors
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
                - W: Water (height: 0)
                - R: Rock (height: 1)
                - M: Mountain (height: 5)
                - T: Tree (height: 2)
                """;

        EntityType[][][] tempMap = new EntityType[BaseRobot.getEnvironment().getSizeX()+1][BaseRobot.getEnvironment().getSizeY()+1][BaseRobot.getEnvironment().getSizeZ()+1];

        for (int i = 0; i < tempMap.length; i++)
            for (int j = 0; j < tempMap.length; j++)
                System.arraycopy(entityTypeMatrix[i][j], 0, tempMap[i][j], 0, tempMap.length);

        // Making a copy of obstacleMatrix with aerial robots grounded
        for (BaseRobot bot: robotList) {
            tempMap[bot.getPosX()][0][bot.getPosZ()] = EntityType.ROBOT;
        }

        // Allowing ANSI colors
        if (doAnsi) {
            ANSI_RESET = "\u001B[0m";
            ANSI_RED = "\u001B[31m";
            ANSI_GREEN = "\u001B[32m";
            ANSI_YELLOW = "\u001B[33m";
            ANSI_BLUE = "\u001B[34m";
            ANSI_PURPLE = "\u001B[35m";
        }

        System.out.println(MAP_INFO);

        // Printing out the flat map with for loops
        System.out.println("+Z (North)");

        for (int z = sizeZ; z >= 0; z--) {
            System.out.print("[ ");

            for (int x = 0; x <= sizeX; x++) {
                switch (tempMap[x][0][z]) {
                    case EMPTY:
                        System.out.printf(EntityType.EMPTY.getEntityChar() + " ");
                        break;

                    case ROBOT:
                        System.out.print(ANSI_RED + EntityType.ROBOT.getEntityChar() + " " + ANSI_RESET);
                        break;

                    case WATER:
                        System.out.print(ANSI_BLUE + EntityType.WATER.getEntityChar() + " " + ANSI_RESET);
                        break;

                    case ROCK:
                        System.out.print(ANSI_PURPLE + EntityType.ROCK.getEntityChar() + " " + ANSI_RESET);
                        break;

                    case MOUNTAIN:
                        System.out.print(ANSI_YELLOW + EntityType.MOUNTAIN.getEntityChar() + " " + ANSI_RESET);
                        break;

                    case TREE:
                        System.out.print(ANSI_GREEN + EntityType.TREE.getEntityChar() + " " + ANSI_RESET);
                        break;

                    default:
                        throw new NoSuchObjectTypeException("Unrecognized object ID identified when parsing obstacle map.");
                }
            }

            if (z > 0) {
                System.out.println("]");
            } else {
                System.out.println("] +X (East)");
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

    public EntityType[][][] getEntityTypeMatrix() {
        return entityTypeMatrix;
    }

    public CommunicationCenter getCommCenter() {
        return commCenter;
    }
}