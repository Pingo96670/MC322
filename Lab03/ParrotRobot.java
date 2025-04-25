/*
The ParrotRobot class is a subclass of AerialRobot capable of storing phrases and repeating them randomly.
 */

import java.util.ArrayList;
import java.util.Random;

public class ParrotRobot extends AerialRobot{
    private final ArrayList<String> learnedPhrases;     // List of phrases learned by the robot

    // ParrotRobot constructor
    public ParrotRobot(String name, int startX, int startZ, int maxPosY, double distanceRadius){
        super(name, startX, startZ, maxPosY, distanceRadius);
        this.setType("Parrot Bot");
        this.learnedPhrases = new ArrayList<>();
    }

    // Adds a phrase to learnedPhrases
    public void learnPhrase(String phrase){
        // Checks if the string is empty
        if (phrase.isEmpty()) {
            System.out.println("Invalid input. Must be a non-empty string.");
        // Successful operation
        } else {
            learnedPhrases.add(phrase);
        }
    }

    // Removes a phrase from learnedPhrases
    public void forgetPhrase(String phrase){
        learnedPhrases.remove(phrase);
    }

    // Randomly prints one of the learned phrases
    public void speak(){
        // Checks if the list is empty
        if(learnedPhrases.isEmpty()) {
            System.out.printf("The Parrot Robot \"%s\" hasn't learned any phrases yet.\n", getName());
        // Successful operation
        } else {
            int index = new Random().nextInt(learnedPhrases.size());
            System.out.printf("The Parrot Robot \"%s\" says: \"%s\".\n", getName(), learnedPhrases.get(index));
        }
    }

    // Getters
    public ArrayList<String> getLearnedPhrases(){
        return learnedPhrases;
    }
}


