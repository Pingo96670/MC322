/*
The JetBot class is a subclass of AerialRobot which
 */

public class JetBot extends AerialRobot implements FluidHandler {
    private int fuel;   // Current fuel in storage
    private final int maxFuel;  // Max fuel capacity

    // JetBot constructor
    public JetBot(String name, int startX, int startZ, int maxPosY, int maxFuel, double distanceRadius) {
        super(name, startX, startZ, maxPosY, distanceRadius);
        this.setType("Jet Bot");
        this.maxFuel = maxFuel;
        this.fuel = maxFuel;
    }

    // Override of move method
    // Consumes 1 liter of fuel per unit moved
    @Override
    public void move(int dX, int dY, int dZ) {
        int startX = getPosX(), startY = getPosY(), startZ = getPosZ();

        int totalDist = Math.abs(dX) + Math.abs(dY) + Math.abs(dZ);

        if (totalDist <= this.fuel) {
            // Successful operation
            super.move(dX, dY, dZ);
            fuel -= Math.abs(getPosX() - startX) + Math.abs(getPosY() - startY) + Math.abs(getPosZ() - startZ);
        // Insufficient fuel
        } else {
            System.out.println("The robot does not have enough fuel to move to the desired position. Position unchanged.");
            System.out.println();
        }
    }

    // Refill the robot's fuel tank to max capacity
    public void refill() {
        if(fuel<maxFuel) {
            int amount = maxFuel - fuel;
            fill(amount);
        }
        else {
            System.out.printf("%s's fuel tank is already at full capacity.%n", getName());
        }
    }

    // Refills the robot's fuel tank by [fuelAmount]
    public void fill(int fuelAmount) {
        // Checks if the input is valid
        if (fuelAmount <= 0) {
            System.out.println("Invalid input. Must be an integer greater than 0.");
        // Successful operation with new fuel < maxFuel
        } else if (this.fuel + fuelAmount < this.maxFuel) {
            this.fuel += fuelAmount;
            System.out.printf("Robot \"%s\" has been refueled. Current fuel: %d L.\n", this.getName(), this.fuel);
            System.out.println();
        // Successful operation with new fuel == maxFuel
        } else if (this.fuel + fuelAmount == this.maxFuel) {
            this.fuel += fuelAmount;
            System.out.printf("Robot \"%s\" has been refueled to max capacity.\n", this.getName());
            System.out.println();
        // New fuel exceeds maxFuel || Sets fuel to max value
        } else {
            this.fuel = this.maxFuel;
            System.out.printf("Fuel exceeds max capacity. Robot \"%s\" has been refueled to max capacity.\n", this.getName());
            System.out.println();
        }
    }

    // Prints the current volume of fuel in the robot's storage
    public void printFluidLevel() {
        System.out.printf("The robot \"%s\" has %d liters of fuel.\n",
                this.getName(), this.fuel);
        System.out.println();
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
