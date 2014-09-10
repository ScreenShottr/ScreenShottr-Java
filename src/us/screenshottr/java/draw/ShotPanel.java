package us.screenshottr.java.draw;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Toolkit;
import javax.swing.JPanel;

public class ShotPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    //
    private ShotPainter painter;
    private ShotMouseAdapter mouseAdapter;

    public ShotPanel(ShotPainter painter) {
        final Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        super.setMinimumSize(size);
        super.setMaximumSize(size);
        super.setPreferredSize(size);

        this.painter = painter;
        this.mouseAdapter = new ShotMouseAdapter(painter);
        super.addMouseListener(mouseAdapter);
    }

    public ShotMouseAdapter getMouseAdapter() {
        return mouseAdapter;
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        final Graphics2D screen = (Graphics2D) graphics.create();

        // TODO: Fix this
        // Hacky: Draw a light (nearly invisible) full screen composite to keep the screen full-size
        screen.setComposite(AlphaComposite.Src.derive(0.002f));
        screen.fillRect(0, 0, getPreferredSize().width, getPreferredSize().height);

        // Get points
        final Point startPoint = mouseAdapter.getStartPoint();
        final Point mousePoint = MouseInfo.getPointerInfo().getLocation();
        if (startPoint == null) {
            return;
        }

        final int x = Math.min(startPoint.x, mousePoint.x);
        final int y = Math.min(startPoint.y, mousePoint.y);
        final int width = Math.max(startPoint.x, mousePoint.x) - x;
        final int height = Math.max(startPoint.y, mousePoint.y) - y;

        // Draw selection
        screen.setComposite(AlphaComposite.SrcOver.derive(0.3f));
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
}
