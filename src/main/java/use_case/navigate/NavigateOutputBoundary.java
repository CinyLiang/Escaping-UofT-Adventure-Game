package use_case.navigate;

public interface NavigateOutputBoundary {
    void present(use_case.navigate.NavigateOutputData data);

    void prepareSuccessView(NavigateOutputData response);

    void prepareFailView(String error);
}
