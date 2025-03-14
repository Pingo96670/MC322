package Lab01;

public class Grid {
    private final int sizeX;    // Grid's x dimension
    private final int sizeY;    // Grid's y dimension

    // Grid constructor
    public Grid(int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }

    // Method to check if a position is within grid bounds ([0, size])
    public boolean isWithinBounds(int posX, int posY) {
        return posX <= sizeX && posY <= sizeY &&
                posX >=0 && posY >= 0;
    }
}