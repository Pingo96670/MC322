public class Robo {
    private String nome;
    private int posX;
    private int posY;
    private Ambiente ambiente;
    private String direcao;


    public Robo(String nome, Ambiente ambiente){
        this.nome = nome;
        this.posX = 0;
        this.posY = 0;
        this.ambiente = ambiente;
        this.direcao = "Norte"; //por padrão os robôs estarão direcionados ao norte ao serem inicializados
        ambiente.adRobo(nome);
    }

    public String pegaNome(){
        return nome;
    }

    public int pegaPosX(){
        return posX;
    }

    public int pegaPosY(){
        return posY;
    }

    public String pegaDirecao(){
        return direcao;
    }

    public Ambiente pegaAmbiente(){
        return ambiente;
    }

    void setDirecao(String direcao){
        this.direcao = direcao;
    }

    void setPosX(int x){
        posX = x;
    }

    void setPosY(int y){
        posY = y;
    }

    public void mover(int dX, int dY){
        if(ambiente.dentroDosLimites(posX+dX, posY+dY)){
            posX = posX + dX;
            posY = posY + dY;
            System.out.printf("Movendo robo %s para a posicao (%d, %d, 0)\n", nome, posX, posY);
        }
        else{
            System.out.printf("Posicao destino fora dos limites, o robo %s nao se moveu.\n", nome);
        }
    }

    public void exibirPosicao(){
        System.out.printf("Robo %s na posicao (%d, %d)\n", nome, posX, posY);
    }

}
