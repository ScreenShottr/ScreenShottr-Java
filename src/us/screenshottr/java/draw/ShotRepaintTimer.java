package us.screenshottr.java.draw;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.Timer;

public class ShotRepaintTimer extends Timer {

    private static final long serialVersionUID = 1L;

    public ShotRepaintTimer(final JFrame parentFrame) {
        super(20, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                parentFrame.repaint();
            }
        });
    }
}
