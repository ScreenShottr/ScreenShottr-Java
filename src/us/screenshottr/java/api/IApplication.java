package us.screenshottr.java.api;

import java.io.File;
import java.util.logging.Logger;

public interface IApplication extends IStartStoppable {

    public String getName();

    public String getVersion();

    public Logger getLogger();

    public File getAppFolder();

    public IConfiguration getConfig();
}
