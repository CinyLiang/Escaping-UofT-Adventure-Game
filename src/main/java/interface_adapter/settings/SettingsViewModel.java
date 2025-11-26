package interface_adapter.settings;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SettingsViewModel {

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    private String theme = "dark";
    private String fontSize = "medium";
    private boolean accessibility = false;

    public void setTheme(String theme) {
        this.theme = theme;
        support.firePropertyChange("theme", null, theme);
    }

    public void setFontSize(String fs) {
        this.fontSize = fs;
        support.firePropertyChange("fontSize", null, fs);
    }

    public void setAccessibility(boolean acc) {
        this.accessibility = acc;
        support.firePropertyChange("accessibility", null, acc);
    }

    public String getTheme() { return theme; }
    public String getFontSize() { return fontSize; }
    public boolean isAccessibility() { return accessibility; }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        support.addPropertyChangeListener(l);
    }
}
