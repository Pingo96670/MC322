package Lab02;

public class FastBot extends GroundRobot {
    private final int minSpeed;

    public FastBot(String name, int posX, int posZ, int minSpeed, int maxSpeed) {
        super(name, posX, posZ, maxSpeed);
        this.setType("Fast Bot");
        this.minSpeed = minSpeed;
    }

    @Override
    public void move(int dX, int dZ) {
        if (Math.abs(dX) + Math.abs(dZ) >= this.minSpeed) {
            super.move(dX, dZ);
        } else {
            System.out.println("Movement speed is too low for this robot. Position unchanged.");
        }
    }

    public int getMinSpeed() {
        return minSpeed;
    }
}