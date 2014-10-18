package us.screenshottr.java.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import us.screenshottr.java.ScreenShottr;
import us.screenshottr.java.ShotUtil;
import us.screenshottr.java.api.IConfiguration;
import us.screenshottr.java.api.IKeyContainer;

public class Configuration implements IConfiguration {

    private final File file;
    private final Properties properties;

    public Configuration(File file) {
        this.file = file;
        this.properties = new Properties();
    }

    @Override
    public void load() {
        ScreenShottr.LOGGER.info("Loading configuration from " + file.getAbsolutePath());
        properties.clear();

        if (!file.exists()) {
            ScreenShottr.LOGGER.info("Configuration does not exist!");
            return;
        }

        try (final InputStream in = new FileInputStream(file)) {
            properties.load(in);
        } catch (IOException ex) {
            ShotUtil.handleError(ex);
        }
    }

    @Override
    public void save() {
        for (ConfigKey key : ConfigKey.values()) {
            if (properties.containsKey(key.getKey())) {
                continue;
            }

            ScreenShottr.LOGGER.info("Populating configuration default - " + key.getKey() + ": " + serialize(key.getDefault()));
            properties.put(key.getKey(), serialize(key.getDefault()));
        }

        ScreenShottr.LOGGER.info("Saving configuration to " + file.getAbsolutePath());
        try (final OutputStream out = new FileOutputStream(file)) {
            properties.store(out, null);
        } catch (IOException ex) {
            ShotUtil.handleError(ex);
        }
    }

    @Override
    public String getString(IKeyContainer key) {
        return properties.getProperty(key.getKey(), key.getDefault().toString());
    }

    @Override
    public boolean getBoolean(IKeyContainer key) {
        if (properties.containsKey(key.getKey())) {
            return (boolean) deserialize(properties.getProperty(key.getKey()), boolean.class);
        } else {
            return (boolean) key.getDefault();
        }
    }

    @Override
    public int getInt(IKeyContainer key) {
        if (properties.containsKey(key.getKey())) {
            return (int) deserialize(properties.getProperty(key.getKey()), int.class);
        } else {
            return (int) key.getDefault();
        }
    }

    @Override
    public float getFloat(IKeyContainer key) {
        if (properties.containsKey(key.getKey())) {
            return (float) deserialize(properties.getProperty(key.getKey()), float.class);
        } else {
            return (float) key.getDefault();
        }
    }

    @Override
    public void set(IKeyContainer key, Object value) {
        properties.put(key.getKey(), serialize(value));
    }

    @Override
    public void reset(IKeyContainer key) {
        set(key, key.getDefault());
    }

    @Override
    public void resetAll() {
        for (ConfigKey key : ConfigKey.values()) {
            reset(key);
        }
    }

    private static String serialize(Object object) {
        if (object instanceof String || object instanceof Number || object instanceof Boolean) {
            return object.toString();
        }

        ScreenShottr.LOGGER.warning("Could not serialize: " + object);
        return object.toString();
    }

    public static Object deserialize(String value, Class<?> type) {
        if (type == int.class) {
            try {
                return Integer.parseInt(value);
            } catch (NumberFormatException ex) {
            }
            ScreenShottr.LOGGER.warning("Could not deserialize: " + value + "<" + type + ">");
            return 0;
        }

        if (type == float.class) {
            try {
                return Float.parseFloat(value);
            } catch (NumberFormatException ex) {
            }
            ScreenShottr.LOGGER.warning("Could not deserialize: " + value + "<" + type + ">");
            return 0.0f;
        }

        if (type == boolean.class) {
            return Boolean.parseBoolean(value);
        }

        ScreenShottr.LOGGER.severe("Unable to deserialize: " + value + "<" + type + ">!");
        return null;
    }

    @SuppressWarnings("unchecked")
    public static <T> T deserialize(Object value, Class<T> type) {
        return (T) deserialize(serialize(value), type);
    }
}
