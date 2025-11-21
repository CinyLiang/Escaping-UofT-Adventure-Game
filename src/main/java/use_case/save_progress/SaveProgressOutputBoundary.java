package use_case.save_progress;

public interface SaveProgressOutputBoundary {

    void prepareSuccessView(SaveProgressOutputData outputData);

    void prepareFailView(String errorMessage);
}
