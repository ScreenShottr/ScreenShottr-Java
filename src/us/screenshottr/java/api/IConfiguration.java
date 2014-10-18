package us.screenshottr.java.api;

public interface IConfiguration {

    public void load();

    public void save();

    public String getString(IKeyContainer key);

    public int getInt(IKeyContainer key);

    public float getFloat(IKeyContainer key);

    public boolean getBoolean(IKeyContainer key);

    public void set(IKeyContainer key, Object value);

    public void reset(IKeyContainer key);

    public void resetAll();
}
