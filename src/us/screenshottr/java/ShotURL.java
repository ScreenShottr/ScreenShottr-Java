package us.screenshottr.java;

import java.net.MalformedURLException;
import java.net.URL;
import us.screenshottr.java.api.IConfiguration;
import us.screenshottr.java.config.ConfigKey;
import us.screenshottr.java.config.Configuration;

public class ShotURL {

    public static final String BASE = "screenshottr.us";
    private boolean https;
    private boolean encrypt;

    public static ShotURL builder() {
        return new ShotURL();
    }

    private ShotURL() {
        this.https = Configuration.deserialize(ConfigKey.URL_HTTPS.getDefault(), boolean.class);
        this.encrypt = Configuration.deserialize(ConfigKey.URL_ENCRYPT.getDefault(), boolean.class);
    }

    public ShotURL withConfig(IConfiguration config) {
        this.https = config.getBoolean(ConfigKey.URL_HTTPS);
        this.encrypt = config.getBoolean(ConfigKey.URL_ENCRYPT);
        return this;
    }

    public ShotURL withHTTPS(boolean https) {
        this.https = https;
        return this;
    }

    public ShotURL withEncrypt(boolean encrypt) {
        this.encrypt = encrypt;
        return this;
    }

    public URL build() {
        final StringBuilder builder = new StringBuilder();

        builder.append(https ? "https://" : "http://");
        builder.append(BASE);
        builder.append("/upload?return=json");
        builder.append(encrypt ? "" : "&unencrypted=true");

        try {
            return new URL(builder.toString());
        } catch (MalformedURLException ex) {
            ShotUtil.handleError(ex);
            return null;
        }
    }
}
