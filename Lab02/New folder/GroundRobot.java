public class GroundRobot extends BaseRobot{
   private int maxSpeed;

   public GroundRobot(String name, int posX, int posZ, int maxSpeed){
    super(name, posX, posZ);
    this.maxSpeed = maxSpeed;
    super.setType("Ground Robot");
   }

   public void move(int dX, int dY, int spd){
      if(spd <= maxSpeed){
         super.move(dX, dY);
      }
      else{
         System.out.printf("Speed exceeds the robot's limit, position wasn't changed.\n");
      }    
   }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }
}