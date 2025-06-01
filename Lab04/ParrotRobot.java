/*
The ParrotRobot class is a subclass of AerialRobot capable of storing phrases and repeating them randomly.
 */

import java.util.ArrayList;
import java.util.Random;

public class ParrotRobot extends AerialRobot implements Communicable, SelfLearner {
    private final ArrayList<String> learnedPhrases;     // List of phrases learned by the robot

    // ParrotRobot constructor
    public ParrotRobot(String name, int startX, int startZ, int maxPosY, double distanceRadius){
        super(name, startX, startZ, maxPosY, distanceRadius);
        this.setType("Parrot Bot");
        this.learnedPhrases = new ArrayList<>();
    }

    // Adds a phrase to learnedPhrases
    public void learnPhrase(String phrase) {
        // Checks if the string is empty
        if (phrase.isEmpty()) {
            System.out.println("Invalid input. Must be a non-empty string.");
            System.out.println();
        // Successful operation
        } else {
            learnedPhrases.add(phrase);
            System.out.printf("Successfully learned phrase \"%s\".\n", phrase);
            System.out.println();
        }
    }

    // Removes a phrase from learnedPhrases
    public void forgetPhrase(String phrase) {
        int starting_num = learnedPhrases.size();

        learnedPhrases.remove(phrase);

        if (learnedPhrases.size()<starting_num) {
            System.out.printf("Successfully forgot phrase \"%s\".\n", phrase);
        } else {
            System.out.printf("Failed to remove phrase \"%s\". Please make sure you've typed the exact same phrase (case sensitive).\n", phrase);
        }

        System.out.println();
    }

    // Randomly prints one of the learned phrases
    public void speak() {
        // Checks if the list is empty
        if(learnedPhrases.isEmpty()) {
            System.out.printf("The Parrot Robot %s hasn't learned any phrases yet.%n", getName());
            System.out.println();
        // Successful operation
        } else {
            int index = new Random().nextInt(learnedPhrases.size());
            System.out.printf("The Parrot Robot %s says: \"%s\".%n", getName(), learnedPhrases.get(index));
            getEnvironment().getCenter().registerMessage(getName(), learnedPhrases.get(index));
            System.out.println();
        }
    }

    // Learn and speak a random previous sent message from the CommunicationCenter
    public void mimicMessage() {
        ArrayList<String> messages = getEnvironment().getCenter().getMessages();
        
        if(messages.isEmpty()) {
            System.out.printf("%s found no messages to mimic.%n", getName());
        }
        
        else {
            int index = new Random().nextInt(messages.size());
            
            if(!learnedPhrases.contains(messages.get(index))) {
                learnPhrase(messages.get(index));
            }
            
            System.out.printf("The Parrot Robot %s says: \"%s\".%n", getName(), messages.get(index));
            getEnvironment().getCenter().registerMessage(getName(), messages.get(index));
        }
    }

    // Learn a new phrase by shuffling a learned phrase
    public void shufflePhrase() {
        if (learnedPhrases.size() < 1) {
            System.out.printf("%s has not learned enough to create a new phrase.%n", getName());
            return;
        }

        Random rand = new Random();
        String basePhrase = learnedPhrases.get(rand.nextInt(learnedPhrases.size()));
        String[] words = basePhrase.split(" ");

        // Shuffle the words
        for (int i = words.length - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            String temp = words[i];
            words[i] = words[j];
            words[j] = temp;
        }

        String newPhrase = String.join(" ", words);
        
        if (!learnedPhrases.contains(newPhrase)) {
            learnPhrase(newPhrase);
        }
        
        System.out.printf("The Parrot Robot %s says: \"%s\"%n", getName(), newPhrase);
        getEnvironment().getCenter().registerMessage(getName(), newPhrase);
    }

    // Execute robot's specific task, which is to try to learn and speak new phrases
    public void specificTask() {
        shufflePhrase();
        mimicMessage();
    }


    // Says a message to another robot
    public void sendMessage(Communicable recipient, String msg) {
        if(isOn()) {
            System.out.printf("%s says: \"%s\" to %s.%n", getName(), msg, recipient.getName());
            getEnvironment().getCenter().registerMessage(getName(), msg);
            if(recipient.isOn()) {
                receiveMessage(msg);
            }
            else {
                System.out.printf("%s is OFF and cannot receive the message.%n", recipient.getName());
            }
        }
        else {
            System.out.printf("%s is OFF and cannot send the message.%n");
        }
    }

    // Receives a message from another robot
    public void receiveMessage(String msg) {
        System.out.printf("The message \"%s\" was received.%n", msg);
    }

    // Getters
    public ArrayList<String> getLearnedPhrases(){
        return learnedPhrases;
    }
}


