
import java.util.ArrayList;

public class CommunicationCenter{
    private ArrayList<String> messages = new ArrayList<>(); // Recorded messages
    private ArrayList<String> robots = new ArrayList<>(); // For eache message in messages, the corresponding sender is stored at the same index in robots

    public CommunicationCenter() {
        super();
    }

    public void registerMessage(String robotName, String msg) {
        robots.add(robotName);
        messages.add(msg);
    }

    public void showMessages(){
        if(messages.isEmpty()) {
            System.out.println("No messages have been recorded yet.");
        }
        else {
            for(int i=0; i<messages.size(); i++) {
                System.out.printf("%s said: \"%s\"%n", robots.get(i), messages.get(i));
            }
        }
    }

    // Getters
    public ArrayList<String> getMessages() {
        return messages;
    }

    public ArrayList<String> getRobots() {
        return robots;
    }
}