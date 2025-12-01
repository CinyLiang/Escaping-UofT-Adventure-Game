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
        assertEquals("Card game", presenter.targetView);

        assertFalse(presenter.failCalled);
        assertFalse(presenter.updateCalled);
    }

    @Test
    void testNavigateSouth() {
        NavigatePresenterStub presenter = new NavigatePresenterStub();
        NavigateInteractor interactor = new NavigateInteractor(presenter);

        interactor.execute(new NavigateInputData("south"));

        assertTrue(presenter.successCalled);
        assertEquals("Win game", presenter.targetView);

        assertFalse(presenter.failCalled);
        assertFalse(presenter.updateCalled);
    }

    @Test
    void testNavigateEast() {
        NavigatePresenterStub presenter = new NavigatePresenterStub();
        NavigateInteractor interactor = new NavigateInteractor(presenter);

        interactor.execute(new NavigateInputData("east"));

        assertTrue(presenter.successCalled);
        assertEquals("Trivia game", presenter.targetView);

        assertFalse(presenter.failCalled);
        assertFalse(presenter.updateCalled);
    }

    @Test
    void testNavigateWest() {
        NavigatePresenterStub presenter = new NavigatePresenterStub();
        NavigateInteractor interactor = new NavigateInteractor(presenter);

        interactor.execute(new NavigateInputData("west"));

        assertTrue(presenter.successCalled);
        assertEquals("Card game", presenter.targetView);

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
        public void prepareSuccessView(NavigateOutputData2 data) {
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
