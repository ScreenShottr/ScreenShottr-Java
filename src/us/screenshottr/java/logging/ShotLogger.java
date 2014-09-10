package us.screenshottr.java.logging;

import java.util.logging.Level;
import java.util.logging.Logger;
import us.screenshottr.java.ScreenShottr;
import static us.screenshottr.java.ScreenShottr.LOGGER;

public class ShotLogger {

    private static Logger logger = null;

    public static Logger getLogger() {
        if (logger != null) {
            return logger;
        }

        logger = Logger.getLogger(ScreenShottr.class.getName());
        logger.setLevel(Level.INFO);
        logger.setUseParentHandlers(false);
        logger.addHandler(new ConsoleLoggerHandler());

        logger.info("Initialized logger");

        return logger;
    }
}
