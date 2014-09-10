package us.screenshottr.java;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Level;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ShotUtil {

    public static void verifyTranslucencySupported() {
        final GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

        // If translucent windows aren't supported, exit.
        if (gd.isWindowTranslucencySupported(GraphicsDevice.WindowTranslucency.TRANSLUCENT)
                && gd.isWindowTranslucencySupported(GraphicsDevice.WindowTranslucency.PERPIXEL_TRANSLUCENT)) {
            return;
        }

        JOptionPane.showMessageDialog(new JFrame(),
                "Translucency is not supported by your graphics card.",
                "Error </3",
                JOptionPane.ERROR_MESSAGE);
        ScreenShottr.LOGGER.severe("Transluceny is not supported by the attached graphics card!");
        System.exit(1);
    }

    public static void showDialog(String title, String message, int type) {
        JOptionPane.showMessageDialog(new JFrame(),
                message,
                title,
                type);
    }

    public static void handleError(Throwable ex) {
        final String stacktrace = getStackTrace(ex);
        ScreenShottr.LOGGER.log(Level.SEVERE, stacktrace);
        JOptionPane.showMessageDialog(new JFrame(),
                "An error occured whilst making screenshot:\n"
                + stacktrace,
                "An error occured",
                JOptionPane.ERROR_MESSAGE);
        ScreenShottr.APP.stop(1);
    }

    public static String getStackTrace(Throwable throwable) {
        final StringWriter sw = new StringWriter();
        final PrintWriter pw = new PrintWriter(sw, true);
        throwable.printStackTrace(pw);
        return sw.getBuffer().toString();
    }

    public static void putClipboard(String contents) {
        final StringSelection selection = new StringSelection(contents);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);
        ScreenShottr.LOGGER.info("Copied in clipboard: " + contents);
    }
}
