package Lab01;

public class Robot {
    private final String name;    // Robot's name
    private int posX;    // Robot's x coordinate
    private int posY;    // Robot's y coordinate

    // Robot constructor
    public Robot(String name, int startX, int startY) {
        this.name = name;
        this.posX = startX;
        this.posY = startY;
    }

    // Method to update a robot's position
    public void move(int dX, int dY) {
        this.posX += dX;
        this.posY += dY;
    }

    // Method to print a robot's coordinates
    public void printPos() {
        System.out.printf("The robot %s is currently in the position (%d, %d).", this.name, this.posX, this.posY);
    }

    // Getters for variables
    public String getName() {
        return this.name;
    }

    public int getPosX() {
        return this.posX;
    }

    public int getPosY() {
        return this.posY;
    }
}
