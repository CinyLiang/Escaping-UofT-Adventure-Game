package use_case.quit_game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class QuitGameInteractorTest {

    @Test
    void testInteractorExit() {
        QuitGamePresenterStub presenter = new QuitGamePresenterStub();

        QuitGameInteractor interactor = new QuitGameInteractor(presenter);

        interactor.exitGame();

        assertFalse(presenter.savePromptCalled); // save dialog NOT opened
        assertTrue(presenter.exitGameCalled); // game exits
        assertFalse(presenter.quitCalled); // quit dialog NOT opened
    }

    @Test
    void testInteractorSave() {
        QuitGamePresenterStub presenter = new QuitGamePresenterStub();

        QuitGameInteractor interactor = new QuitGameInteractor(presenter);

        interactor.execute();

         assertTrue(presenter.savePromptCalled);
         assertFalse(presenter.exitGameCalled);
         assertFalse(presenter.quitCalled);
    }

    @Test
    void testInteractorQuit() {
        QuitGamePresenterStub presenter = new QuitGamePresenterStub();

        QuitGameInteractor interactor = new QuitGameInteractor(presenter);

        interactor.executeRequestQuit();

        assertFalse(presenter.savePromptCalled);
        assertFalse(presenter.exitGameCalled);
        assertTrue(presenter.quitCalled);
    }

    static class QuitGamePresenterStub implements QuitGameOutputBoundary {
        boolean savePromptCalled = false;
        boolean exitGameCalled = false;
        boolean quitCalled = false;

        @Override
        public void prepareSavePromptView() {
            savePromptCalled = true;
        }

        @Override
        public void prepareExitView() {
            exitGameCalled = true;
        }

        @Override
        public void prepareQuitView() {
            quitCalled = true;
        }
    }
}
