package interface_adapter.clear_history;

import use_case.clear_history.ClearHistoryInputBoundary;

public class ClearHistoryController {
    private ClearHistoryInputBoundary interactor;

    public ClearHistoryController(ClearHistoryInputBoundary interactor) {
        this.interactor = interactor;
    }

    // Shows dialog
    public void showConfirmDialog() {
        interactor.executeRequestClearHistory();
    }

    // Executes clear
    public void execute() {
        interactor.clearHistory();
    }

}