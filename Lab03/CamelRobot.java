/*
The CamelRobot class is a subclass of GroundRobot capable of storing and transporting water.
 */

public class CamelRobot extends GroundRobot {
    private final int storageCapacity; // Max volume of water in the reserve (liters)
    private int waterLevel = 0; // Volume of water currently in reserve (liters)
    private final WaterSensor waterSensor;

    // CamelRobot constructor
    public CamelRobot(String name, int posX, int posZ, int maxSpeed, int storageCapacity, double obstacleSensorRadius, double waterSensorRadius){
        super(name, posX, posZ, maxSpeed, obstacleSensorRadius);
        this.setType("Camel Bot");
        this.storageCapacity = storageCapacity;
        this.waterSensor = new WaterSensor(waterSensorRadius);
    }

    @Override
    public void joinSensor() {
        super.joinSensor();
        this.waterSensor.setOwnerBot(this);
    }

    // Uses the WaterSensor to detect nearby water source
    public void searchWater() {
        this.waterSensor.monitor();
    }

    // Fills the robot's reserve with [amount]
    public void fillStorage(int amount) {
        // Validating if [amount] is a valid input
        if (amount <= 0) {
            System.out.println("Invalid input. Must be an integer greater than 0.");
        // Validating if the reserve has enough capacity
        } else if ((waterLevel + amount) > storageCapacity) {
            System.out.println("Cannot add. Amount exceeds the storage capacity.");
        // Successful operation
        } else {
            waterLevel += amount;
            System.out.printf("Robot \"%s\"'s water reserve was filled with %d liters. Total reserve: %d liters.\n",
                    getName(), amount, waterLevel);
        }
    }

    // Empties the robot's reserve by [amount]
    public void emptyStorage(int amount) {
        // Validating if [amount] is a valid input
        if (amount <= 0) {
            System.out.println("Invalid input. Must be an integer greater than 0.");
        // Validating if the reserve has enough water
        } else if ((waterLevel - amount) < 0) {
            System.out.printf("The robot \"%s\" does not have enough water in its reserve. Current amount: %d liters.\n", getName(), getWaterLevel());
        // Successful operation
        } else {
            waterLevel -= amount;
            System.out.printf("The robot \"%s\"'s water reserve was emptied by %d liters. Total reserve: %d liters.\n",
            getName(), amount, waterLevel);
        }
    }

    // Prints the current water in the robot's reserve
    public void printStorage() {
        System.out.printf("The robot \"%s\"'s water reserve contains %d liters.\n",
                getName(), waterLevel);
        System.out.println();
    }

    // Getters and setters

    public int getStorageCapacity() {
        return storageCapacity;
    }

    public int getWaterLevel() {
        return waterLevel;
    }

    public void setWaterLevel(int waterLevel) {
        this.waterLevel = waterLevel;
    }

    public WaterSensor getWaterSensor() {
        return waterSensor;
    }
}
