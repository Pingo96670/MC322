package lab;

import lab.entities.bots.BaseRobot;
import lab.entities.bots.automatic.MoleBot;
import lab.entities.bots.RobotUnavailableException;
import lab.entities.bots.aerial.JetBot;
import lab.entities.bots.aerial.ParrotRobot;
import lab.entities.bots.ground.CamelRobot;
import lab.entities.bots.ground.FastBot;
import lab.environment.Environment;
import lab.environment.ObjectOutOfBoundsException;
import lab.environment.ObjectOverlapException;
import lab.environment.UnsuccessfulRemovalException;
import lab.entities.obstacles.ObstacleType;
import lab.system.InteractiveMenu;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static final String INTRODUCTION = """
        ========== Robot Simulator ==========
        Welcome to update 5 of Robot Simulator!
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
        MoleBot drilly = new MoleBot("Drilly", -1, -1, 4);

        System.out.println("Preparing program...");
        environment.addRobot(sandy);
        environment.addRobot(speedy);
        environment.addRobot(talky);
        environment.addRobot(jetty);
        environment.getRobotList().add(drilly);
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

                        // Single feature test
                        // Automated task
                        System.out.println("========== Mole Bot - Automated task ==========");
                        System.out.println("Executing Mole Bot's automated task");
                        drilly.specificTask();
                        System.out.println("Check the MoleBotLog.txt file in /bin/lab for task details.");

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
                        drilly.shutdown();
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
