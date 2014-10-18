package us.screenshottr.java.render;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import us.screenshottr.java.ScreenShottr;
import us.screenshottr.java.ShotUtil;
import us.screenshottr.java.api.IStartStoppable;
import us.screenshottr.java.render.settings.ShotSettings;

public class ShotPainter implements Runnable, IStartStoppable {

    public static final Cursor DEFAULT_CURSOR = Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR);
    public static final Cursor HOVER_CURSOR = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
    public static final Image ICON = ShotUtil.getImageResource("screenshottr.png");
    //
    private final ScreenShottr app;
    private final JFrame parentFrame;
    private final ShotMouseAdapter mouseAdapter;
    private final ShotFullscreenPanel contentPanel;
    private final ShotRepaintTimer timer;
    private final ShotSettings settings;

    public ShotPainter(ScreenShottr app) {
        this.app = app;
        this.parentFrame = new JFrame();
        this.mouseAdapter = new ShotMouseAdapter(this);
        this.settings = new ShotSettings(this);
        this.timer = new ShotRepaintTimer(this);
        this.contentPanel = new ShotFullscreenPanel(this);
    }

    @Override
    public void run() { // Start app
        ScreenShottr.LOGGER.info("Initializing components...");
        ScreenShottr.LOGGER.info("Cursor: " + DEFAULT_CURSOR.getName());

        // Frame
        parentFrame.setIconImage(ICON);
        parentFrame.setContentPane(contentPanel);
        parentFrame.setLayout(new BorderLayout());
        parentFrame.setUndecorated(true);
        parentFrame.setBackground(new Color(0, 0, 0, 0));
        parentFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        parentFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        parentFrame.setAlwaysOnTop(true);
        parentFrame.setCursor(DEFAULT_CURSOR);

        // Mouse adapter
        parentFrame.addMouseListener(mouseAdapter);
        parentFrame.addMouseMotionListener(mouseAdapter);

        // Start showing the frame
        parentFrame.pack();
        parentFrame.setVisible(true);

        // Start repainting
        timer.start();
        ScreenShottr.LOGGER.info("Started repaint timer");
    }

    @Override
    public void start() {
        SwingUtilities.invokeLater(this);
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

    public JFrame getParentFrame() {
        return parentFrame;
    }

    public ShotFullscreenPanel getContentPanel() {
        return contentPanel;
    }

    public ShotMouseAdapter getMouseAdapter() {
        return mouseAdapter;
    }

    public ShotSettings getSettings() {
        return settings;
    }
}
