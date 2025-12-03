package use_case.view_progress;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Set;

class ViewProgressInteractorTest {

    @Test
    void testViewProgress() {
        TestPresenter presenter = new TestPresenter();
        ViewProgressInteractor interactor = new ViewProgressInteractor(presenter);

        ViewProgressInputData input = new ViewProgressInputData(
                "Library", 3, Set.of("p1", "p2")
        );

        interactor.execute(input);

        assertEquals("Library", presenter.output.getLocation());
        assertEquals(3, presenter.output.getKeysCollected());
        assertEquals(Set.of("p1", "p2"), presenter.output.getSolvedPuzzles());
    }

    static class TestPresenter implements ViewProgressOutputBoundary {
        ViewProgressOutputData output;

        @Override
        public void present(ViewProgressOutputData data) {
            this.output = data;
        }
    }
}

