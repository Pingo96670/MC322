package lab.entities.bots.interfaces;

// Related to robots which can adapt and change over the program`s execution
// Currently only implemented by the Parrot Bot type
public interface SelfLearner {
    void mimicMessage(); // Learn and speak a previous sent message
    void shufflePhrase(); // Learn a new phrase by shuffling a learned phrase
}