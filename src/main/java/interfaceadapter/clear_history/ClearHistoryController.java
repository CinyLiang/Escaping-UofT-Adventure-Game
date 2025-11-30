package interfaceadapter.clear_history;

import use_case.clear_history.ClearHistoryInputBoundary;

public class ClearHistoryController {
    private Runnable showConfirmDialog;
    private ClearHistoryInputBoundary interactor;

    public ClearHistoryController(ClearHistoryInputBoundary interactor) {
        this.interactor = interactor; // init in app builder?
    }

    public void setShowConfirmDialog(Runnable r) {
        this.showConfirmDialog = r;
    }

    public void showConfirm() {
        showConfirmDialog.run();
    }

    public void execute() {
        interactor.clearHistory();
    }

}