package interface_adapter.save_progress;

import use_case.save_progress.SaveProgressInputBoundary;
import use_case.save_progress.SaveProgressInputData;

/**
 * Controller for the Save Progress use case.
 * Called from the Save button in the GUI.
 */
public class SaveProgressController {

    private final SaveProgressInputBoundary interactor;

    public SaveProgressController(SaveProgressInputBoundary interactor) {
        this.interactor = interactor;
    }

    /**
     * No parameters for now since saving just uses current game state.
     * The view can simply call controller.execute() when the button is clicked.
     */
    public void execute() {
        SaveProgressInputData inputData = new SaveProgressInputData();
        interactor.execute(inputData);
    }
}
