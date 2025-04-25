public class Obstacle {
    private final int posX1; 
    private final int posX2; 
    private final int posY1; 
    private final int posY2;
    private final int height;
    private final ObstacleType type;

    // Constructor
    public Obstacle(ObstacleType type, int x1, int y1) {
        this.type = type;
        this.posX1 = x1;
        this.posY1 = y1;
        this.posX2 = x1 + type.getWidth();
        this.posY2 = y1 + type.getDepth();
        this.height = type.getHeight();
    }

    // Getters
    public int getPosX1() {
        return posX1;
    }

    public int getPosX2() {
        return posX2;
    }

    public int getPosY1() {
        return posY1;
    }

    public int getPosY2() {
        return posY2;
    }

    public int getHeight() {
        return height;
    }

    public ObstacleType getType() {
        return type;
    }
}
