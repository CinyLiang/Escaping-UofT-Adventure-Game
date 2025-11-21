package use_case.save_progress;

/**
 * Interactor for the Save Progress use case.
 */
public class SaveProgressInteractor implements SaveProgressInputBoundary {

    private final SaveProgressDataAccessInterface saveGateway;
    private final SaveProgressOutputBoundary presenter;

    public SaveProgressInteractor(SaveProgressDataAccessInterface saveGateway,
                                  SaveProgressOutputBoundary presenter) {
        this.saveGateway = saveGateway;
        this.presenter = presenter;
    }

    @Override
    public void execute(SaveProgressInputData inputData) {
        boolean ok = saveGateway.saveProgress();

        if (ok) {
            SaveProgressOutputData outputData =
                    new SaveProgressOutputData(true, "Game progress saved successfully.");
            presenter.prepareSuccessView(outputData);
        } else {
            presenter.prepareFailView("Failed to save game progress.");
        }
    }
}
