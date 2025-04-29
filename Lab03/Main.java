import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static final String INTRODUCTION = """
        ========== Robot Simulator ==========
        Welcome to update 3 of Robot Simulator!
        For more information on the program and its source code, check out the GitHub page at: https://github.com/Pingo96670/MC322
        """;

    private static final String SELECTION = """
        ========== Execution menu ==========
        1) Automated tests
        2) Interactive menu
        
        0) Exit program
        """;

    public static void main(String[] args) {
        // Initializing variables
        Scanner scanner = new Scanner(System.in);
        int executionInput;

        Environment environment = new Environment(15, 15, 15);
        BaseRobot.setEnvironment(environment);

        CamelRobot sandy = new CamelRobot("Sandy", 0, 0, 4, 10, 4, 5);
        FastBot speedy = new FastBot("Speedy", 0, 15, 4, 8, 4);
        ParrotRobot talky = new ParrotRobot("Talky", 15, 0, 10, 4);
        JetBot jetty = new JetBot("Jetty", 15, 15, 15, 10, 4);

        environment.addRobot(sandy);
        environment.addRobot(speedy);
        environment.addRobot(talky);
        environment.addRobot(jetty);

        for (BaseRobot bot: environment.getRobotList()) {
            bot.joinSensor();
        }

        System.out.println(INTRODUCTION);

        // Menu selection loop
        while (true) {
            System.out.println(SELECTION);
            System.out.print("Please select an execution method: ");

            // Try-catch block to deal with non integer input
            try {
                executionInput = scanner.nextInt();

                switch (executionInput) {
                    // Automated tests
                    case 1:
                        System.out.println("Running automated tests...\n");

                        // Robot List tests
                        System.out.println("========== Robot List tests ==========");
                        environment.printRobotList();
                        System.out.println();

                        // Successful removal
                        System.out.println("---------- Successful removal ----------");
                        System.out.println("Attempting to remove robot \"Jetty\" from environment.");
                        environment.removeRobot(jetty);
                        System.out.println();
                        environment.printRobotList();
                        System.out.println();

                        // Trying to remove inactive robot
                        System.out.println("---------- Unsuccessful removal ----------");
                        System.out.println("Attempting to remove robot \"Jetty\" from environment.");
                        environment.removeRobot(jetty);
                        System.out.println();
                        environment.printRobotList();
                        System.out.println();
                        
                        // Obstacle tests
                        System.out.println("========== Obstacle tests ==========");

                        // Adds obstacles in valid positions
                        System.out.println("---------- Successful addition of obstacles ----------");
                        System.out.println("Attempting to add obstacles in valid positions.");
                        environment.addObstacle(9, 9, ObstacleType.TREE); 
                        environment.addObstacle(8, 8, ObstacleType.ROCK);
                        environment.addObstacle(1, 1, ObstacleType.MOUNTAIN);
                        environment.addObstacle(6, 6, ObstacleType.WATER);
                        System.out.println();
                        environment.printFlatMap(false);
                        System.out.println();

                        // Adds obstacle in an occupied position
                        System.out.println("---------- Overlapping obstacle ----------");
                        System.out.println("Attempting to add obstacle in position (3, y, 3).");
                        environment.addObstacle(3, 3, ObstacleType.WATER);

                        // Trying to add obstacle out of the environment bounds
                        System.out.println("---------- Out of bounds obstacle ----------");
                        System.out.println("Attempting to add obstacle in position (16, y, 16) while in 15x15x15 environment.");
                        environment.addObstacle(16, 16, ObstacleType.ROCK);
                        
                        // Movement tests
                        System.out.println("========== Movement tests ==========");

                        talky.printDir(); talky.printPos();
                        System.out.println();

                        // Moves talky to a valid position
                        System.out.println("---------- XYZ movement ----------");
                        System.out.println("Moving robot \"Talky\" by (-1, 4, 5). Movement in Z (North), then -X (West).");
                        talky.move(-1, 4, 5);
                        talky.printDir();
                        System.out.println();

                        System.out.println("---------- X/Z movement ----------");
                        System.out.println("Moving robot \"Talky\" by (1, 0, 0). Movement in X (East).");
                        talky.move(1, 0, 0);
                        talky.printDir();
                        System.out.println();

                        System.out.println("Moving robot \"Talky\" by (0, 0, 1). Movement in Z (North).");
                        talky.move(0, 0, 1);
                        talky.printDir();
                        System.out.println();

                        System.out.println("---------- Y movement ----------");
                        System.out.println("Moving robot \"Talky\" by (0, 1, 0). No movement in cardinal directions.");
                        talky.move(0, 1, 0);
                        talky.printDir();
                        System.out.println();

                        // Moves talky to invalid positions
                        System.out.println("---------- Invalid movement ----------");

                        System.out.println("Attempting to move robot \"Talky\" by (16, 0, 16).");
                        talky.move(16, 0, 16);

                        System.out.println();

                        System.out.println("Attempting to move robot \"Talky\" by (-16, 0, -16).");
                        talky.move(-16, 0, -16);

                        System.out.println();

                        System.out.println("Attempting to move robot \"Talky\" by (0, 6, 0).");
                        talky.move(0, 6, 0);

                        System.out.println();

                        System.out.println("Attempting to move robot \"Talky\" by (0, -10, 0).");
                        talky.move(0, -10, 0);

                        System.out.println();

                        // Sensor tests
                        System.out.println("========== Sensor tests ==========");
                        System.out.println("Moving \"Talky\" into position for tests.");
                        talky.move(-11, 0, -6);
                        environment.printFlatMap(false);
                        System.out.println();

                        System.out.println("---------- Detecting and trying to move into obstacle (Z) ----------");
                        System.out.println("Using \"Talky\"'s obstacle sensor at the mountain's height (5).");
                        talky.lookAllDirections();
                        System.out.println();

                        System.out.println("Attempting to move \"Talky\" by (0, 0, 1).");
                        talky.move(0, 0, 1);
                        System.out.println();

                        System.out.println("---------- Detection in the Y axis ----------");
                        System.out.println("Moving \"Talky\" by (0, 1, 0).");
                        talky.move(0, 1, 0);
                        System.out.println();

                        System.out.println("Using \"Talky\"'s obstacle sensor above the mountain's height (5), but not above the mountain itself.");
                        talky.lookAllDirections();
                        System.out.println();

                        System.out.println("Moving \"Talky\" by (0, 0, 1).");
                        talky.move(0, 0, 1);
                        System.out.println();

                        System.out.println("Using \"Talky\"'s obstacle sensor above the mountain itself.");
                        talky.lookAllDirections();
                        System.out.println();

                        System.out.println("---------- Trying to move into obstacle (Y) ----------");
                        System.out.println("Attempting to move \"Talky\" by (0, -1, 0).");
                        talky.move(0, -1, 0);
                        System.out.println();

                        System.out.println("---------- Camel Robot - Water sensor (range: 5) ----------");
                        System.out.println("Using \"Sandy\"'s water sensor out of any WATER type obstacle.");
                        sandy.searchWater();
                        System.out.println();

                        System.out.println("Moving \"Sandy\" into range of WATER type obstacle and scanning.");
                        sandy.move(0,4);
                        sandy.move(1,2);
                        sandy.searchWater();
                        System.out.println();

                        System.out.println("---------- Segmented movement (obstacle sensor with range 4) ----------");
                        System.out.println("Moving \"Talky\" into position for testing.");
                        talky.move(-3, 0, 11);
                        talky.move(0, -6, 0);
                        System.out.println();

                        System.out.println("Attempting to move \"Talky\" by (0, 0, 8).");
                        talky.move(0, 0, -8);
                        System.out.println();

                        System.out.println("Step-by-step analysis:");
                        System.out.println("- First scan: no obstacles found South within sensor range");
                        System.out.println("  - Move (0, 0, -4) to position (1, 0, 8)");
                        System.out.println("- Second scan: obstacle found South within sensor range (Sandy (1, 0, 6))");
                        System.out.println("  - Terminate movement");

                        System.out.println();
                        System.out.println("Automated tests finished. Exiting program...");
                        scanner.close();
                        return;

                    // Interactive menu (manual input)
                    case 2:
                        System.out.println("Initiating interactive menu...\n");

                        environment.addObstacle(4, 4, ObstacleType.WATER);
                        environment.addObstacle(3, 13, ObstacleType.ROCK);
                        environment.addObstacle(8, 8, ObstacleType.MOUNTAIN);
                        environment.addObstacle(12, 10, ObstacleType.TREE);

                        System.out.println();

                        InteractiveMenu.mainMenu(scanner, environment);
                        break;

                    // Exit program
                    case 0:
                        System.out.println("Exiting program...");
                        scanner.close();
                        return;

                    // Invalid option
                    default:
                        System.out.println("Invalid option. Please select a valid number.\n");
                        break;

                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please select a valid number.\n");
                scanner.nextLine();
            }
        }
    }
}
