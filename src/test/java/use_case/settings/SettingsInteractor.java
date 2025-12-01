package use_case.settings;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SettingsInteractorTest {

    @Test
    void testInteractorSendsCorrectSettings() {
        SettingsPresenterStub presenter = new SettingsPresenterStub();
        SettingsInteractor interactor = new SettingsInteractor(presenter);

        SettingsInputData input = new SettingsInputData(
                "Dark",
                "Large",
                true
        );

        interactor.saveSettings(input);
        // Verify presenter was called
        assertTrue(presenter.called);
        // Verify correct values were passed
        assertEquals("Dark", presenter.theme);
        assertEquals("Large", presenter.fontSize);
        assertTrue(presenter.accessibility);
    }

    @Test
    void testInteractorHandlesDifferentValues() {
        SettingsPresenterStub presenter = new SettingsPresenterStub();
        SettingsInteractor interactor = new SettingsInteractor(presenter);
        SettingsInputData input = new SettingsInputData(
                "Light",
                "Small",
                false
        );
        interactor.saveSettings(input);
        // Presenter called once
        assertTrue(presenter.called);
        // Check values
        assertEquals("Light", presenter.theme);
        assertEquals("Small", presenter.fontSize);
        assertFalse(presenter.accessibility);
    }


    static class SettingsPresenterStub implements SettingsOutputBoundary {
        boolean called = false;
        String theme = null;
        String fontSize = null;
        boolean accessibility = false;

        @Override
        public void applySettings(SettingsOutputData data) {
            called = true;
            theme = data.theme;
            fontSize = data.fontSize;
            accessibility = data.accessibility;
        }
    }
}
