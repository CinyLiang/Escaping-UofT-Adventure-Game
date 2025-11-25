package interface_adapter.clear_history;
import interface_adapter.navigate.NavigateState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * ViewModel for the Clear History use case.
 * Stores UI state (success/failure)
 * and notifies views when changes occur.
 */

public class ClearHistoryViewModel {

    public static final String CLEAR_HISTORY_STATE = "clearHistoryState";
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private String message;
    public ClearHistoryViewModel() {
        this.message = "";
    }
    public void setMessage(String newMessage) {
        String oldMessage = this.message;
        this.message = newMessage;
        support.firePropertyChange(CLEAR_HISTORY_STATE, oldMessage, newMessage);
    }
    public String getMessage() {
        return message;
    }
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }

    public NavigateState getState() {
        return null;
    }

    public void firePropertyChange() {}
}