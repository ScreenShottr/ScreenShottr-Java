package us.screenshottr.java.render;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Toolkit;
import javax.swing.JPanel;
import us.screenshottr.java.config.ConfigKey;
import us.screenshottr.java.render.menu.ShotMenu;

public class ShotFullscreenPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    //
    private final ShotPainter painter;
    private final ShotMenu menuBar;

    public ShotFullscreenPanel(ShotPainter painter) {
        this.painter = painter;

        // Set size
        final Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        super.setMinimumSize(size);
        super.setPreferredSize(size);

        // Menu
        this.menuBar = new ShotMenu(painter);
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        final Graphics2D screen = (Graphics2D) graphics.create();

        if (painter.getSettings().areSettingsVisible()) {
            screen.setComposite(AlphaComposite.Src.derive(0.4f));
            screen.fillRect(0, 0, getSize().width, getSize().height);
            screen.dispose();
            return;
        }

        //TODO: Fix this
        // Hacky: Draw a light (nearly invisible) full screen composite to keep the screen full-size
        screen.setComposite(AlphaComposite.Src.derive(0.002f));
        screen.fillRect(0, 0, getSize().width, getSize().height);

        // Get points
        final Point startPoint = painter.getMouseAdapter().getStartPoint();
        final Point mousePoint = MouseInfo.getPointerInfo().getLocation();

        menuBar.paintComponent(graphics);

        if (startPoint == null) { // Can't draw yet
            screen.dispose();
            return;
        }

        // Draw selection
        final int x = Math.min(startPoint.x, mousePoint.x);
        final int y = Math.min(startPoint.y, mousePoint.y);
        final int width = Math.max(startPoint.x, mousePoint.x) - x;
        final int height = Math.max(startPoint.y, mousePoint.y) - y;

        // Draw selection
        screen.setComposite(AlphaComposite.SrcOver.derive(painter.getApp().getConfig().getFloat(ConfigKey.OPACITY)));
        screen.fillRect(x, y, width, height);

        // Reset
        screen.setColor(Color.WHITE);
        screen.setComposite(AlphaComposite.SrcOver);

        // Draw dimensions
        if (width > 40 && height > 40) {
            int drawX = x + width - 33;
            int drawY = y + height - 25;
            int newLine = 12;
            screen.drawString(String.valueOf(width), drawX, drawY);
            screen.drawString(String.valueOf(height), drawX, drawY + newLine);
        }

        screen.dispose();
    }

    public ShotPainter getPainter() {
        return painter;
    }

    public ShotMenu getMenuBar() {
        return menuBar;
    }
}
