package us.screenshottr.java.draw;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import us.screenshottr.java.ScreenShottr;

public class Painter implements Runnable {

    private JFrame parentFrame;
    private ShotPanel shotPanel;
    private Timer timer;

    public Painter() {
        // Parent
        parentFrame = new JFrame();

        // Shot
        shotPanel = new ShotPanel(this);

        // Repaint every 20ms
        timer = new Timer(20, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                parentFrame.repaint();
            }
        });
    }

    @Override
    public void run() { // Start app
        ScreenShottr.LOGGER.info("Initializing components");

        final Cursor cursor = Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR);

        // Frame
        parentFrame.setLayout(new FlowLayout());

        parentFrame.setUndecorated(true);
        parentFrame.setBackground(new Color(0, 0, 0, 0)); // Invisible
        parentFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        parentFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        parentFrame.setContentPane(shotPanel);
        parentFrame.setCursor(cursor);
        parentFrame.setAlwaysOnTop(true);

        // Start visibling...
        parentFrame.pack();
        parentFrame.setVisible(true);

        // Repaint timer
        timer.start();

        ScreenShottr.LOGGER.info("Cursor: " + cursor.getName());
    }

    public JFrame getParentFrame() {
        return parentFrame;
    }

    public void exit() {
        timer.stop();
        parentFrame.setVisible(false);
        parentFrame.dispose();
    }
}
