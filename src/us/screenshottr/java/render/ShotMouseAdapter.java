package us.screenshottr.java.render;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import us.screenshottr.java.ShotCreator;
import us.screenshottr.java.ShotUtil;
import us.screenshottr.java.api.IMouseAdapter;
import us.screenshottr.java.render.menu.ShotMenuButton;

public class ShotMouseAdapter extends MouseAdapter implements IMouseAdapter {

    private final ShotPainter painter;
    private int mouseX, mouseY;
    private Point startPoint;
    private Point endPoint;

    public ShotMouseAdapter(ShotPainter painter) {
        this.painter = painter;
        this.startPoint = null;
        this.endPoint = null;
    }

    @Override
    public int getX() {
        return mouseX;
    }

    @Override
    public int getY() {
        return mouseY;
    }

    @Override
    public Point getStartPoint() {
        return startPoint;
    }

    @Override
    public Point getEndPoint() {
        return endPoint;
    }

    @Override
    public void mouseMoved(MouseEvent event) {
        this.mouseX = event.getX();
        this.mouseY = event.getY();
    }

    @Override
    public void mousePressed(MouseEvent event) {
        event.consume();

        if (event.getButton() == MouseEvent.BUTTON3) {
            startPoint = null;
            return;
        }

        if (event.getButton() != MouseEvent.BUTTON1) {
            return;
        }

        if (painter.getContentPanel().getMenuBar().isVisible()) {
            for (ShotMenuButton button : painter.getContentPanel().getMenuBar().getButtons()) {
                if (button.isMouseOver()) {
                    button.mouseClicked();
                    return;
                }
            }
        }

        startPoint = event.getLocationOnScreen();
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        if (startPoint == null) {
            return;
        }

        try {
            endPoint = event.getLocationOnScreen();

            // Stop showing selection box
            painter.stop(0);

            ShotCreator.handleScreenShot(painter, startPoint, endPoint);
        } catch (Exception ex) {
            ShotUtil.handleError(ex);
        }
    }
}
