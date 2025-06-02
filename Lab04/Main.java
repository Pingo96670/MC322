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

        CamelRobot sandy = new CamelRobot("Sandy", 0, 0, 8, 10, 4, 5);
        FastBot speedy = new FastBot("Speedy", 0, 15, 4, 8, 4);
        ParrotRobot talky = new ParrotRobot("Talky", 15, 0, 10, 4);
        JetBot jetty = new JetBot("Jetty", 15, 15, 15, 10, 4);

        System.out.println("Preparing program...");
        environment.addRobot(sandy);
        environment.addRobot(speedy);
        environment.addRobot(talky);
        environment.addRobot(jetty);
        System.out.println();

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

                        System.out.println("========== Robot exceptions ==========");

                        // Trying to remove inactive robot
                        System.out.println("---------- Unsuccessful removal ----------");
                        System.out.println("Attempting to remove robot \"Jetty\" from environment.");

                        try {
                            environment.removeRobot(jetty);
                        } catch(UnsuccessfulRemovalException e) {
                            System.out.println(e);
                        }

                        System.out.println();

                        // Trying to remove inactive robot
                        System.out.println("---------- Using deactivated robot ----------");
                        System.out.println("Switching robot \"Jetty\" OFF.");
                        jetty.switchState();
                        System.out.println("Attempting to move robot \"Jetty\".");

                        try {
                            jetty.move(0, 1, 0);
                        } catch(RobotUnavailableException e) {
                            System.out.println(e);
                        }

                        System.out.println("Switching robot \"Jetty\" ON.");
                        jetty.switchState();

                        System.out.println();
                        
                        // Obstacle tests
                        System.out.println("========== Obstacle exceptions ==========");

                        // Adds obstacles in valid positions
                        System.out.println("---------- Successful addition of obstacles ----------");
                        System.out.println("Attempting to add obstacles in valid positions.");

                        environment.addObstacle(4, 4, ObstacleType.WATER);
                        environment.addObstacle(3, 13, ObstacleType.ROCK);
                        environment.addObstacle(8, 8, ObstacleType.MOUNTAIN);
                        environment.addObstacle(12, 10, ObstacleType.TREE);

                        System.out.println();

                        // Adds obstacle in an occupied position
                        System.out.println("---------- Overlapping obstacle ----------");
                        System.out.println("Attempting to add obstacle in position (3, y, 3).");

                        try {
                            environment.addObstacle(3, 3, ObstacleType.WATER);
                        } catch(ObjectOverlapException e) {
                            System.out.println(e);
                        }

                        System.out.println();

                        // Trying to add obstacle out of the environment bounds
                        System.out.println("---------- Out of bounds obstacle ----------");
                        System.out.println("Attempting to add obstacle in position (16, y, 16) while in 15x15x15 environment.");

                        try {
                            environment.addObstacle(16, 16, ObstacleType.ROCK);
                        } catch(ObjectOutOfBoundsException e) {
                            System.out.println(e);
                        }

                        System.out.println();

                        // Specific tasks
                        System.out.println("========== Obstacle exceptions ==========");
                        System.out.println("---------- Special tasks ----------");
                        System.out.println("Executing Camel Bot's special task...");

                        sandy.specificTask();

                        System.out.println();

                        System.out.println("---------- Fast Bot ----------");
                        System.out.println("Executing Fast Bot's special task...");

                        speedy.specificTask();

                        System.out.println("---------- Parrot Bot ----------");
                        talky.learnPhrase("Good morning to everyone");
                        talky.learnPhrase("Howdy there partner");
                        talky.learnPhrase("My name is talky");

                        System.out.println("Executing Parrot Bot's special task...");

                        talky.specificTask();

                        System.out.println();

                        System.out.println("---------- Jet Bot ----------");
                        System.out.println("Executing Jet Bot's special task...");

                        jetty.specificTask();

                        System.out.println();

                        // Communication tests
                        System.out.println("========== Communication center tests ==========");
                        System.out.println("---------- Sending messages with Parrot Bot ----------");
                        talky.sendMessage("Was that the bite of 87?!");
                        talky.sendMessage("Poyo!");
                        talky.sendMessage("More More Jump is the BEST!!");
                        talky.sendMessage("Doot doot");

                        System.out.println();

                        System.out.println("---------- Listing messages stored by the communication center ----------");
                        environment.getCommCenter().showMessages();

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
