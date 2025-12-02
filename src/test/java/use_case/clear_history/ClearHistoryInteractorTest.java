package use_case.clear_history;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ClearHistoryInteractorTest {

    @Test
    void testClearHistoryDialog() {
        ClearHistoryPresenterStub presenter = new ClearHistoryPresenterStub();

        ClearHistoryInteractor interactor = new ClearHistoryInteractor(presenter);

        interactor.executeRequestClearHistory();

        assertTrue(presenter.confirmPromptCalled);
        assertFalse(presenter.clearHistoryCalled);
    }

    @Test
    void testClearHistory() {
        ClearHistoryPresenterStub presenter = new ClearHistoryPresenterStub();

        ClearHistoryInteractor interactor = new ClearHistoryInteractor(presenter);

        interactor.clearHistory();

        assertFalse(presenter.confirmPromptCalled);
        assertTrue(presenter.clearHistoryCalled);
    }

    static class ClearHistoryPresenterStub implements ClearHistoryOutputBoundary {
        boolean confirmPromptCalled = false;
        boolean clearHistoryCalled = false;

        // resets
        @Override
        public void execute() {
            clearHistoryCalled = true;
        }

        // shows prompt
        @Override
        public void prepareClearHistoryView() {
            confirmPromptCalled = true;
        }
    }
}
