package use_case.save_progress;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SaveProgressInteractorTest {

    @Test
    void testSaveSuccess() {
        // Fake gateway that always returns true
        SaveProgressDataAccessInterface fakeGateway = (location, keys, puzzles) -> true;

        TestPresenter presenter = new TestPresenter();
        SaveProgressInteractor interactor = new SaveProgressInteractor(fakeGateway, presenter);

        SaveProgressInputData input = new SaveProgressInputData("RoomA", 3, java.util.Set.of("p1"));

        interactor.execute(input);

        assertTrue(presenter.successCalled);
        assertFalse(presenter.failCalled);
        assertEquals("Game progress saved successfully.", presenter.lastOutput.getMessage());
    }

    @Test
    void testSaveFail() {
        // Fake gateway that always returns false
        SaveProgressDataAccessInterface fakeGateway = (location, keys, puzzles) -> false;

        TestPresenter presenter = new TestPresenter();
        SaveProgressInteractor interactor = new SaveProgressInteractor(fakeGateway, presenter);

        SaveProgressInputData input = new SaveProgressInputData("RoomA", 3, java.util.Set.of("p1"));

        interactor.execute(input);

        assertFalse(presenter.successCalled);
        assertTrue(presenter.failCalled);
        assertEquals("Failed to save game progress.", presenter.failedMessage);
    }


    // Helper presenter for testing
    private static class TestPresenter implements SaveProgressOutputBoundary {
        boolean successCalled = false;
        boolean failCalled = false;
        SaveProgressOutputData lastOutput = null;
        String failedMessage = null;

        @Override
        public void prepareSuccessView(SaveProgressOutputData data) {
            successCalled = true;
            lastOutput = data;
        }

        @Override
        public void prepareFailView(String errorMessage) {
            failCalled = true;
            failedMessage = errorMessage;
        }
    }
}
