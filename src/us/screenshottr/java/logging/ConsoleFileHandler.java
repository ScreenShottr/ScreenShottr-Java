package us.screenshottr.java.logging;

import java.io.File;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import us.screenshottr.java.ScreenShottr;

public class ConsoleFileHandler extends Handler {

    private final Handler handle;

    public ConsoleFileHandler(Formatter formatter) {
        final File file = new File(ScreenShottr.APP_FOLDER, "latest.log");

        if (file.exists()) {
            file.delete();
        }

        Handler fileHandler = null;
        try {
            fileHandler = new FileHandler(file.getAbsolutePath());
            fileHandler.setFormatter(formatter);
        } catch (Exception ex) {
            ex.printStackTrace(); // No logger here yet
        }

        handle = fileHandler;
    }

    @Override
    public void publish(LogRecord lr) {
        handle.publish(lr);
    }

    @Override
    public void flush() {
        handle.flush();
    }

    @Override
    public void close() throws SecurityException {
        handle.close();
    }
}
