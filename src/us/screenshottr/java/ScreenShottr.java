package us.screenshottr.java;

import java.util.logging.Level;
import java.util.logging.Logger;
import us.screenshottr.java.draw.Painter;
import javax.swing.SwingUtilities;

public class ScreenShottr {

    public static final String VERSION = "1.0";
    public static final Logger LOGGER = Logger.getLogger(ScreenShottr.class.getName());

    static {
        LOGGER.setLevel(Level.INFO);
        LOGGER.setUseParentHandlers(false);
        LOGGER.addHandler(new ConsoleLoggerHandler());
    }

    public static void main(String[] args) {

        LOGGER.info("Starting ScreenShottr");

        // Verify translucency
        Util.verifyTranslucencySupported();

        // Run app
        SwingUtilities.invokeLater(new Painter());
    }
}
