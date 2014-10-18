package us.screenshottr.java;

import java.io.File;
import java.util.logging.Logger;
import javax.swing.UIManager;
import us.screenshottr.java.api.IApplication;
import us.screenshottr.java.api.IConfiguration;
import us.screenshottr.java.config.Configuration;
import us.screenshottr.java.logging.ShotLogger;
import us.screenshottr.java.render.ShotPainter;

public class ScreenShottr implements IApplication {

    public static final Logger LOGGER = ShotLogger.getLogger();
    public static final String NAME = "ScreenShottr";
    public static final String VERSION = "2.0";
    public static final ScreenShottr APP = new ScreenShottr();
    //
    private final File appFolder;
    private final IConfiguration config;
    private final ShotPainter painter;

    public static void main(String[] args) {
        APP.start();
    }

    public ScreenShottr() {
        this.appFolder = ShotUtil.determineAppFolder();
        this.config = new Configuration(new File(appFolder, "config.properties"));
        this.painter = new ShotPainter(this);
    }

    @Override
    public void start() {
        LOGGER.info("Starting " + NAME + " v" + VERSION);
        LOGGER.info("Application folder: " + appFolder.getAbsolutePath());

        // Verify translucency
        ShotUtil.verifyTranslucencySupported();

        // LNF
        ShotUtil.setLookAndFeel();
        ScreenShottr.LOGGER.info("LNF: " + UIManager.getLookAndFeel().getName());

        // Config
        config.load();

        // Run app
        painter.start();
    }

    @Override
    public void stop(int code) {
        LOGGER.info("Shutting down...");

        painter.stop(code);

        //config.save();

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

    @Override
    public File getAppFolder() {
        return appFolder;
    }

    @Override
    public IConfiguration getConfig() {
        return config;
    }
}
