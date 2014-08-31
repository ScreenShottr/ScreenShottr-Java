package us.screenshottr.java.draw;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import us.screenshottr.java.ImageCapturer;
import us.screenshottr.java.ScreenShottr;

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
        }

        this.startPoint = event.getLocationOnScreen();
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        this.endPoint = event.getLocationOnScreen();
        painter.exit();
        ImageCapturer.takeScreenShot(startPoint, endPoint);
    }

    public Point getStartPoint() {
        return startPoint;
    }

    public Point getEndPoint() {
        return endPoint;
    }
}
