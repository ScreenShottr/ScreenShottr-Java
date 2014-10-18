package us.screenshottr.java;

import java.awt.AWTException;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.net.URL;
import org.json.simple.JSONObject;
import us.screenshottr.java.api.IConfiguration;
import us.screenshottr.java.config.ConfigKey;
import us.screenshottr.java.render.ShotPainter;

public class ShotCreator {

    public static void handleScreenShot(ShotPainter painter, Point pointA, Point pointB) {
        ScreenShottr.LOGGER.info("Handling ScreenShot: (" + pointA.x + "," + pointA.y + "), (" + pointB.x + "," + pointB.y + ")");

        final int x = Math.min(pointA.x, pointB.x);
        final int y = Math.min(pointA.y, pointB.y);
        final int width = Math.max(pointA.x, pointB.x) - x;
        final int height = Math.max(pointA.y, pointB.y) - y;
        final IConfiguration config = painter.getApp().getConfig();

        if (height < 20 || width < 20) {
            ScreenShottr.LOGGER.info("Cancelled: Image too small");
            painter.getApp().stop(0);
        }

        final Rectangle screen = new Rectangle(x, y, width, height);
        final BufferedImage image = takeScreenShot(screen);

        final URL url = ShotURL.builder().withConfig(painter.getApp().getConfig()).build();
        ScreenShottr.LOGGER.info("Post URL: " + url);

        final JSONObject response = ShotUploader.uploadScreenShot(image, url);
        if (response == null) {
            return;
        }

        if (!response.containsKey("URL")
                || !response.containsKey("landingURL")) {
            ScreenShottr.LOGGER.severe("Response does not include required JSON keys!");
            return;
        }

        String responseUrlString = response.get("URL").toString();
        if (config.getBoolean(ConfigKey.URL_LANDING_PAGE)) {
            responseUrlString = response.get("landingURL").toString();
        }

        ScreenShottr.LOGGER.info("Response URL: " + responseUrlString);

        URL responseUrl = null;
        try {
            responseUrl = new URL(responseUrlString);
        } catch (Exception ex) {
            ScreenShottr.LOGGER.severe("Server returned invalid URL!");
            ShotUtil.handleError(ex);
        }

        if (painter.getApp().getConfig().getBoolean(ConfigKey.COPY_TO_CLIBOARD)) {
            ShotUtil.putClipboard(responseUrlString);
        }

        if (config.getBoolean(ConfigKey.OPEN_IN_BROWSER)) {
            ShotUtil.openWebpage(responseUrl);
        }

        painter.getApp().stop(0);
    }

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
