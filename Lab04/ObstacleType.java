public enum ObstacleType {
    WATER(1, 1, 0, 2),
    ROCK(0, 0, 1, 3),
    MOUNTAIN(3, 3, 5, 4),
    TREE(0, 0, 2, 5);

    private final int width; // positions occupied on X axis after x1
    private final int depth; // // positions occupied on Z axis after y1
    private final int height; // positions occupied on Y axis above ground(0)
    private final int code; // used for identification of the obstacles in the obstacleMatrix

    ObstacleType(int width, int depth, int height, int code) {
        this.width = width;
        this.depth = depth;
        this.height = height;
        this.code = code;
    }
    
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
}
