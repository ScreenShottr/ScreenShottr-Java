package us.screenshottr.java.render.menu;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.JFrame;
import us.screenshottr.java.ShotCreator;
import us.screenshottr.java.render.ShotMouseAdapter;
import us.screenshottr.java.render.ShotPainter;

public class ShotMenu extends JComponent {

    private static final long serialVersionUID = 1L;
    public static final int BUTTON_SIZE = ShotMenuButton.BUTTON_SIZE;
    public static final int SPACING = ShotMenuButton.SPACING;
    //
    private final ShotPainter painter;
    private final JFrame parentFrame;
    private final ShotMouseAdapter mouseAdapter;
    private final List<ShotMenuButton> buttons;
    private final Rectangle background;

    public ShotMenu(ShotPainter painter) {
        this.painter = painter;
        this.parentFrame = painter.getParentFrame();
        this.mouseAdapter = painter.getMouseAdapter();
        this.buttons = new ArrayList<>();
        this.background = new Rectangle();
    }

    private void initComponents() {
        // Background
        background.setBounds(parentFrame.getWidth() - BUTTON_SIZE - SPACING,
                parentFrame.getHeight() / 2 - BUTTON_SIZE / 2 - SPACING - BUTTON_SIZE - SPACING,
                BUTTON_SIZE + SPACING,
                SPACING + BUTTON_SIZE + SPACING + BUTTON_SIZE + SPACING + BUTTON_SIZE + SPACING);

        // Screencap button
        final ShotMenuButton screencapButton = new ShotMenuButton(
                painter,
                parentFrame.getWidth() - BUTTON_SIZE,
                parentFrame.getHeight() / 2 - BUTTON_SIZE / 2 - SPACING - BUTTON_SIZE,
                "screencapwhite.png");
        screencapButton.setMouseClickHandler(new ShotRunnable(painter) {
            @Override
            public void run() {
                ShotCreator.handleScreenShot(painter, new Point(0, 0), new Point(parentFrame.getSize().width, parentFrame.getSize().height));
            }
        });
        buttons.add(screencapButton);

        // Settings button
        final ShotMenuButton settingsButton = new ShotMenuButton(
                painter,
                parentFrame.getWidth() - BUTTON_SIZE,
                parentFrame.getHeight() / 2 - BUTTON_SIZE / 2,
                "settingswhite.png");
        settingsButton.setMouseClickHandler(new ShotRunnable(painter) {
            @Override
            public void run() {
                painter.getSettings().showSettings();
            }
        });
        buttons.add(settingsButton);

        // Close button
        final ShotMenuButton closeButton = new ShotMenuButton(
                painter,
                parentFrame.getWidth() - BUTTON_SIZE,
                parentFrame.getHeight() / 2 + BUTTON_SIZE / 2 + SPACING,
                "closewhite.png");
        closeButton.setMouseClickHandler(new ShotRunnable(painter) {
            @Override
            public void run() {
                // New behavior: Exit ScreenShottr
                painter.getApp().stop(0);

                // Old behavior: Close only the menu bar
                //painter.getContentPanel().getMenuBar().setVisible(false);
                //parentFrame.setCursor(ShotPainter.DEFAULT_CURSOR);
            }
        });
        buttons.add(closeButton);
    }

    @Override
    public void paintComponent(Graphics graphics) {
        if (!isVisible()) {
            return;
        }

        if (background.contains(mouseAdapter.getX(), mouseAdapter.getY())) {
            painter.getParentFrame().setCursor(ShotPainter.HOVER_CURSOR);
        } else {
            painter.getParentFrame().setCursor(ShotPainter.DEFAULT_CURSOR);
        }


        if (buttons.isEmpty()) {
            initComponents();
        }

        final Graphics2D screen = (Graphics2D) graphics.create();

        // Background
        screen.setComposite(AlphaComposite.SrcOver.derive(0.4f));
        screen.fill(background);

        // Buttons
        for (ShotMenuButton button : buttons) {
            button.paintComponent(graphics);
        }

        screen.dispose();
    }

    public List<ShotMenuButton> getButtons() {
        return Collections.unmodifiableList(buttons);
    }
}
