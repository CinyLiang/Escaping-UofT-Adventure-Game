package use_case.navigate;

public class NavigateInteractor implements NavigateInputBoundary {

    private final NavigateOutputBoundary presenter;
    public NavigateInteractor(NavigateOutputBoundary presenter) {
        this.presenter = presenter;
    }

    @Override
    public void execute(NavigateInputData inputData) {
        String direction = inputData.getDirection();
        String storyText = generateStoryForDirection(direction);
        NavigateOutputData output =
                new NavigateOutputData(storyText, direction);
        presenter.prepareSuccessView(output);
    }
    private String generateStoryForDirection(String direction) {
        return direction;
    }
}
