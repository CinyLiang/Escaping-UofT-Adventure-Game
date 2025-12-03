package use_case.navigate;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NavigateInteractorTest {

    @Test
    void testNavigateNorth() {
        NavigatePresenterStub presenter = new NavigatePresenterStub();
        NavigateInteractor interactor = new NavigateInteractor(presenter);

        interactor.execute(new NavigateInputData("north"));

        assertTrue(presenter.successCalled);
        assertEquals("UC exterior", presenter.targetView);

        assertFalse(presenter.failCalled);
        assertFalse(presenter.updateCalled);
    }

    @Test
    void testNavigateSouth() {
        NavigatePresenterStub presenter = new NavigatePresenterStub();
        NavigateInteractor interactor = new NavigateInteractor(presenter);

        interactor.execute(new NavigateInputData("south"));

        assertTrue(presenter.successCalled);
        assertEquals("con hall exterior", presenter.targetView);

        assertFalse(presenter.failCalled);
        assertFalse(presenter.updateCalled);
    }

    @Test
    void testNavigateEast() {
        NavigatePresenterStub presenter = new NavigatePresenterStub();
        NavigateInteractor interactor = new NavigateInteractor(presenter);

        interactor.execute(new NavigateInputData("east"));

        assertTrue(presenter.successCalled);
        assertEquals("Gerstein exterior", presenter.targetView);
        assertFalse(presenter.failCalled);
        assertFalse(presenter.updateCalled);
    }

    @Test
    void testNavigateWest() {
        NavigatePresenterStub presenter = new NavigatePresenterStub();
        NavigateInteractor interactor = new NavigateInteractor(presenter);

        interactor.execute(new NavigateInputData("west"));

        assertTrue(presenter.successCalled);
        assertEquals("Knox exterior", presenter.targetView);

        assertFalse(presenter.failCalled);
        assertFalse(presenter.updateCalled);
    }

    @Test
    void testNavigateKnoxInt() {
        NavigatePresenterStub presenter = new NavigatePresenterStub();
        NavigateInteractor interactor = new NavigateInteractor(presenter);

        interactor.execute(new NavigateInputData("knox interior"));

        assertTrue(presenter.successCalled);
        assertEquals("Knox interior", presenter.targetView);

        assertFalse(presenter.failCalled);
        assertFalse(presenter.updateCalled);
    }

    @Test
    void testNavigateGersteinInt() {
        NavigatePresenterStub presenter = new NavigatePresenterStub();
        NavigateInteractor interactor = new NavigateInteractor(presenter);

        interactor.execute(new NavigateInputData("gerstein interior"));

        assertTrue(presenter.successCalled);
        assertEquals("Gerstein interior", presenter.targetView);

        assertFalse(presenter.failCalled);
        assertFalse(presenter.updateCalled);
    }

    @Test
    void testNavigateUCInt() {
        NavigatePresenterStub presenter = new NavigatePresenterStub();
        NavigateInteractor interactor = new NavigateInteractor(presenter);

        interactor.execute(new NavigateInputData("uc interior"));

        assertTrue(presenter.successCalled);
        assertEquals("UC interior", presenter.targetView);

        assertFalse(presenter.failCalled);
        assertFalse(presenter.updateCalled);
    }

    @Test
    void testNavigateConHallInt() {
        NavigatePresenterStub presenter = new NavigatePresenterStub();
        NavigateInteractor interactor = new NavigateInteractor(presenter);

        interactor.execute(new NavigateInputData("con hall interior"));

        assertTrue(presenter.successCalled);
        assertEquals("Con Hall interior", presenter.targetView);

        assertFalse(presenter.failCalled);
        assertFalse(presenter.updateCalled);
    }

    @Test
    void testNavigateCardGame() {
        NavigatePresenterStub presenter = new NavigatePresenterStub();
        NavigateInteractor interactor = new NavigateInteractor(presenter);

        interactor.execute(new NavigateInputData("card game"));

        assertTrue(presenter.successCalled);
        assertEquals("Card game", presenter.targetView);

        assertFalse(presenter.failCalled);
        assertFalse(presenter.updateCalled);
    }

    @Test
    void testNavigateTriviaGame() {
        NavigatePresenterStub presenter = new NavigatePresenterStub();
        NavigateInteractor interactor = new NavigateInteractor(presenter);

        interactor.execute(new NavigateInputData("trivia game"));

        assertTrue(presenter.successCalled);
        assertEquals("Trivia game", presenter.targetView);

        assertFalse(presenter.failCalled);
        assertFalse(presenter.updateCalled);
    }

    @Test
    void testNavigateWinGame() {
        NavigatePresenterStub presenter = new NavigatePresenterStub();
        NavigateInteractor interactor = new NavigateInteractor(presenter);

        interactor.execute(new NavigateInputData("win game"));

        assertTrue(presenter.successCalled);
        assertEquals("win game", presenter.targetView);

        assertFalse(presenter.failCalled);
        assertFalse(presenter.updateCalled);
    }

    @Test
    void testNavigateInvalidDirection() {
        NavigatePresenterStub presenter = new NavigatePresenterStub();
        NavigateInteractor interactor = new NavigateInteractor(presenter);

        interactor.execute(new NavigateInputData("invalid"));

        assertTrue(presenter.successCalled);
        assertEquals("", presenter.targetView);

        assertFalse(presenter.failCalled);
        assertFalse(presenter.updateCalled);
    }

    static class NavigatePresenterStub implements NavigateOutputBoundary {

        boolean successCalled = false;
        boolean failCalled = false;
        boolean updateCalled = false;

        String targetView = null;
        String errorMessage = null;
        String updatedLocation = null;

        @Override
        public void prepareSuccessView(NavigateOutputData data) {
            successCalled = true;
            targetView = data.getTargetView();
        }

        @Override
        public void prepareFailView(String error) {
            failCalled = true;
            errorMessage = error;
        }

        @Override
        public void updateNavigation(String newLocation) {
            updateCalled = true;
            updatedLocation = newLocation;
        }
    }
}
