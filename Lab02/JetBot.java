/*
The JetBot class is a subclass of AerialRobot which
 */

public class JetBot extends AerialRobot {
    private int fuel;   // Current fuel in storage
    private final int maxFuel;  // Max fuel capacity

    // JetBot constructor
    public JetBot(String name, int startX, int startZ, int maxPosY, int maxFuel) {
        super(name, startX, startZ, maxPosY);
        this.setType("Jet Bot");
        this.maxFuel = maxFuel;
        this.fuel = maxFuel;
    }

    // Override of move method
    // Consumes 1 liter of fuel per unit moved
    @Override
    public void move(int dX, int dY, int dZ) {
        int totalDist = Math.abs(dX) + Math.abs(dY) + Math.abs(dZ);

        if (totalDist <= this.fuel) {
            // Successful operation
            super.move(dX, dY, dZ);
            fuel -= totalDist;
        // Insufficient fuel
        } else {
            System.out.println("The robot does not have enough fuel to move to the desired position. Position unchanged.");
        }
    }

    // Refills the robot's fuel tank by [fuelAmount]
    public void refuel(int fuelAmount) {
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

    // Prints the current volume of fuel in the robot's storage
    public void printFuel() {
        System.out.printf("The robot \"%s\" has %d liters of fuel.\n",
                this.getName(), this.fuel);
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
