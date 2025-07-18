public interface Communicable {
    String getName();
    boolean isOn();
    void sendMessage(Communicable recipient, String msg);
    void sendMessage(String msg);
    void receiveMessage(String msg);
}
