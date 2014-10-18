package us.screenshottr.java.render.settings;

import us.screenshottr.java.render.ShotPainter;

public class ShotSettings {

    public static final float OPACITY_MAX = 0.98f;
    public static final float OPACITY_MIN = 0.06f;
    //private final ShotPainter painter;
    private final ShotSettingsFrame settingsFrame;

    public ShotSettings(ShotPainter painter) {
        //this.painter = painter;
        this.settingsFrame = new ShotSettingsFrame(painter);
        this.settingsFrame.setIconImage(ShotPainter.ICON);
    }

    public void showSettings() {
        settingsFrame.loadValues();
        settingsFrame.revalidate();
        settingsFrame.pack();
        settingsFrame.setVisible(true);
    }

    public void hideSettings() {
        settingsFrame.setVisible(false);
    }

    public boolean areSettingsVisible() {
        return settingsFrame.isVisible();
    }
}
