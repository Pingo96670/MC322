package Lab02;

public class JetBot extends AerialRobot {
    private int fuel;
    private final int maxFuel;

    public JetBot(String name, int startX, int startZ, int maxPosY, int maxFuel) {
        super(name, startX, startZ, maxPosY);
        this.setType("Jet Bot");
        this.maxFuel = maxFuel;
        this.fuel = maxFuel;
    }

    @Override
    public void move(int dX, int dY, int dZ) {
        int totalDist = Math.abs(dX) + Math.abs(dY) + Math.abs(dZ);

        if (totalDist <= this.fuel) {
            super.move(dX, dY, dZ);
            fuel -= totalDist;
        } else {
            System.out.println("The robot does not have enough fuel to move to the desired position. Position unchanged.");
        }
    }

    public void refuel(int fuelAmount) {
        if (fuelAmount <= 0) {
            System.out.println("Invalid input. Must be an integer greater than 0.");
        } else if (this.fuel + fuelAmount < this.maxFuel) {
            this.fuel += fuelAmount;
            System.out.printf("Robot \"%s\" has been refueled. Current fuel: %d L.\n", this.getName(), this.fuel);
        } else if (this.fuel + fuelAmount == this.maxFuel) {
            this.fuel += fuelAmount;
            System.out.printf("Robot \"%s\" has been refueled to max capacity.\n", this.getName());
        } else {
            this.fuel = this.maxFuel;
            System.out.printf("Fuel exceeds max capacity. Robot \"%s\" has been refueled to max capacity.\n", this.getName());
        }
    }

    public void printFuel() {
        System.out.printf("The robot \"%s\" has %d liters of fuel.\n",
                this.getName(), this.fuel);
    }

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
