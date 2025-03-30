import java.util.ArrayList;

public class Environment {
    private final int sizeX;    // Environment's x dimension
    private final int sizeY;    // Environment's y dimension
    private final int sizeZ;    // Environment's y dimension
    private ArrayList<BaseRobot> robotList = new ArrayList<BaseRobot>();

    // Environment constructor
    public Environment(int sizeX, int sizeY, int sizeZ) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.sizeZ = sizeZ;
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

    public void setRobotList(ArrayList<BaseRobot> robotList) {
        this.robotList = robotList;
    }
}