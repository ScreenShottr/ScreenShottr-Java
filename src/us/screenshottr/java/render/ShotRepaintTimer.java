package us.screenshottr.java.render;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class ShotRepaintTimer extends Timer {

    private static final long serialVersionUID = 1L;
    public static int FPS = 20;

    public ShotRepaintTimer(final ShotPainter painter) {
        super(FPS, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                painter.getParentFrame().repaint();
            }
        });
    }
}
