package us.screenshottr.java.render;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import us.screenshottr.java.ScreenShottr;
import us.screenshottr.java.ShotUtil;
import us.screenshottr.java.api.IMouseAdapter;
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
    private final ShotKeyAdapter keyAdapter;
    private final ShotFullscreenPanel contentPanel;
    private final Timer timer;
    private final ShotSettings settings;

    public ShotPainter(ScreenShottr app) {
        this.app = app;
        this.parentFrame = new JFrame();
        this.mouseAdapter = new ShotMouseAdapter(this);
        this.keyAdapter = new ShotKeyAdapter(this);
        this.settings = new ShotSettings(this);
        this.timer = new ShotRepaintTimer(this);
        this.contentPanel = new ShotFullscreenPanel(this);
    }

    @Override
    public void run() { // Start app
        ScreenShottr.LOGGER.info("Initializing components...");
        ScreenShottr.LOGGER.info("Cursor: " + DEFAULT_CURSOR.getName());

        final Dimension size = Toolkit.getDefaultToolkit().getScreenSize();

        parentFrame.setUndecorated(true);
        //
        parentFrame.setLocation(0, 0);
        parentFrame.setSize(size);
        parentFrame.setMinimumSize(size);
        parentFrame.setLayout(null);
        parentFrame.setBackground(new Color(0, 0, 0, 0.002f)); //TODO: Hacky hacky
        //
        parentFrame.setIconImage(ICON);
        parentFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        parentFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        parentFrame.setAlwaysOnTop(true);
        parentFrame.setCursor(DEFAULT_CURSOR);
        parentFrame.setLocationByPlatform(true);
        parentFrame.setResizable(false);
        parentFrame.addMouseListener(mouseAdapter);
        parentFrame.addMouseMotionListener(mouseAdapter);
        parentFrame.addKeyListener(keyAdapter);

        // Content panel
        contentPanel.setLocation(0, 0);
        contentPanel.setSize(size);
        contentPanel.setMinimumSize(size);
        contentPanel.setLayout(null);
        contentPanel.setBackground(new Color(0, 0, 0, 0));
        contentPanel.repaint();

        // Start showing the frame
        parentFrame.add(contentPanel);
        parentFrame.pack();
        parentFrame.setVisible(true);

        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice dev = env.getDefaultScreenDevice();
        //dev.setFullScreenWindow(parentFrame); // Untested stuff

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

    public IMouseAdapter getMouseAdapter() {
        return mouseAdapter;
    }

    public ShotSettings getSettings() {
        return settings;
    }
}
