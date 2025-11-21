package use_case.navigate;

public interface NavigateInputBoundary {

    /**
     * Perform navigation based on the user's chosen direction
     * @param inputData contains the direction to move (north, south, east, west)
     */
    void execute(NavigateInputData inputData);
}
