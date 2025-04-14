public class Main {
    public static void main(String[] args) {
        // Initializing variables;
        Environment environment = new Environment(10, 10, 10);
        BaseRobot.setEnvironment(environment);

        BaseRobot rob = new BaseRobot("Rob", 0, 0);
        GroundRobot rocky = new GroundRobot("Rocky", 0, 5, 3);
        CamelRobot sandy = new CamelRobot("Sandy", 5, 5, 2, 10);
        FastBot speedy = new FastBot("Speedy", 10, 5, 2, 3);
        AerialRobot harpy = new AerialRobot("Harpy", 0, 10, 5);
        ParrotRobot talky = new ParrotRobot("Talky", 5, 10, 5);
        JetBot jetty = new JetBot("Jetty", 10, 10, 5, 10);

        // Test 0: Environment
        environment.printRobotList();
        System.out.println();

        // Test batch 1: Base robot
        rob.printPos();
        rob.printDir();
            // Basic movement
        rob.move(1, 1);
        rob.printPos();
        rob.printDir();

        rob.move(0, -1);
        rob.printPos();
        rob.printDir();
            // Collision with another robot
        rob.move(4, 5);
        rob.printPos();
        rob.printDir();
            // Out of bounds
        rob.move(11, 11);
        rob.printPos();
        rob.printDir();
        System.out.println();

        // Test batch 2: Ground robot
        rocky.printPos();
            // Successful movement
        rocky.move(1, 1);
        rocky.printPos();
            // Over speed limit
        rocky.move(-2, 2);
        rocky.printPos();
        System.out.println();

        // Test batch 3: Camel robot
            // Check storage
        sandy.printStorage();
            // Fill storage and overfill
        sandy.fillStorage(5);
        sandy.fillStorage(6);
            // Empty storage and insufficient water
        sandy.emptyStorage(5);
        sandy.emptyStorage(1);
            // Invalid values
        sandy.fillStorage(-1);
        sandy.emptyStorage(-1);
        System.out.println();

        // Test batch 4: Fast robot
        speedy.printPos();
            // Successful movement
        speedy.move(-1, 1);
        speedy.printPos();
            // Over max speed
        speedy.move(-2, 2);
        speedy.printPos();
            // Under max speed
        speedy.move(0, 1);
        speedy.printPos();
        System.out.println();

        // Test batch 5: Aerial robot
        harpy.printPos();
            // Successful movement
        harpy.move(1, 5,-1);
        harpy.printPos();
            // Over max height
        harpy.move(0, 1,0);
        harpy.printPos();
            // Under environment boundaries
        harpy.move(0, -6,0);
        harpy.printPos();
        System.out.println();

        // Test 5.1: Aerial collision
        talky.printPos();
        talky.move(-4, 5, -1);
        talky.printPos();
        System.out.println();

        // Test batch 6: Parrot robot
            // Learn phrases
        talky.learnPhrase("Hello world!");
        talky.learnPhrase("Howdy! My name is Talky! Talky the parrot!");
        talky.learnPhrase("Beep boop!");
            // Speak a random learned phrase
        talky.speak();
        talky.speak();
        talky.speak();
            // Forget phrases
        talky.forgetPhrase("Hello world!");
        talky.forgetPhrase("Howdy! My name is Talky! Talky the parrot!");
        talky.forgetPhrase("Beep boop!");
            // No phrases in list
        talky.speak();
            // Empty phrase
        talky.learnPhrase("");
        System.out.println();

        // Test batch 7: Jet robot
        jetty.printPos();
            // Print fuel in storage
        jetty.printFuel();
            // Movement and fuel cost
        jetty.move(-1, 2, -1);
        jetty.printPos();
        jetty.printFuel();
            // Insufficient fuel
        jetty.move(-2, 5, -2);
        jetty.printPos();
        jetty.printFuel();
            // Refuel
        jetty.refuel(2);
        jetty.printFuel();
            // Overfill
        jetty.refuel(3);
        jetty.printFuel();
            // Invalid value
        jetty.refuel(-1);
        jetty.printFuel();
        System.out.println();

        // Final positions
        environment.printRobotList();
    }
}
