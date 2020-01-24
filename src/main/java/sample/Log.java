package sample;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Log {

    private static  FileHandler logFile;

    public static void setup() throws IOException{

        Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

        logger.setLevel(Level.INFO);
        logFile = new FileHandler("Logging.txt");
        logger.addHandler(logFile);
    }
}
