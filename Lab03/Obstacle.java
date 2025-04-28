public class Obstacle {
    private final int posX1;    // X coordinate of southwest corner
    private final int posX2;    // Z coordinate of southwest corner
    private final int posZ1;    // X coordinate of northeast corner
    private final int posZ2;    // Z coordinate of northeast corner
    private final int height;   // Obstacle's height
    private final ObstacleType type;    // Obstacle's type

    // Constructor
    public Obstacle(ObstacleType type, int x1, int z1) {
        this.type = type;
        this.posX1 = x1;
        this.posZ1 = z1;
        this.posX2 = x1 + type.getWidth();
        this.posZ2 = z1 + type.getDepth();
        this.height = type.getHeight();
    }

    // Getters
    public int getPosX1() {
        return posX1;
    }

    public int getPosX2() {
        return posX2;
    }

    public int getPosZ1() {
        return posZ1;
    }

    public int getPosZ2() {
        return posZ2;
    }

    public int getHeight() {
        return height;
    }

    public ObstacleType getType() {
        return type;
    }
}
