package Lab02;
    /*
    A ideia é que Lab02.CamelRobot seja um robô terrestre capaz de armazenar e transportar água.
    */

public class CamelRobot extends GroundRobot{
    private final int storageCapacity; //volume de água comportado em L
    private int waterLevel; //volume de água armazenada em L

    public CamelRobot(String name, int posX, int posZ, int maxSpeed, int storageCapacity){
        super(name, posX, posZ, maxSpeed);
        this.setType("Camel Bot");
        this.storageCapacity = storageCapacity;
        waterLevel = 0;
    }

    public void fillStorage(int amount) {
        if (amount <= 0) {
            System.out.println("Invalid input. Must be an integer greater than 0.");
        } else if ((waterLevel + amount) <= storageCapacity) {
            waterLevel += amount;
            System.out.printf("Robot \"%s\"'s water reserve was filled with %d liters. Total reserve: %d liters.\n",
            getName(), amount, waterLevel);
        } else {
            System.out.println("Cannot add. Amount exceeds the storage capacity.");
        }
    }

    public void emptyStorage(int amount) {
        if (amount <= 0) {
            System.out.println("Invalid input. Must be an integer greater than 0.");
        } else if ((waterLevel-amount) < 0) {
            System.out.printf("The robot \"%s\" does not have enough water in its reserve. Current amount: %d liters.\n", getName(), getWaterLevel());
        } else {
            waterLevel -= amount;
            System.out.printf("The robot \"%s\"'s water reserve was emptied by %d liters. Total reserve: %d liters.\n",
            getName(), amount, waterLevel);
        }
    }

    public void printStorage() {
        System.out.printf("The robot \"%s\"'s water reserve contains %d liters.\n",
                getName(), waterLevel);
    }

    public int getStorageCapacity() {
        return storageCapacity;
    }

    public int getWaterLevel() {
        return waterLevel;
    }

    public void setWaterLevel(int waterLevel) {
        this.waterLevel = waterLevel;
    }
}
