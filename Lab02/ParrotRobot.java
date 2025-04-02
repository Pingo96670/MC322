package Lab02;/*
    A ideia é que ParrotRobot seja uma subclasse de robôs aéreos que conseguem armazenar
    falas(frases) e é capaz de reproduzí-las de forma randômica.
*/

import java.util.ArrayList;
import java.util.Random;

public class ParrotRobot extends AerialRobot{
    private final ArrayList<String> learnedPhrases;

    public ParrotRobot(String name, int startX, int startZ, int maxPosY){
        super(name, startX, startZ, maxPosY);
        this.setType("Parrot Bot");
        this.learnedPhrases = new ArrayList<>();
    }

    public void learnPhrase(String phrase){
        if (phrase.isEmpty()) {
            System.out.println("Invalid input. Must be a non-empty string.");
        } else {
            learnedPhrases.add(phrase);
        }
    }

    public void forgetPhrase(String phrase){
        learnedPhrases.remove(phrase);
    }

    public void speak(){
        if(learnedPhrases.isEmpty()) {
            System.out.printf("The Parrot Robot \"%s\" hasn't learned any phrases yet.\n", getName());
        } else {
            int index = new Random().nextInt(learnedPhrases.size());
            System.out.printf("The Parrot Robot \"%s\" says: \"%s\".\n", getName(), learnedPhrases.get(index));
        }
    }

    public ArrayList<String> getLearnedPhrases(){
        return learnedPhrases;
    }
}


