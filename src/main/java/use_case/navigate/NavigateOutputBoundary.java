package use_case.navigate;

public interface NavigateOutputBoundary {
//    void execute(); ?
    void prepareSuccessView(use_case.navigate.NavigateOutputData data);

    void prepareFailVew (error.InvalidInputException e);
}