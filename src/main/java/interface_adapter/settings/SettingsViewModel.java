package interface_adapter.settings;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SettingsViewModel {

    public static final String VIEW_NAME = "settings_view";

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    private String theme = "dark";
    private String fontSize = "medium";
    private boolean accessibility = false;

    public void setTheme(String theme) {
        this.theme = theme;
        support.firePropertyChange("settings", null, null);
    }

    public void setFontSize(String size) {
        this.fontSize = size;
        support.firePropertyChange("settings", null, null);
    }

    public void setAccessibility(boolean enabled) {
        this.accessibility = enabled;
        support.firePropertyChange("settings", null, null);
    }

    public String getTheme() { return theme; }
    public String getFontSize() { return fontSize; }
    public boolean isAccessibility() { return accessibility; }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        support.addPropertyChangeListener(l);
    }
}
