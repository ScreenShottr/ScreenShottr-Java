package us.screenshottr.java.draw;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.MultipleGradientPaint.CycleMethod;
import java.awt.Paint;
import java.awt.Point;
import java.awt.RadialGradientPaint;
import java.awt.Toolkit;
import java.awt.geom.Point2D;
import javax.swing.JPanel;

public class ShotPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    //
    private Painter painter;
    private ShotMouseAdapter mouseAdapter;

    public ShotPanel(Painter painter) {
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

        screen.setComposite(AlphaComposite.Src.derive(0.002f));
        screen.fillRect(0, 0, getPreferredSize().width, getPreferredSize().height);

        // Get points
        final Point startPoint = mouseAdapter.getStartPoint();
        if (startPoint == null) {
            return;
        }
        final Point mousePoint = MouseInfo.getPointerInfo().getLocation();

        final int dimX = mousePoint.x - startPoint.x;
        final int dimY = mousePoint.y - startPoint.y;

        // Draw selection
        screen.setComposite(AlphaComposite.SrcOver.derive(0.3f));
        screen.fillRect(startPoint.x, startPoint.y, dimX, dimY);

        // Draw dimensions
        screen.setColor(Color.WHITE);
        screen.setComposite(AlphaComposite.Src);
        screen.drawString(String.valueOf(dimY), mousePoint.x - 40, mousePoint.y - 26);
        screen.drawString(String.valueOf(dimX), mousePoint.x - 40, mousePoint.y - 14);

        screen.dispose();
    }
}
