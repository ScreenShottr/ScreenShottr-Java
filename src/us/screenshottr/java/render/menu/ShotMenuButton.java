package us.screenshottr.java.render.menu;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.JComponent;
import us.screenshottr.java.ShotUtil;
import us.screenshottr.java.render.ShotPainter;

public class ShotMenuButton extends JComponent {

    private static final long serialVersionUID = 1L;
    public static final int BUTTON_SIZE = 40;
    public static final int SPACING = 4;
    //
    private final int x, y;
    private final Image icon;
    private final ShotPainter painter;
    //
    private boolean mouseOver;
    private ShotRunnable onClick;

    public ShotMenuButton(ShotPainter painter, int x, int y, String iconResource) {
        this.x = x;
        this.y = y;
        this.icon = ShotUtil.getImageResource(iconResource);
        this.painter = painter;
    }

    @Override
    public void paintComponent(Graphics graphics) {
        final Graphics2D screen = (Graphics2D) graphics.create();

        try {
            if (intersects(painter.getMouseAdapter().getX(), painter.getMouseAdapter().getY())) {
                screen.setComposite(AlphaComposite.SrcOver.derive(0.6f));
                mouseOver = true;
            } else {
                screen.setComposite(AlphaComposite.SrcOver.derive(0.8f));
                mouseOver = false;
            }

            screen.fillRect(
                    x,
                    y,
                    BUTTON_SIZE,
                    BUTTON_SIZE);


            screen.setComposite(AlphaComposite.SrcOver);
            screen.drawImage(
                    icon,
                    x + SPACING,
                    y + SPACING,
                    BUTTON_SIZE - SPACING * 2,
                    BUTTON_SIZE - SPACING * 2,
                    null);
        } catch (Exception ex) {
            ShotUtil.handleError(ex);
        } finally {
            screen.dispose();
        }

    }

    public boolean intersects(int checkX, int checkY) {
        return checkX > x && checkX < x + BUTTON_SIZE
                && checkY > y && checkY < y + BUTTON_SIZE;
    }

    public boolean isMouseOver() {
        return mouseOver;
    }

    public void setMouseClickHandler(ShotRunnable onClick) {
        this.onClick = onClick;
    }

    public void mouseClicked() {
        if (onClick == null) {
            return;
        }

        onClick.run();
    }
}
