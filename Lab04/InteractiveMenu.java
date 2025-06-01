import java.util.InputMismatchException;
import java.util.Scanner;

public class InteractiveMenu {
    private static boolean ansiCheck = false;
    private static boolean doAnsi= false;
    private static final String MAIN_MENU = """
            ========== [Main menu] ==========
            1) Robot list
            2) Environment info
            
            9) Back to execution menu
            0) Exit
            """;

    private InteractiveMenu() {}

    private static void promptEnterPress(Scanner sc) {
        System.out.println("Press \"ENTER\" to continue...");
        sc.nextLine();
    }

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
                promptEnterPress(sc);
            }
        }
    }

    private static void botMenu(Scanner sc, Environment environment) {
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
                promptEnterPress(sc);
            }
        }
    }

    private static void camelBotMenu(Scanner sc, Environment environment, CamelRobot camelBot) {
        String camelCmd;
        int dX, dZ, waterAmount;
        String camelInfo;
        String menuState = "INFO";

        // Menu loop
        while (true) {
            // Camel Bot info
            camelInfo = """
                ========== [Bot menu: %s - INFO] ==========
                Capable of storing and drawing water from its reserve.
                Can only move if the distance doesn't exceed its maximum speed.
                
                Position: (%d, %d, %d)
                Facing: %s
                Obstacle sensor range: %.2f units
                Water sensor range: %.2f units
                Maximum speed: %d
                Water stored: %d/%d
                
                Available commands:
                - Move [dx] [dz]
                - Fill [amount]
                - Empty [amount]
                - Obstacle (sensor)
                - Water (sensor)
                
                - Swap (Bot info/Flat map)
                - Environment (info)
                - Back
                - Exit
                """.formatted(camelBot.getName(), camelBot.getPosX(), camelBot.getPosY(), camelBot.getPosZ(), camelBot.getDirection(), camelBot.getObstacleSensor().getDistanceRadius(), camelBot.getWaterSensor().getDistanceRadius(), camelBot.getMaxSpeed(), camelBot.getWaterLevel(), camelBot.getStorageCapacity());

            if (menuState.equals("INFO")) {
                System.out.println(camelInfo);

            } else {
                ansiChecker(sc);
                System.out.printf("========== [Bot menu: %s - FLAT MAP] ==========\n", camelBot.getName());
                environment.printFlatMap(doAnsi);
                System.out.println();

                System.out.printf("%s: (%d, %d, %d)\n", camelBot.getName(), camelBot.getPosX(), camelBot.getPosY(), camelBot.getPosZ());
                System.out.println();

                System.out.println("Use command \"Swap\" to return to INFO mode.");
            }

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
                    promptEnterPress(sc);
                    break;

                // fillStorage method
                case "fill":
                    // Try-catch block to deal with non integer input
                    try {
                        waterAmount = sc.nextInt();
                        camelBot.fill(waterAmount);

                    } catch (InputMismatchException e) {
                        System.out.println("Incorrect syntax for command. Expected: \"Fill [amount]\".");
                        System.out.println();
                    }

                    sc.nextLine();
                    promptEnterPress(sc);
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
                    promptEnterPress(sc);
                    break;

                // Obstacle sensor monitor method
                case "obstacle":
                    camelBot.getObstacleSensor().monitor();

                    sc.nextLine();
                    promptEnterPress(sc);
                    break;

                // Water sensor monitor method
                case "water":
                    camelBot.searchWater();

                    sc.nextLine();
                    promptEnterPress(sc);
                    break;

                // Swaps between menu modes
                case "swap":
                    if (menuState.equals("INFO")) {
                        menuState = "MAP";

                    } else {
                        menuState = "INFO";
                    }

                    System.out.printf("Menu mode changed to \"%s\".\n", menuState);

                    sc.nextLine();
                    promptEnterPress(sc);
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
                    promptEnterPress(sc);
                    break;
            }
        }
    }

    private static void fastBotMenu(Scanner sc, Environment environment, FastBot fastBot) {
        String fastCmd;
        int dX, dZ;
        String fastInfo;
        String menuState = "INFO";

        // Menu loop
        while (true) {
            // Fast Bot info
            fastInfo = """
                ========== [Bot menu: %s - INFO] ==========
                Can only move if the distance is within its minimum and maximum required speed.
                
                Position: (%d, %d, %d)
                Facing: %s
                Obstacle sensor range: %.2f units
                Minimum speed: %d
                Maximum speed: %d
                
                Available commands:
                - Move [dx] [dz]
                - Obstacle (sensor)
                
                - Swap (Bot info/Flat map)
                - Environment (info)
                - Back
                - Exit
                """.formatted(fastBot.getName(), fastBot.getPosX(), fastBot.getPosY(), fastBot.getPosZ(), fastBot.getDirection(), fastBot.getObstacleSensor().getDistanceRadius(), fastBot.getMinSpeed(), fastBot.getMaxSpeed());

            if (menuState.equals("INFO")) {
                System.out.println(fastInfo);

            } else {
                ansiChecker(sc);
                System.out.printf("========== [Bot menu: %s - FLAT MAP] ==========\n", fastBot.getName());
                environment.printFlatMap(doAnsi);
                System.out.println();

                System.out.printf("%s: (%d, %d, %d)\n", fastBot.getName(), fastBot.getPosX(), fastBot.getPosY(), fastBot.getPosZ());
                System.out.println();

                System.out.println("Use command \"Swap\" to return to INFO mode.");
            }

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
                    promptEnterPress(sc);
                    break;

                // Obstacle sensor monitor method
                case "obstacle":
                    fastBot.getObstacleSensor().monitor();

                    sc.nextLine();
                    promptEnterPress(sc);
                    break;

                // Swaps between menu modes
                case "swap":
                    if (menuState.equals("INFO")) {
                        menuState = "MAP";

                    } else {
                        menuState = "INFO";
                    }

                    System.out.printf("Menu mode changed to \"%s\".\n", menuState);

                    sc.nextLine();
                    promptEnterPress(sc);
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
                    promptEnterPress(sc);
                    break;
            }
        }
    }

    private static void parrotBotMenu(Scanner sc, Environment environment, ParrotRobot parrotBot) {
        String parrotCmd, phrase;
        int dX, dY, dZ;
        String parrotInfo;
        String menuState = "INFO";

        // Menu loop
        while (true) {
            // Parrot Bot info
            parrotInfo = """
                ========== [Bot menu: %s - INFO] ==========
                Can learn phrases and repeat them randomly.
                Can forget learned phrases with the "Forget" command.
                
                Position: (%d, %d, %d)
                Facing: %s
                Obstacle sensor range: %.2f units
                Number of learned phrases: %d
                
                Available commands:
                - Move [dx] [dy] [dz]
                - Obstacle (sensor)
                - Learn [phrase]
                - Forget [phrase]
                - List (phrases)
                - Speak
                
                - Swap (Bot info/Flat map)
                - Environment (info)
                - Back
                - Exit
                """.formatted(parrotBot.getName(), parrotBot.getPosX(), parrotBot.getPosY(), parrotBot.getPosZ(), parrotBot.getDirection(), parrotBot.getObstacleSensor().getDistanceRadius(), parrotBot.getLearnedPhrases().size());

            if (menuState.equals("INFO")) {
                System.out.println(parrotInfo);

            } else {
                ansiChecker(sc);
                System.out.printf("========== [Bot menu: %s - FLAT MAP] ==========\n", parrotBot.getName());
                environment.printFlatMap(doAnsi);
                System.out.println();

                System.out.printf("%s: (%d, %d, %d)\n", parrotBot.getName(), parrotBot.getPosX(), parrotBot.getPosY(), parrotBot.getPosZ());
                System.out.println();

                System.out.println("Use command \"Swap\" to return to INFO mode.");
            }

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
                    promptEnterPress(sc);
                    break;

                // Obstacle sensor monitor method
                case "obstacle":
                    parrotBot.getObstacleSensor().monitor();

                    sc.nextLine();
                    promptEnterPress(sc);
                    break;

                // learnPhrase method
                case "learn":
                    phrase = sc.nextLine().trim();  // Remove preceding space
                    parrotBot.learnPhrase(phrase);

                    sc.nextLine();
                    promptEnterPress(sc);
                    break;

                // forgetPhrase method
                case "forget":
                    phrase = sc.nextLine().trim();  // Remove preceding space
                    parrotBot.forgetPhrase(phrase);

                    sc.nextLine();
                    promptEnterPress(sc);
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

                    sc.nextLine();
                    promptEnterPress(sc);
                    break;

                // speak method
                case "speak":
                    parrotBot.speak();

                    sc.nextLine();
                    promptEnterPress(sc);
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
                    promptEnterPress(sc);
                    break;
            }
        }
    }

    private static void jetBotMenu(Scanner sc, Environment environment, JetBot jetBot) {
        String jetCmd;
        int dX, dY, dZ, fuelAmount;
        String jetInfo;
        String menuState = "INFO";

        // Menu loop
        while (true) {
            // Jet Bot info
            jetInfo = """
                ========== [Bot menu: %s - INFO] ==========
                Consumes fuel for each unit of distance moved.
                Can be refueled with the "Refuel" command.
                
                Position: (%d, %d, %d)
                Facing: %s
                Obstacle sensor range: %.2f units
                Fuel: %d/%d
                
                Available commands:
                - Move [dx] [dy] [dz]
                - Obstacle (sensor)
                - Refuel [amount]
                
                - Swap (Bot info/Flat map)
                - Environment (info)
                - Back
                - Exit
                """.formatted(jetBot.getName(), jetBot.getPosX(), jetBot.getPosY(), jetBot.getPosZ(), jetBot.getDirection(), jetBot.getObstacleSensor().getDistanceRadius(), jetBot.getFuel(), jetBot.getMaxFuel());

            if (menuState.equals("INFO")) {
                System.out.println(jetInfo);

            } else {
                ansiChecker(sc);
                System.out.printf("========== [Bot menu: %s - FLAT MAP] ==========\n", jetBot.getName());
                environment.printFlatMap(doAnsi);
                System.out.println();

                System.out.printf("%s: (%d, %d, %d)\n", jetBot.getName(), jetBot.getPosX(), jetBot.getPosY(), jetBot.getPosZ());
                System.out.println();

                System.out.println("Use command \"Swap\" to return to INFO mode.");
            }

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
                    promptEnterPress(sc);
                    break;

                // Obstacle sensor monitor method
                case "obstacle":
                    jetBot.getObstacleSensor().monitor();

                    sc.nextLine();
                    promptEnterPress(sc);
                    break;

                // refuel method
                case "refuel":
                    try {
                        fuelAmount = sc.nextInt();
                        jetBot.fill(fuelAmount);

                    } catch (InputMismatchException e) {
                        System.out.println("Incorrect syntax for command. Expected: \"Refuel [amount]\".");
                        System.out.println();
                    }

                    sc.nextLine();
                    promptEnterPress(sc);
                    break;

                // Swaps between menu modes
                case "swap":
                    if (menuState.equals("INFO")) {
                        menuState = "MAP";

                    } else {
                        menuState = "INFO";
                    }

                    System.out.printf("Menu mode changed to \"%s\".\n", menuState);

                    sc.nextLine();
                    promptEnterPress(sc);
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
                    promptEnterPress(sc);
                    break;
            }
        }
    }

    private static void environmentInfo(Scanner sc, Environment environment) {
        int envInfoChoice;
        String ENV_OPTIONS = """
                1) Flat map
                
                9) Back to previous menu
                0) Exit
                """;

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
                System.out.printf("- %s (%s): (%d, %d, %d), facing %s\n", currentBot.getName(), currentBot.getType(), currentBot.getPosX(), currentBot.getPosY(), currentBot.getPosZ(), currentBot.getDirection());
            }
            System.out.println();

            // Back and Exit options
            System.out.println(ENV_OPTIONS);
            System.out.print("Please select an option: ");

            // Try-catch block to deal with non integer input
            try {
                envInfoChoice = sc.nextInt();
                System.out.println();


                switch (envInfoChoice) {
                    // Print flat environment map
                    case 1:
                        mapMenu(sc, environment);
                        break;

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
                        promptEnterPress(sc);
                        break;

                }
            } catch(InputMismatchException e) {
                System.out.println("Invalid input. Please select a valid number.\n");
                sc.nextLine();
                promptEnterPress(sc);
            }
        }
    }

    private static void mapMenu(Scanner sc, Environment environment) {
        int mapChoice;
        String MAP_INFO = """
                ========== Environment map ==========
                This is a flat map of the environment.
                Aerial robots' heights are not shown, and they may be omitted by an obstacle under them.
                """;

        String MAP_OPTIONS = """
                1) Robot menu
                
                9) Back to Environment menu
                0) Exit
                """;

        sc.nextLine();
        ansiChecker(sc);

        System.out.println(MAP_INFO);

        environment.printFlatMap(doAnsi);

        System.out.println();

        // Menu loop
        while (true) {
            // Back and Exit options
            System.out.println(MAP_OPTIONS);
            System.out.print("Please select an option: ");

            // Try-catch block to deal with non integer input
            try {
                mapChoice = sc.nextInt();
                System.out.println();

                switch (mapChoice) {
                    case 1:
                        sc.nextLine();
                        botMenu(sc, environment);
                        return;

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
                        promptEnterPress(sc);
                        break;

                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please select a valid number.\n");
                sc.nextLine();
                promptEnterPress(sc);
            }
        }
    }

    private static void ansiChecker(Scanner sc) {
        String ANSI_CHECK = """
                ========== ANSI configuration ==========
                First, we need to configure your map's color display.
                An ANSI-capable terminal display is recommended for ease of reading.
                
                \u001B[31mIs this text red for you?\u001B[0m (Y/N)
                """;

        if (!ansiCheck) {
            System.out.print(ANSI_CHECK);

            while(!ansiCheck) {
                switch (sc.nextLine().toLowerCase()) {
                    case "y":
                        System.out.println("Great! You have an ANSI-capable terminal!");
                        promptEnterPress(sc);
                        ansiCheck = true;
                        doAnsi = true;
                        break;

                    case "n":
                        System.out.println("Unfortunately, you do not have an ANSI-capable terminal. If possible, search for a capable alternative for a better experience.");
                        promptEnterPress(sc);
                        ansiCheck = true;
                        doAnsi = false;
                        break;

                    default:
                        System.out.println("Please input a valid answer. (Y/N)");
                        break;
                }
            }
        }
    }
}

