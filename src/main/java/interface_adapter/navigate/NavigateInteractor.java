package use_case.navigate;

public class NavigateInteractor implements NavigateInputBoundary {

    private final NavigateOutputBoundary navigatePresenter;
    private final Map<String, String> map;
    private final Player player;

    public NavigateInteractor(NavigateOutputBoundary navigatePresenter, Map<String, String> map, Player player) {
        this.navigatePresenter = navigatePresenter;
        this.map = gameMap;
        this.player = player;
    }

    /*
     * Takes a direction and returns updated story
     */
    @Override
    public void execute(NavigateInputData inputData) {
        String direction = inputData.getDirection();
        String storyText = generateStoryForDirection(direction);
        NavigateOutputData outputData =
                new NavigateOutputData(storyText, direction);
        navigatePresenter.present(outputData);
    }

    /*
     * with building traversal, puzzles, and key collection.
     */
    private String generateStoryForDirection(String direction) {
        switch (direction.toLowerCase()) {
            case "North":
                return "You head North toward University College...\n\nA cold breeze brushes past.";
            case "South":
                return "You walk South toward the Engineering buildings...\n\nSomething feels off.";
            case "East":
                return "You turn East toward Convocation Hall...\n\nThe echoes follow you.";
            case "West":
                return "You walk West toward the Quad...\n\nThe shadows grow deeper.";
            default:
                return "You stand still, unsure where to go.";
        }
    }
}
