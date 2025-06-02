/*
The JetBot class is a subclass of AerialRobot which consumes fuel equal to the Manhattan distance moved.
 */

public class JetBot extends AerialRobot implements FluidHandler {
    private int fuel;   // Current fuel in storage
    private final int maxFuel;  // Max fuel capacity

    // JetBot constructor
    public JetBot(String name, int startX, int startZ, int maxPosY, int maxFuel, double sensorRadius) {
        super(name, startX, startZ, maxPosY, sensorRadius);
        this.setType("Jet Bot");
        this.maxFuel = maxFuel;
        this.fuel = maxFuel;
    }

    // Override of move method
    // Consumes 1 liter of fuel per unit moved
    @Override
    public void move(int dX, int dY, int dZ, boolean doPrintPos) throws RobotUnavailableException {
        // Checks if the robot is ON
        // Throws RobotUnavailableException otherwise
        if (!isOn()) {
            throw new RobotUnavailableException("The robot %s is currently OFF. Please turn it on to proceed.".formatted(getName()));
        }

        int startX = getPosX(), startY = getPosY(), startZ = getPosZ();

        int totalDist = Math.abs(dX) + Math.abs(dY) + Math.abs(dZ);

        if (totalDist <= this.fuel) {
            // Successful operation
            super.move(dX, dY, dZ, doPrintPos);
            fuel -= Math.abs(getPosX() - startX) + Math.abs(getPosY() - startY) + Math.abs(getPosZ() - startZ);
        // Insufficient fuel
        } else {
            System.out.println("The robot does not have enough fuel to move to the desired position. Position unchanged.");
        }
    }

    @Override
    public void move(int dX, int dY, int dZ) {
        move(dX, dY, dZ, true);
    }


    // Refills the robot's fuel tank by [fuelAmount]
    public void fill(int fuelAmount) throws RobotUnavailableException {
        // Checks if the robot is ON
        // Throws RobotUnavailableException otherwise
        if (!isOn()) {
            throw new RobotUnavailableException("The robot %s is currently OFF. Please turn it on to proceed.".formatted(getName()));
        }

        // Checks if the input is valid
        if (fuelAmount <= 0) {
            System.out.println("Invalid input. Must be an integer greater than 0.");
        // Successful operation with new fuel < maxFuel
        } else if (this.fuel + fuelAmount < this.maxFuel) {
            this.fuel += fuelAmount;
            System.out.printf("Robot \"%s\" has been refueled. Current fuel: %d L.\n", this.getName(), this.fuel);
        // Successful operation with new fuel == maxFuel
        } else if (this.fuel + fuelAmount == this.maxFuel) {
            this.fuel += fuelAmount;
            System.out.printf("Robot \"%s\" has been refueled to max capacity.\n", this.getName());
        // New fuel exceeds maxFuel || Sets fuel to max value
        } else {
            this.fuel = this.maxFuel;
            System.out.printf("Fuel exceeds max capacity. Robot \"%s\" has been refueled to max capacity.\n", this.getName());
        }
    }

    // Refill the robot's fuel tank to max capacity
    public void refill() throws RobotUnavailableException {
        // Checks if the robot is ON
        // Throws RobotUnavailableException otherwise
        if (!isOn()) {
            throw new RobotUnavailableException("The robot %s is currently OFF. Please turn it on to proceed.".formatted(getName()));
        }

        if(fuel<maxFuel) {
            int amount = maxFuel - fuel;
            fill(amount);
        }
        else {
            System.out.printf("%s's fuel tank is already at full capacity.%n", getName());
        }
    }

    // Performs JetBot's specific task
    // Checks how many units tall, at least, the environment is
    public void specificTask() throws RobotUnavailableException {
        // Checks if the robot is ON
        // Throws RobotUnavailableException otherwise
        if (!isOn()) {
            throw new RobotUnavailableException("The robot %s is currently OFF. Please turn it on to proceed.".formatted(getName()));
        }

        int dY = 0;

        while (!checkObstacles(getPosX(), getPosY() + 1, getPosZ()) && getPosY() < getMaxPosY()) {
            move(0, 1, 0, false);
            fuel += 1;
            dY += 1;
        }

        if (!checkObstacles(getPosX(), getPosY() + 1, getPosZ())) {
            System.out.printf("The robot %s concludes that the environment has at least %d unit(s) of height.", getName(), getPosY() + 2);
        }
        System.out.printf("The robot %s concludes that the environment has %d unit(s) of height.\n", getName(), getPosY() + 1);

        for (int i = 0; i < dY; i++) {
            move(0, -1, 0, false);
            fuel += 1;
        }
    }

    // Prints the current volume of fuel in the robot's storage
    public void printFluidLevel() {
        System.out.printf("The robot \"%s\" has %d liters of fuel.\n",
                this.getName(), this.fuel);
    }

    @Override
    public String getDescription() {
        return "The JetBot class is a subclass of AerialRobot which consumes fuel equal to the Manhattan distance moved.";
    }

    // Getters and setters
    public int getFuel() {
        return fuel;
    }

    public void setFuel(int fuel) {
        this.fuel = fuel;
    }

    public int getMaxFuel() {
        return maxFuel;
    }
}
