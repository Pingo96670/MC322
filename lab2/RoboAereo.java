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
        if((altitude-dA)>=0){
            altitude = altitude - dA;
            System.out.printf("Robo %s desceu para altitude %d metros.\n", pegaNome(), altitude);
        }
        else{
            altitude = 0;
            System.out.printf("Robo %s desceu para altitude 0 metros.\n", pegaNome());
        }
    }
}