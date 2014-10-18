package us.screenshottr.java.config;

import java.awt.event.KeyEvent;
import us.screenshottr.java.api.IKeyContainer;

public enum ConfigKey implements IKeyContainer {

    MOUSEBIND("mousebind", KeyEvent.VK_F12), //TODO: Implement
    OPACITY("selection_opacity", 0.3f),
    URL_HTTPS("url_https", true),
    URL_LANDING_PAGE("url_landing_page", false),
    URL_ENCRYPT("url_encrypt", true),
    COPY_TO_CLIBOARD("copy_to_clipboard", true),
    OPEN_IN_BROWSER("open_in_browser", true),;
    //
    private final String key;
    private final Object defValue;

    private ConfigKey(String key, Object defValue) {
        this.key = key;
        this.defValue = defValue;
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public Object getDefault() {
        return defValue;
    }
}
