package use_case.clear_history;

public class ClearHistoryInteractor implements ClearHistoryInputBoundary {
    private final ClearHistoryOutputBoundary presenter;

    public ClearHistoryInteractor(ClearHistoryOutputBoundary presenter) {
        this.presenter = presenter;
    }

    // clear history
    @Override
    public void clearHistory() {
        presenter.execute();
    }

    // shows dialog
    @Override
    public void executeRequestClearHistory() {
        presenter.prepareClearHistoryView();
    }
}