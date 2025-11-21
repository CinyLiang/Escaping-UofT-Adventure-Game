package use_case.navigate;

public class NavigateInteractor implements NavigateInputBoundary {

    private final NavigateOutputBoundary navigatePresenter;

    public NavigateInteractor(NavigateOutputBoundary navigatePresenter) {
        this.navigatePresenter = navigatePresenter;
    }

    @Override
    public void execute(NavigateInputData inputData) {
        try {
            String direction = inputData.getDirection();
            String storyText = generateStoryForDirection(direction);

            NavigateOutputData outputData =
                    new NavigateOutputData(storyText, direction);

            navigatePresenter.prepareSuccessView(outputData);

        } catch (Exception e) {
            navigatePresenter.prepareFailView("Navigation failed: " + e.getMessage());
        }
    }

    private String generateStoryForDirection(String direction) {
        switch (direction.toLowerCase()) {
            case "North":
                return "You head north toward University College...\n\nA cold breeze brushes past.";
            case "South":
                return "You walk south toward the Engineering buildings...\n\nSomething feels off.";
            case "East":
                return "You turn east toward Convocation Hall...\n\nThe echoes follow you.";
            case "West":
                return "You walk west toward the Quad...\n\nThe shadows grow deeper.";
            default:
                return "You stand still, unsure where to go.";
        }
    }
}
