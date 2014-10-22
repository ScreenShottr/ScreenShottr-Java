package us.screenshottr.java.logging;

import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import us.screenshottr.java.ScreenShottr;

public class ShotLogger {

    private static Logger logger = null;

    public static Logger getLogger() {
        if (logger != null) {
            return logger;
        }

        final Formatter formatter = new ConsoleFormatter();

        logger = Logger.getLogger(ScreenShottr.class.getName());
        logger.setLevel(Level.INFO);
        logger.setUseParentHandlers(false);
        logger.addHandler(new ConsoleLoggerHandler(formatter));
        logger.addHandler(new ConsoleFileHandler(formatter));

        logger.info("Initialized logger");

        return logger;
    }
}
