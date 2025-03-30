public class Main {
    public static void main(String[] args) {
        // Initializing variables;
        Environment environment;
        BaseRobot rob0;
        AerialRobot aRob;

        environment = new Environment(10, 10, 10);

        BaseRobot.setEnvironment(environment);

        rob0 = new BaseRobot("Rob", 0, 0);
        aRob = new AerialRobot("FlyBot", 1, 1, 10);

        environment.addRobot(aRob);
        environment.addRobot(rob0);
        environment.printRobotList();

        aRob.move(-2, 1, 1);

        environment.printRobotList();
    }
}
