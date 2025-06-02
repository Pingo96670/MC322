
import java.util.ArrayList;

public class CommunicationCenter{
    private final ArrayList<String> messages = new ArrayList<>();
    private final ArrayList<String> robots = new ArrayList<>();

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