package us.screenshottr.java.api;

import java.util.logging.Logger;

public interface IApplication extends IStoppable {

    public String getName();

    public String getVersion();

    public void start();

    public Logger getLogger();
}
