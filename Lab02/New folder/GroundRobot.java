public class GroundRobot extends BaseRobot{
   private int maxSpeed;

   public GroundRobot(String nome, Ambiente ambiente, int velMax){
    super(nome, ambiente);
    this.velMax = velMax;
   }

   public void mover(int dX, int dY, int vel){
      if(vel <= velMax){
         super.mover(dX, dY);
      }
      else{
         System.out.printf("Velocidade acima da suportada pelo robo, posicao nao foi alterada.\n");
      }    
   }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }
}