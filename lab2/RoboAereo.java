public class RoboAereo extends Robo{
    private int altitude;
    private int altitudeMax;

    public int pegaAltitude(){
        return altitude;
    }

    public int pegaAltitudeMax(){
        return altitudeMax;
    }

    public RoboAereo(String nome, Ambiente ambiente, int altitudeMax){
        super(nome, ambiente);
        this.altitudeMax = altitudeMax;
        this.altitude = 0;
    }

    void subir(int dA){
        if((altitude + dA)<=altitudeMax){
            altitude = altitude + dA;
            System.out.printf("Robo %s subiu para altitude %d metros.\n", pegaNome(), altitude);
        }
        else{
            System.out.printf("Altitude nao suportada pelo robo %s, a altitude nao foi alterada.\n", pegaNome());
        }
    }

    void descer(int dA){
        altitude = altitude - dA;
        if(altitude<0){ 
            altitude = 0;
        }
        System.out.printf("Robo %s desceu para altitude %d metros.\n", pegaNome(), altitude);
    }

    public void exibirPosicao(){
        System.out.printf("Robo %s na posicao (%d, %d, %d)\n", pegaNome(), pegaPosX(), pegaPosY(), altitude);
    }

    public void mover(int dX, int dY, int dA){
        if((pegaAmbiente()).dentroDosLimites(pegaPosX()+dX, pegaPosY()+dY, altitude+dA)){
            setPosX(pegaPosX()+dX);
            setPosY(pegaPosY()+dY);
            altitude = altitude + dA;
            System.out.printf("Movendo robo %s para a posicao (%d, %d, 0)\n", pegaNome(), pegaPosX(), pegaPosY());
        }
        else{
            System.out.printf("Posicao destino fora dos limites, o robo %s nao se moveu.\n", pegaNome());
        }
    }
}