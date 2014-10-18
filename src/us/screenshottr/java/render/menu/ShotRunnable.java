package us.screenshottr.java.render.menu;

import us.screenshottr.java.render.ShotPainter;

public abstract class ShotRunnable implements Runnable {

    protected final ShotPainter painter;

    public ShotRunnable(ShotPainter painter) {
        this.painter = painter;
    }
}
