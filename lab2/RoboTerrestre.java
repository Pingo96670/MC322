public class RoboTerrestre extends Robo{
   private int velMax;

   public int pegaVelMax(){
    return velMax;
   }

   public RoboTerrestre(String nome, Ambiente ambiente, int velMax){
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

   public void exibirPosicao(){
      System.out.printf("Robo %s na posicao (%d, %d, 0)\n", pegaNome(), pegaPosX(), pegaPosY());
  }
}