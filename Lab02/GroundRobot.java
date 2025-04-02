package Lab02;

public class GroundRobot extends BaseRobot {
   private final int maxSpeed;

   public GroundRobot(String name, int posX, int posZ, int maxSpeed) {
       super(name, posX, posZ);
       this.maxSpeed = maxSpeed;
       super.setType("Ground Robot");
   }

   public void move(int dX, int dZ) {
       int totalDist = Math.abs(dX) + Math.abs(dZ);

       if (totalDist <= maxSpeed) {
           super.move(dX, dZ);
       } else {
           System.out.printf("Movement speed of %d exceeds the robot's speed limit of %d. Position unchanged.\n",
                   totalDist, this.maxSpeed);
       }
   }

    public int getMaxSpeed() {
       return maxSpeed;
    }
}