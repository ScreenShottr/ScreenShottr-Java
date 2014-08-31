package us.screenshottr.java;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import static us.screenshottr.java.ScreenShottr.LOGGER;

public class ImageCapturer {

    public static void takeScreenShot(Point pointA, Point pointB) {
        try {
            ScreenShottr.LOGGER.info("Making screenshot...");

            final int x = Math.min(pointA.x, pointB.x);
            final int y = Math.min(pointA.y, pointB.y);
            final int width = Math.max(pointA.x, pointB.x) - x;
            final int height = Math.max(pointA.y, pointB.y) - y;

            final Robot robot = new Robot();
            final Rectangle screen = new Rectangle(
                    x,
                    y,
                    width,
                    height);

            final BufferedImage screenshot = robot.createScreenCapture(screen);

            final File file = new File("screenshot.png");
            LOGGER.info("Saving to " + file.getAbsolutePath());
            ImageIO.write(screenshot, "png", file);


        } catch (AWTException | IOException | RuntimeException ex) {
            Util.handleError(ex);
        }
    }
}
