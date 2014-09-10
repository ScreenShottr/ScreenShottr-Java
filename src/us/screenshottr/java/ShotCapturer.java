package us.screenshottr.java;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;

public class ShotCapturer {

    public static BufferedImage takeScreenShot(Rectangle screen) {
        try {
            ScreenShottr.LOGGER.info("Making screenshot...");

            return new Robot().createScreenCapture(screen);

        } catch (AWTException | RuntimeException ex) {
            ShotUtil.handleError(ex);
        }
        return null;
    }
}
