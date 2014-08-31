package us.screenshottr.java.draw;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import us.screenshottr.java.ImageCapturer;
import us.screenshottr.java.ScreenShottr;
import us.screenshottr.java.Util;

public class ShotMouseAdapter extends MouseAdapter {

    private final Painter painter;
    private Point startPoint;
    private Point endPoint;

    public ShotMouseAdapter(Painter painter) {
        this.painter = painter;
        this.startPoint = null;
        this.endPoint = null;
    }

    @Override
    public void mousePressed(MouseEvent event) {
        if (event.getButton() == MouseEvent.BUTTON2) {
            painter.exit();
            return;
        }

        startPoint = event.getLocationOnScreen();
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        try {
            endPoint = event.getLocationOnScreen();

            painter.exit();

            if (Math.abs(endPoint.x - startPoint.x) < 15
                    && Math.abs(endPoint.y - startPoint.y) < 15) {
                ScreenShottr.LOGGER.warning("Cancelled: Image too small");
                return;
            }

            ImageCapturer.takeScreenShot(startPoint, endPoint);
        } catch (Exception ex) {
            Util.handleError(ex);
        }
    }

    public Point getStartPoint() {
        return startPoint;
    }

    public Point getEndPoint() {
        return endPoint;
    }
}
