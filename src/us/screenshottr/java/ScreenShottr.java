package us.screenshottr.java;

import java.util.logging.Logger;
import us.screenshottr.java.draw.ShotPainter;
import javax.swing.SwingUtilities;
import us.screenshottr.java.api.IApplication;
import us.screenshottr.java.logging.ShotLogger;

public class ScreenShottr implements IApplication {

    public static final Logger LOGGER = ShotLogger.getLogger();
    public static final String NAME = "ScreenShottr";
    public static final String VERSION = "1.1";
    public static final ScreenShottr APP = new ScreenShottr();
    //
    private final ShotPainter painter;

    public static void main(String[] args) {
        APP.start();
    }

    public ScreenShottr() {
        this.painter = new ShotPainter(this);
    }

    @Override
    public void start() {
        LOGGER.info("Starting " + NAME + " v" + VERSION);

        // Verify translucency
        ShotUtil.verifyTranslucencySupported();

        // Run app
        SwingUtilities.invokeLater(painter);
    }

    @Override
    public void stop(int code) {
        LOGGER.info("Shutting down...");
        painter.stop(code);

        System.exit(code);
    }

    public ShotPainter getPainter() {
        return painter;
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public String getVersion() {
        return VERSION;
    }

    @Override
    public Logger getLogger() {
        return LOGGER;
    }
}
