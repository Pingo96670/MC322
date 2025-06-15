import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Log {
    private static final String log = "log.txt";

    public static void register(String text) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(log, true))) {
            bw.write(text);
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
