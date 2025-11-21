package use_case.navigate;

import interface_adapter.ViewManagerModel;
import view.CardGameView;
import view.TriviaGameView;

public class NavigateInteractor implements NavigateInputBoundary {

    private final NavigateOutputBoundary navigatePresenter;
    private final ViewManagerModel viewManagerModel;
    private CardGameView cardGameView;
    private TriviaGameView triviaGameView;

    public NavigateInteractor(NavigateOutputBoundary navigatePresenter, ViewManagerModel viewManagerModel) {
        this.navigatePresenter = navigatePresenter;
        this.viewManagerModel = viewManagerModel;
    }

    public NavigateInteractor(NavigateOutputBoundary navigatePresenter) {
        this(navigatePresenter, null);
    }

    public void setCardGameView(CardGameView cardGameView) {
        this.cardGameView = cardGameView;
    }

    public void setTriviaGameView(TriviaGameView triviaGameView) {
        this.triviaGameView = triviaGameView;
    }

    @Override
    public void execute(NavigateInputData inputData) {
        String direction = inputData.getDirection();

        if (viewManagerModel != null) {
            switch (direction) {
                case "North":
                    viewManagerModel.setState("trivia game");
                    viewManagerModel.firePropertyChange();
                    if (triviaGameView != null) {
                        triviaGameView.startGame();
                    }
                    break;

                case "East":
                    viewManagerModel.setState("card game");
                    viewManagerModel.firePropertyChange();
                    if (cardGameView != null) {
                        cardGameView.startGame();
                    }
                    break;

                case "South":
                    viewManagerModel.setState("win game");
                    viewManagerModel.firePropertyChange();
                    break;

                case "West":
                    String storyText = "You walk West toward the Quad...\n\nThe shadows grow deeper.\n\n(This location is not yet implemented)";
                    NavigateOutputData outputData = new NavigateOutputData(storyText, direction);
                    navigatePresenter.prepareSuccessView(outputData);
                    break;

                default:
                    String defaultStory = "You stand still, unsure where to go.";
                    NavigateOutputData defaultOutput = new NavigateOutputData(defaultStory, direction);
                    navigatePresenter.prepareSuccessView(defaultOutput);
                    break;
            }
        } else {
            String storyText = generateStoryForDirection(direction);
            NavigateOutputData output = new NavigateOutputData(storyText, direction);
            navigatePresenter.prepareSuccessView(output);
        }
    }

    private String generateStoryForDirection(String direction) {
        return direction;
    }
}