package use_case.navigate;

public interface NavigateOutputBoundary {

    /*
     * Called when navigation succeeds.
     * @param response navigation data (story text + direction)
     */
    void prepareSuccessView(NavigateOutputData2 response);

    /*
     * Called when navigation fails.
     */
    void prepareFailView(String error);

    void updateNavigation(String newLocation);

}
