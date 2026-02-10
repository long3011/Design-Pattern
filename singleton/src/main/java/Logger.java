import java.io.*;

public class Logger {
    private static String fileName="log.txt";
    private static FileWriter fileWriter;
    private static Logger instance;

    private Logger() {
        // Private constructor to prevent instantiation
    }

    public void write(String message){
        try{
            fileWriter.write(message + "\n");
        } catch (IOException e) {
            System.out.println("Error writing to log file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void setFileName(String fileName) {
        Logger.fileName = fileName;
        try {
            fileWriter = new FileWriter(fileName, true); // Open in append mode
        } catch (IOException e) {
            System.out.println("Error initializing log file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void close() {
        if (fileWriter != null) {
            try {
                fileWriter.close();
            } catch (IOException e) {
                System.out.println("Error closing log file: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }
}
