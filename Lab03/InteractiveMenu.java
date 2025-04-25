import java.util.InputMismatchException;
import java.util.Scanner;

public class InteractiveMenu {
    private static final String MAIN_MENU = """
            ========== [Main menu] ==========
            1) Robot list
            2) Environment info
            
            9) Back to execution menu
            0) Exit
            """;

    private InteractiveMenu() {}

    public static void mainMenu(Scanner sc, Environment environment) {
        int mainChoice;

        // Menu loop
        while (true) {
            System.out.println(MAIN_MENU);
            System.out.print("Please select an option: ");

            // Try-catch block to deal with non integer input
            try {
                mainChoice = sc.nextInt();
                System.out.println();

                switch (mainChoice) {
                    // Bot selection menu
                    case 1:
                        botMenu(sc, environment);
                        break;

                    // Environment info
                    case 2:
                        environmentInfo(sc, environment);
                        break;

                    // Back to previous menu
                    case 9:
                        return;

                    // End program
                    case 0:
                        System.out.println("Exiting program...");
                        sc.close();
                        System.exit(0);

                    // Invalid input
                    default:
                        System.out.println("Invalid option. Please select a valid number.\n");
                        break;

                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please select a valid number.\n");
                sc.nextLine();
            }
        }
    }

    public static void botMenu(Scanner sc, Environment environment) {
        int botChoice;

        // Menu loop
        while (true) {
            // List available bots for selection
            System.out.println("========== [Bot menu] ==========");

            for (int i = 0; i < environment.getRobotList().size(); i++) {
                BaseRobot currentBot = environment.getRobotList().get(i);
                System.out.printf("%d) %s (%s)\n", i+1, currentBot.getName(), currentBot.getType());
            }

            System.out.println();

            // Back and Exit options
            System.out.println("9) Back to main menu\n" +
                    "0) Exit program\n");
            System.out.print("Please select an option: ");

            // Try-catch block to deal with non integer input
            try {
                botChoice = sc.nextInt();
                System.out.println();

                switch (botChoice) {
                    // Camel Bot menu
                    case 1:
                        camelBotMenu(sc, environment, (CamelRobot)environment.getRobotList().get(0));
                        break;

                    // Fast Bot menu
                    case 2:
                        fastBotMenu(sc, environment, (FastBot)environment.getRobotList().get(1));
                        break;

                    // Parrot Bot menu
                    case 3:
                        parrotBotMenu(sc, environment, (ParrotRobot)environment.getRobotList().get(2));
                        break;

                    // Jet Bot menu
                    case 4:
                        jetBotMenu(sc, environment, (JetBot)environment.getRobotList().get(3));
                        break;

                    // Back to previous menu
                    case 9:
                        return;

                    // Exit program
                    case 0:
                        System.out.println("Exiting program...");
                        sc.close();
                        System.exit(0);

                    // Invalid input
                    default:
                        System.out.println("Invalid option. Please select a valid number.\n");
                        break;

                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please select a valid number.\n");
                sc.nextLine();
            }
        }
    }

    public static void camelBotMenu(Scanner sc, Environment environment, CamelRobot camelBot) {
        String camelCmd;
        int dX, dZ, waterAmount;
        String camelInfo;

        // Menu loop
        while (true) {
            // Camel Bot info
            camelInfo = """
                ========== [Bot menu: Sandy] ==========
                Capable of storing and drawing water from its reserve.
                Can only move if the distance doesn't exceed its maximum speed.
                
                Position: (%d, %d, %d)
                Maximum speed: %d
                Water stored: %d/%d
                
                Available commands:
                - Move [dx] [dz]
                - Fill [amount]
                - Empty [amount]
                
                - Environment (info)
                - Back
                - Exit
                """.formatted(camelBot.getPosX(), camelBot.getPosY(), camelBot.getPosZ(), camelBot.getMaxSpeed(), camelBot.getWaterLevel(), camelBot.getStorageCapacity());

            System.out.println(camelInfo);
            System.out.println("Please input a command.");

            // Case insensitivity
            camelCmd = sc.next().toLowerCase();

            switch (camelCmd) {
                // move method
                case "move":
                    // Try-catch block to deal with non integer input
                    try {
                        dX = sc.nextInt();
                        dZ = sc.nextInt();

                        camelBot.move(dX, dZ);

                    } catch (InputMismatchException e) {
                        System.out.println("Incorrect syntax for command. Expected: \"Move [dx] [dz]\".");
                        System.out.println();
                    }
                    sc.nextLine();
                    break;

                // fillStorage method
                case "fill":
                    // Try-catch block to deal with non integer input
                    try {
                        waterAmount = sc.nextInt();
                        camelBot.fillStorage(waterAmount);

                    } catch (InputMismatchException e) {
                        System.out.println("Incorrect syntax for command. Expected: \"Fill [amount]\".");
                        System.out.println();
                    }
                    sc.nextLine();
                    break;

                // emptyStorage method
                case "empty":
                    // Try-catch block to deal with non integer input
                    try {
                        waterAmount = sc.nextInt();
                        camelBot.emptyStorage(waterAmount);

                    } catch (InputMismatchException e) {
                        System.out.println("Incorrect syntax for command. Expected: \"Empty [amount]\".");
                        System.out.println();
                    }
                    sc.nextLine();
                    break;

                // Environment info
                case "environment":
                    sc.nextLine();
                    System.out.println();
                    environmentInfo(sc, environment);
                    break;

                // Back to previous menu
                case "back":
                    System.out.println();
                    return;

                // Exit program
                case "exit":
                    System.out.println("Exiting program...");
                    sc.close();
                    System.exit(0);

                // Invalid input
                default:
                    System.out.println("Unknown command. Please check your input and try again.");
                    System.out.println();
                    sc.nextLine();
                    break;
            }
        }
    }

    public static void fastBotMenu(Scanner sc, Environment environment, FastBot fastBot) {
        String fastCmd;
        int dX, dZ;
        String fastInfo;

        // Menu loop
        while (true) {
            // Fast Bot info
            fastInfo = """
                ========== [Bot menu: Speedy] ==========
                Can only move if the distance is within its minimum and maximum required speed.
                
                Position: (%d, %d, %d)
                Minimum speed: %d
                Maximum speed: %d
                
                Available commands:
                - Move [dx] [dz]
                
                - Environment (info)
                - Back
                - Exit
                """.formatted(fastBot.getPosX(), fastBot.getPosY(), fastBot.getPosZ(), fastBot.getMinSpeed(), fastBot.getMaxSpeed());

            System.out.println(fastInfo);
            System.out.println("Please input a command.");

            // Case insensitivity
            fastCmd = sc.next().toLowerCase();

            switch (fastCmd) {
                // move method
                case "move":
                    // Try-catch block to deal with non integer input
                    try {
                        dX = sc.nextInt();
                        dZ = sc.nextInt();

                        fastBot.move(dX, dZ);

                    } catch (InputMismatchException e) {
                        System.out.println("Incorrect syntax for command. Expected: \"Move [dx] [dz]\".");
                        System.out.println();
                    }
                    sc.nextLine();
                    break;

                // Environment info
                case "environment":
                    sc.nextLine();
                    System.out.println();
                    environmentInfo(sc, environment);
                    break;

                // Back to previous menu
                case "back":
                    System.out.println();
                    return;

                // Exit program
                case "exit":
                    System.out.println("Exiting program...");
                    sc.close();
                    System.exit(0);

                // Invalid input
                default:
                    System.out.println("Unknown command. Please check your input and try again.");
                    System.out.println();
                    sc.nextLine();
                    break;
            }
        }
    }

    public static void parrotBotMenu(Scanner sc, Environment environment, ParrotRobot parrotBot) {
        String parrotCmd, phrase;
        int dX, dY, dZ;
        String parrotInfo;

        // Menu loop
        while (true) {
            // Parrot Bot info
            parrotInfo = """
                ========== [Bot menu: Talky] ==========
                Can learn phrases and repeat them randomly.
                Can forget learned phrases with the "Forget" command.
                
                Position: (%d, %d, %d)
                Number of learned phrases: %d
                
                Available commands:
                - Move [dx] [dy] [dz]
                - Learn [phrase]
                - Forget [phrase]
                - List (phrases)
                - Speak
                
                - Environment (info)
                - Back
                - Exit
                """.formatted(parrotBot.getPosX(), parrotBot.getPosY(), parrotBot.getPosZ(), parrotBot.getLearnedPhrases().size());

            System.out.println(parrotInfo);
            System.out.println("Please input a command.");

            // Case insensitivity
            parrotCmd = sc.next().toLowerCase();

            switch (parrotCmd) {
                // move method
                case "move":
                    // Try-catch block to deal with non integer input
                    try {
                        dX = sc.nextInt();
                        dY = sc.nextInt();
                        dZ = sc.nextInt();

                        parrotBot.move(dX, dY, dZ);

                    } catch (InputMismatchException e) {
                        System.out.println("Incorrect syntax for command. Expected: \"Move [dx] [dy] [dz]\".");
                        System.out.println();
                    }
                    sc.nextLine();
                    break;

                // learnPhrase method
                case "learn":
                    phrase = sc.nextLine().trim();  // Remove preceding space
                    parrotBot.learnPhrase(phrase);
                    break;

                // forgetPhrase method
                case "forget":
                    phrase = sc.nextLine().trim();  // Remove preceding space
                    parrotBot.forgetPhrase(phrase);
                    break;

                // List learned phrases
                // Not a class method
                case "list":
                    if (parrotBot.getLearnedPhrases().isEmpty()) {
                        System.out.printf("The Parrot Robot \"%s\" hasn't learned any phrases yet.\n", parrotBot.getName());
                    } else {
                        System.out.println("Learned phrases:");
                        for (String i : parrotBot.getLearnedPhrases()) {
                            System.out.printf("- \"%s\"\n", i);
                        }
                    }

                    System.out.println();
                    break;

                // speak method
                case "speak":
                    parrotBot.speak();
                    break;

                // Environment info
                case "environment":
                    sc.nextLine();
                    System.out.println();
                    environmentInfo(sc, environment);
                    break;

                // Back to previous menu
                case "back":
                    System.out.println();
                    return;

                // Exit program
                case "exit":
                    System.out.println("Exiting program...");
                    sc.close();
                    System.exit(0);

                // Invalid input
                default:
                    System.out.println("Unknown command. Please check your input and try again.");
                    System.out.println();
                    sc.nextLine();
                    break;
            }
        }
    }

    public static void jetBotMenu(Scanner sc, Environment environment, JetBot jetBot) {
        String jetCmd;
        int dX, dY, dZ, fuelAmount;
        String jetInfo;

        // Menu loop
        while (true) {
            // Jet Bot info
            jetInfo = """
                ========== [Bot menu: Jetty] ==========
                Consumes fuel for each unit of distance moved.
                Can be refueled with the "Refuel" command.
                
                Position: (%d, %d, %d)
                Fuel: %d/%d
                
                Available commands:
                - Move [dx] [dy] [dz]
                - Refuel [amount]
                
                - Environment (info)
                - Back
                - Exit
                """.formatted(jetBot.getPosX(), jetBot.getPosY(), jetBot.getPosZ(), jetBot.getFuel(), jetBot.getMaxFuel());

            System.out.println(jetInfo);
            System.out.println("Please input a command.");

            // Case insensitivity
            jetCmd = sc.next().toLowerCase();

            switch (jetCmd) {
                // move method
                case "move":
                    // Try-catch block to deal with non integer input
                    try {
                        dX = sc.nextInt();
                        dY = sc.nextInt();
                        dZ = sc.nextInt();

                        jetBot.move(dX, dY, dZ);

                    } catch (InputMismatchException e) {
                        System.out.println("Incorrect syntax for command. Expected: \"Move [dx] [dy] [dz]\".");
                        System.out.println();
                    }
                    sc.nextLine();
                    break;

                // refuel method
                case "refuel":
                    try {
                        fuelAmount = sc.nextInt();
                        jetBot.refuel(fuelAmount);

                    } catch (InputMismatchException e) {
                        System.out.println("Incorrect syntax for command. Expected: \"Refuel [amount]\".");
                        System.out.println();
                    }
                    sc.nextLine();
                    break;

                // Environment info
                case "environment":
                    sc.nextLine();
                    System.out.println();
                    environmentInfo(sc, environment);
                    break;

                // Back to previous menu
                case "back":
                    System.out.println();
                    return;

                // Exit program
                case "exit":
                    System.out.println("Exiting program...");
                    sc.close();
                    System.exit(0);

                // Invalid input
                default:
                    System.out.println("Unknown command. Please check your input and try again.");
                    System.out.println();
                    sc.nextLine();
                    break;
            }
        }
    }

    public static void environmentInfo(Scanner sc, Environment environment) {
        int envInfoChoice;

        // Option selection loop
        while (true) {
            // Basic environment information
            System.out.println("========== [Environment info] ==========");
            System.out.printf("Dimensions: %d x %d x %d\n", environment.getSizeX(), environment.getSizeY(), environment.getSizeZ());
            System.out.println();

            // Robots information
            System.out.println("Active robots:");
            for (int i = 0; i < environment.getRobotList().size(); i++) {
                BaseRobot currentBot = environment.getRobotList().get(i);
                System.out.printf("- %s (%s): (%d, %d, %d)\n", currentBot.getName(), currentBot.getType(), currentBot.getPosX(), currentBot.getPosY(), currentBot.getPosZ());
            }
            System.out.println();

            // Obstacles information
            System.out.println("Obstacles on field:");
            // TODO: List obstacles
            System.out.println();

            // Back and Exit options
            System.out.println("9) Back to previous menu\n" +
                    "0) Exit program\n");
            System.out.print("Please select an option: ");

            // Try-catch block to deal with non integer input
            try {
                envInfoChoice = sc.nextInt();
                System.out.println();


                switch (envInfoChoice) {
                    // Back to previous menu
                    case 9:
                        sc.nextLine();
                        return;

                    // Exit program
                    case 0:
                        System.out.println("Exiting program...");
                        sc.close();
                        System.exit(0);

                    // Invalid option
                    default:
                        System.out.println("Invalid option. Please select a valid number.\n");
                        sc.nextLine();
                        break;

                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please select a valid number.\n");
                sc.nextLine();
            }
        }
    }
}
