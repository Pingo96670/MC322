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

        Environment environment = new Environment(10, 10, 10);
        BaseRobot.setEnvironment(environment);

        CamelRobot sandy = new CamelRobot("Sandy", 5, 5, 2, 10, 5, 5);
        FastBot speedy = new FastBot("Speedy", 10, 5, 4, 8, 5);
        ParrotRobot talky = new ParrotRobot("Talky", 5, 10, 5, 5);
        JetBot jetty = new JetBot("Jetty", 10, 10, 10, 10, 5);

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
                        // TODO: Automated tests

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
