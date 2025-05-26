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
        3) Info
        
        0) Exit program
        """;

    public static void main(String[] args) {
        // Initializing variables
        Scanner scanner = new Scanner(System.in);
        int executionInput;

        Environment environment = new Environment(15, 15, 15);
        BaseRobot.setEnvironment(environment);

        CamelRobot sandy = new CamelRobot("Sandy", 0, 0, 4, 10, 4, 8);
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

        environment.addObstacle(4, 4, ObstacleType.WATER);
        environment.addObstacle(3, 13, ObstacleType.ROCK);
        environment.addObstacle(8, 8, ObstacleType.MOUNTAIN);
        environment.addObstacle(12, 10, ObstacleType.TREE);

        System.out.println(INTRODUCTION);

        // Menu selection loop
        while (true) {
            System.out.println(SELECTION);
            System.out.print("Please select an option: ");

            // Try-catch block to deal with non integer input
            try {
                executionInput = scanner.nextInt();

                switch (executionInput) {
                    // Automated tests
                    case 1:
                        System.out.println("Running automated tests...\n");
                        // Prints RobotList
                        environment.printRobotList();
                        System.out.println();
                        
                        // Obstacles tests
                        
                        // Adds obstacle out of the environment bounds
                        environment.addObstacle(11, 11, ObstacleType.ROCK); 
                        // Adds obstacles in valid positions
                        environment.addObstacle(9, 9, ObstacleType.TREE); 
                        environment.addObstacle(8, 8, ObstacleType.ROCK); 
                        environment.addObstacle(1, 1, ObstacleType.MOUNTAIN); 
                        environment.addObstacle(6, 6, ObstacleType.WATER);
                        // Adds obstacle in an occupied position
                        environment.addObstacle(3, 3, ObstacleType.WATER);
                        
                        // Movement tests
                        
                        // Moves talky to a valid position
                        talky.printDir(); talky.printPos();
                        talky.move(1, 4, -5);
                        talky.printDir(); talky.printPos();
                        // Moves talky to invalid positions
                        talky.move(10, 0, 10);
                        talky.move(0, 4, 0);
                        talky.move(-2, 0, -5);
                        talky.printDir(); talky.printPos();
                        // Moves speedy with invalid speed
                        speedy.printDir(); speedy.printPos();
                        speedy.move(-1, 0);
                        speedy.move(9, 9);
                        // Moves speedy to a valid position with valid speed
                        speedy.move(0, 4);
                        speedy.printDir(); speedy.printPos();
                        // Moves jetty withou enough fuel
                        jetty.move(3, 4, 4);
                        // Moves jetty with enough fuel
                        jetty.printDir(); jetty.printPos(); jetty.printFuel();
                        jetty.move(0, 9, 0);
                        jetty.printDir(); jetty.printPos(); jetty.printFuel();

                        // Sandy's tests
                        
                        // Storage tests
                        sandy.printStorage();
                        sandy.fillStorage(5);
                        sandy.fillStorage(6);
                        sandy.emptyStorage(5);
                        sandy.emptyStorage(1);
                        // Invalid inputs
                        sandy.fillStorage(-1);
                        sandy.emptyStorage(-5);
                        
                        // Sensor tests
                        sandy.lookAllDirections();
                        sandy.searchWater();
                        // Moves to look all directions again
                        sandy.move(1, 0);
                        sandy.lookAllDirections();

                        // Talky's tests
                        
                        // No phrases in list
                        talky.speak();
                        // Learn phrases
                        talky.learnPhrase("Hello world!");
                        talky.learnPhrase("Howdy! My name is Talky! Talky the parrot!");
                        talky.learnPhrase("Beep boop!");
                        talky.learnPhrase("Talky wants a cracker!");
                        talky.learnPhrase("404: craker not found!");
                        // Speak
                        talky.speak();
                        talky.speak();
                        talky.speak();
                        talky.speak();
                        talky.speak();
                        talky.speak();
                        talky.speak();
                        talky.speak();
                        // Forget phrases
                        talky.forgetPhrase("Hello world!");
                        talky.forgetPhrase("Howdy! My name is Talky! Talky the parrot!");
                        talky.forgetPhrase("Beep boop!");
                        talky.forgetPhrase("Talky wants a cracker!");
                        talky.forgetPhrase("404: craker not found!");
                        // Tries to forget a phrase that wasn't learned
                        talky.forgetPhrase("It's cracker time!");
                        // No phrases in list (again)
                        talky.speak();
                        
                        System.out.println();
                        break;

                    // Interactive menu (manual input)
                    case 2:
                        System.out.println("Initiating interactive menu...\n");
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
