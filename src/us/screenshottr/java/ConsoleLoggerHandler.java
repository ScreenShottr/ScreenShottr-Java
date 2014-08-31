package us.screenshottr.java;

import java.util.logging.ConsoleHandler;

public class ConsoleLoggerHandler extends ConsoleHandler {

    public ConsoleLoggerHandler() {
        setOutputStream(System.out);
    }
}
