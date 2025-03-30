/*  
    A ideia é que ParrotRobot seja uma subclasse de robôs aéreos que conseguem armazenar
    falas(frases) e é capaz de reproduzí-las de forma randômica.
*/

import java.util.ArrayList;
import java.util.Random;

public class ParrotRobot extends AerialRobot{
    private ArrayList<String> learnedPhrases;

    public ParrotRobot(String name, int startX, int startZ, int maxPosY){
        super(name, startX, startZ, maxPosY);
        this.learnedPhrases = new ArrayList<>();
    }

    public ArrayList<String> getLearnedPhrases(){
        return learnedPhrases;
    }

    public void learnPhrase(String phrase){
        learnedPhrases.add(phrase);
    }

    public void forgetPhrase(String phrase){
        learnedPhrases.remove(phrase);
    }

    public void speak(){
        if(learnedPhrases.isEmpty()){
            System.out.printf("Parrot Robot %s hasn't learned any phrases yet.\n", getName());
        }
        else{
            int index = new Random().nextInt(learnedPhrases.size());
            System.out.printf("Parrot Robot %s says: %s\n", getName(), learnedPhrases.get(index));
        }
    }
}


