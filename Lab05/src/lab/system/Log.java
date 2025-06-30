package lab.system;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

public class Log {
    // Private constructor for util class
    private Log() {}

    // Deletes a log file if it exists
    public static void resetLogFile(String filePath) {
        File f = new File(filePath);

        if (f.exists() && !f.isDirectory()) {
            f.delete();
        }

        try {
            f.createNewFile();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    // Writes to a log file
    public static void register(String filePath, String text) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))) {
            bw.write(text);
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
