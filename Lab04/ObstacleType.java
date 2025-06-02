public enum ObstacleType {
    WATER(1, 1, 0, 2, 'W'),
    ROCK(0, 0, 1, 3, 'R'),
    MOUNTAIN(3, 3, 5, 4, 'M'),
    TREE(0, 0, 2, 5, 'T');

    private final int width; // Positions occupied on X axis after x1
    private final int depth; // Positions occupied on Z axis after z1
    private final int height; // Positions occupied on Y axis above ground(0)
    private final int code; // Used for identification of the obstacles in the obstacleMatrix
    private final char repChar; // Used for identifying the obstacle when printing

    ObstacleType(int width, int depth, int height, int code, char repChar) {
        this.width = width;
        this.depth = depth;
        this.height = height;
        this.code = code;
        this.repChar = repChar;
    }

    // Getters
    public int getWidth() {
        return width;
    }

    public int getDepth() {
        return depth;
    }

    public int getHeight() {
        return height;
    }

    public int getCode(){
        return code;
    }

    public char getRepChar() {
        return repChar;
    }
}
