package us.screenshottr.java.render;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import us.screenshottr.java.ScreenShottr;

public class ShotKeyAdapter extends KeyAdapter {

    private final ShotPainter painter;

    public ShotKeyAdapter(ShotPainter painter) {
        this.painter = painter;
    }

    @Override
    public void keyPressed(KeyEvent event) {
        if (event.getKeyCode() != KeyEvent.VK_ESCAPE) {
            return;
        }

        ScreenShottr.LOGGER.info("Caught ESC; exiting...");
        painter.getApp().stop(0);
    }
}
