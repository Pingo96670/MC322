import java.util.ArrayList;

public class Ambiente {
    private int largura;
    private int altura;
    private ArrayList<String> listaDeRobos;

    public Ambiente (int largura, int altura){
        this.largura = largura;
        this.altura = altura;
        this.listaDeRobos = new ArrayList<>();
    }

    public void adRobo(String nomeRobo){
        listaDeRobos.add(nomeRobo);
    }

    public ArrayList<String> pegaListaDeRobos(){
        return listaDeRobos;
    }

    public void imprimeListaDeRobos(){
        System.out.println("Lista de robos do ambiente:");
        for(String nome : listaDeRobos){
            System.out.println(nome);
        }
    }

    public boolean dentroDosLimites(int x, int y){
        return (x>=0 && x<= largura && y>=0 && y<= altura);
    }
}
