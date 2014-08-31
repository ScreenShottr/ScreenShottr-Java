package us.screenshottr.java;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import static us.screenshottr.java.ScreenShottr.LOGGER;

public class ImageCapturer {

    public static void takeScreenShot(Point startPoint, Point endPoint) {
        try {
            final BufferedImage screenshot = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize())).getSubimage(
                    startPoint.x,
                    startPoint.y,
                    endPoint.x - startPoint.x,
                    endPoint.y - startPoint.y);

            final File file = new File("screenshot.png");
            LOGGER.info("Saving to " + file.getAbsolutePath());
            ImageIO.write(screenshot, "png", file);


        } catch (Exception ex) {
            Util.handleError(ex);
        }
    }
}
