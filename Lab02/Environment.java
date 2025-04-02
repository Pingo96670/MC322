package Lab02;

import java.util.ArrayList;

public class Environment {
    private final int sizeX;    // Environment's x dimension
    private final int sizeY;    // Environment's y dimension
    private final int sizeZ;    // Environment's z dimension
    private final ArrayList<BaseRobot> robotList = new ArrayList<>();
    private final int[][][] obstacleMatrix;

    // Environment constructor
    public Environment(int sizeX, int sizeY, int sizeZ) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.sizeZ = sizeZ;

        this.obstacleMatrix = new int[sizeX + 1][sizeY + 1][sizeZ + 1];
    }

    public void addRobot(BaseRobot bot) {
        robotList.add(bot);
    }

    public void printRobotList() {
        System.out.println("List of robots:");

        for (int i = 0; i < this.robotList.size(); i++) {
            robotList.get(i).printPos();
        }
    }

    // Method to check if a position is within environment bounds ([0, size])
    public boolean isWithinBounds(int posX, int posY, int posZ) {
        return posX <= sizeX && posY <= sizeY && posZ <= sizeZ &&
                posX >=0 && posY >= 0 && posZ >=0;
    }

    // Setters and getters
    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public int getSizeZ() {
        return sizeZ;
    }

    public ArrayList<BaseRobot> getRobotList() {
        return robotList;
    }

    public int[][][] getObstacleMatrix() {
        return obstacleMatrix;
    }
}