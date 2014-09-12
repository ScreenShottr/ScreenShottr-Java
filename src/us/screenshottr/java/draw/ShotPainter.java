package us.screenshottr.java.draw;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.Timer;
import us.screenshottr.java.ScreenShottr;
import us.screenshottr.java.api.IStoppable;

public class ShotPainter implements Runnable, IStoppable {

    private final ScreenShottr app;
    private final Image icon;
    private final JFrame parentFrame;
    private final ShotPanel shotPanel;
    private final Timer timer;
    private final Cursor cursor;

    public ShotPainter(ScreenShottr app) {
        this.app = app;
        this.icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/resources/screenshottr.png"));
        this.parentFrame = new JFrame();
        this.shotPanel = new ShotPanel(this);
        this.timer = new ShotRepaintTimer(parentFrame);
        this.cursor = Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR);
    }

    @Override
    public void run() { // Start app
        ScreenShottr.LOGGER.info("Initializing components...");
        ScreenShottr.LOGGER.info("Cursor: " + cursor.getName());

        // Frame
        parentFrame.setIconImage(icon);
        parentFrame.setContentPane(shotPanel);
        parentFrame.setLayout(new BorderLayout());
        parentFrame.setUndecorated(true);
        parentFrame.setBackground(new Color(0, 0, 0, 0));
        parentFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        parentFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        parentFrame.setAlwaysOnTop(true);
        parentFrame.setCursor(cursor);

        // Start showing the frame
        parentFrame.pack();
        parentFrame.setVisible(true);

        // Start painting on the screen
        timer.start();
        ScreenShottr.LOGGER.info("Started repaint timer");
    }

    @Override
    public void stop(int code) {
        timer.stop();
        parentFrame.setVisible(false);
        parentFrame.dispose();
    }

    public ScreenShottr getApp() {
        return app;
    }
}
