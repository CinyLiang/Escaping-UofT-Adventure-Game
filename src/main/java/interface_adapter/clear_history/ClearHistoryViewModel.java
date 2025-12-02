package interface_adapter.clear_history;

import interface_adapter.ViewModel;

public class ClearHistoryViewModel extends ViewModel<ClearHistoryState> {
    public static final String CLEAR_HISTORY_STATE = "clearHistoryState";

    public ClearHistoryViewModel() {
        super("clear history");
        setState(new ClearHistoryState());
    }

    public void fireShowRestartDialog() {
        super.firePropertyChange(CLEAR_HISTORY_STATE);
    }
}
