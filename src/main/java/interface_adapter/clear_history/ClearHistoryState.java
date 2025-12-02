package interface_adapter.clear_history;

public class ClearHistoryState {
    private boolean clearDialogVisible = false;

    public void setClearDialogVisible(boolean clearDialogVisible) {
        this.clearDialogVisible = clearDialogVisible;
    }

    public boolean isClearDialogVisible() {
        return clearDialogVisible;
    }
}
