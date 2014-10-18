package us.screenshottr.java.render;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class ShotRepaintTimer extends Timer {

    private static final long serialVersionUID = 1L;

    public ShotRepaintTimer(final ShotPainter painter) {
        super(20, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                painter.getParentFrame().repaint();
            }
        });
    }
}
