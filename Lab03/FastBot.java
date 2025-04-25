/*
The FastBot class is a subclass of GroundBot can only move if the total distance is:
 - Greater than its minimum speed AND;
 - Lesser than its maximum speed (inherited movement property of GroundRobot).
 */


public class FastBot extends GroundRobot {
    private final int minSpeed;

    // FastRobot constructor
    public FastBot(String name, int posX, int posZ, int minSpeed, int maxSpeed) {
        super(name, posX, posZ, maxSpeed);
        this.setType("Fast Bot");
        this.minSpeed = minSpeed;
    }

    // Adapted move method
    // Moves the robot if minSpeed <= totalDist <= maxSpeed
    @Override
    public void move(int dX, int dZ) {
        if (Math.abs(dX) + Math.abs(dZ) >= this.minSpeed) {
            super.move(dX, dZ);
        } else {
            System.out.println("Movement speed is too low for this robot. Position unchanged.");
            System.out.println();
        }
    }

    // Getters
    public int getMinSpeed() {
        return minSpeed;
    }
}