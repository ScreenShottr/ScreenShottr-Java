package us.screenshottr.java.draw;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.swing.JOptionPane;
import us.screenshottr.java.ShotCapturer;
import us.screenshottr.java.ScreenShottr;
import us.screenshottr.java.ShotUploader;
import us.screenshottr.java.ShotUtil;

public class ShotMouseAdapter extends MouseAdapter {

    private final ShotPainter painter;
    private Point startPoint;
    private Point endPoint;

    public ShotMouseAdapter(ShotPainter painter) {
        this.painter = painter;
        this.startPoint = null;
        this.endPoint = null;
    }

    public Point getStartPoint() {
        return startPoint;
    }

    public Point getEndPoint() {
        return endPoint;
    }

    @Override
    public void mousePressed(MouseEvent event) {
        if (event.getButton() == MouseEvent.BUTTON2) {
            painter.getApp().stop(0);
            return;
        }

        startPoint = event.getLocationOnScreen();
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        try {
            endPoint = event.getLocationOnScreen();

            // Stop showing selection box
            painter.stop(0);

            final int x = Math.min(startPoint.x, endPoint.x);
            final int y = Math.min(startPoint.y, endPoint.y);
            final int width = Math.max(startPoint.x, endPoint.x) - x;
            final int height = Math.max(startPoint.y, endPoint.y) - y;

            if (height < 20 || width < 20) {
                ScreenShottr.LOGGER.info("Cancelled: Image too small");
                painter.getApp().stop(0);
            }

            final Rectangle screen = new Rectangle(x, y, width, height);
            final BufferedImage image = ShotCapturer.takeScreenShot(screen);
            final String url = ShotUploader.uploadScreenShot(image);

            if (url.equals(ShotUploader.INVALID_RESPONSE) || url.equals(ShotUploader.ERROR_RESPONSE)) {
                ShotUtil.showDialog("Error", "An error occurred whilst uploading the screenshot! Sorry!", JOptionPane.ERROR_MESSAGE);
                painter.getApp().stop(1);
            }

            ShotUtil.putClipboard(url);

            painter.getApp().stop(0);
        } catch (Exception ex) {
            ShotUtil.handleError(ex);
        }
    }
}
