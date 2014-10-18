package us.screenshottr.java;

import java.awt.Desktop;
import java.awt.Desktop.Action;
import java.awt.GraphicsDevice;
import java.awt.GraphicsDevice.WindowTranslucency;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.util.logging.Level;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

public class ShotUtil {

    public static void verifyTranslucencySupported() {
        final GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

        // If translucent windows aren't supported, exit.
        if (gd.isWindowTranslucencySupported(WindowTranslucency.TRANSLUCENT)
                && gd.isWindowTranslucencySupported(WindowTranslucency.PERPIXEL_TRANSLUCENT)) {
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

    public static void setLookAndFeel() {
        // Try native LNF
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            return;
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
        }

        // Fallback to Nimbus
        try {
            for (LookAndFeelInfo lnf : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(lnf.getName())) {
                    UIManager.setLookAndFeel(lnf.getClassName());
                    return;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
        }

        // Last resort: Metal
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

    private static String getStackTrace(Throwable throwable) {
        final StringWriter sw = new StringWriter();
        final PrintWriter pw = new PrintWriter(sw, true);
        throwable.printStackTrace(pw);
        return sw.getBuffer().toString();
    }

    public static void putClipboard(String contents) {
        final StringSelection selection = new StringSelection(contents);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);
        ScreenShottr.LOGGER.info("Set contents in clipboard");
    }

    public static void openWebpage(URL url) {
        final Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;

        if (desktop == null) {
            ScreenShottr.LOGGER.warning("Could not open URL in webbrowser: Desktop not supported!");
            return;
        }

        if (!desktop.isSupported(Action.BROWSE)) {
            ScreenShottr.LOGGER.warning("Could not open URL in webbrowser: Browsing not supported!");
            return;
        }

        try {
            desktop.browse(url.toURI());
        } catch (Exception ex) {
            ShotUtil.handleError(ex);
        }
    }

    public static File determineAppFolder() {

        final String os = System.getProperty("os.name").toUpperCase();
        File parentFolder = null;

        // Windows
        if (os.contains("WIN")) {
            final String appData = System.getenv("APPDATA");

            if (appData != null && !appData.isEmpty()) {
                final File appDataFolder = new File(appData);
                if (appDataFolder.exists()) {
                    parentFolder = appDataFolder;
                }
            }
        }

        // Linux/Mac
        if (parentFolder == null) {
            final String userHome = System.getProperty("user.home");

            if (userHome != null && !userHome.isEmpty()) {
                final File userHomeFolder = new File(userHome);
                if (userHomeFolder.exists()) {
                    parentFolder = userHomeFolder;
                }
            }
        }

        // Last resort: Current directory
        if (parentFolder == null) {
            parentFolder = new File(".");
        }

        final File dataFolder = new File(parentFolder, ScreenShottr.NAME);
        if (!dataFolder.exists()) {
            dataFolder.mkdirs();
        }

        return dataFolder;
    }

    public static Image getImageResource(String name) {
        return Toolkit.getDefaultToolkit().getImage(ScreenShottr.class.getResource("/resources/" + name));
    }
}
