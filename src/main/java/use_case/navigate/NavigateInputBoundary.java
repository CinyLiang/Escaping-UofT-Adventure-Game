package use_case.navigate;

public interface NavigateInputBoundary {

    /*
     * Execute the navigation given the user's chosen direction.
     * @param inputData contains the chosen direction (north, south, east, west)
     */
    void execute(NavigateInputData inputData);
}
