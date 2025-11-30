package interfaceadapter.trivia_game;

import interfaceadapter.ViewManagerModel;
import interfaceadapter.navigate.NavigateState;
import interfaceadapter.navigate.NavigateViewModel;
import use_case.trivia_game.TriviaGameOutputBoundary;
import use_case.trivia_game.TriviaGameOutputData;

public class TriviaGamePresenter implements TriviaGameOutputBoundary {
    private final TriviaGameViewModel viewModel;

    private final NavigateViewModel navigateViewModel;
    private final ViewManagerModel viewManagerModel;

    public TriviaGamePresenter(TriviaGameViewModel viewModel, NavigateViewModel navigateViewModel, ViewManagerModel viewManagerModel) {
        this.viewModel = viewModel;
        this.navigateViewModel = navigateViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void presentQuestion(TriviaGameOutputData outputData) {
        TriviaGameState state = viewModel.getState();
        state.setQuestion(outputData.getQuestion());
        state.setMessage(outputData.getMessage());
        state.setCorrectAnswers(outputData.getCorrectAnswers());
        state.setRequiredAnswers(outputData.getRequiredAnswers());
        state.setPuzzleSolved(outputData.isPuzzleSolved());
        state.setAnsweredCurrentQuestion(false);

        viewModel.firePropertyChange();
    }

    @Override
    public void presentResult(TriviaGameOutputData outputData) {
        TriviaGameState state = viewModel.getState();
        state.setMessage(outputData.getMessage());
        state.setCorrectAnswers(outputData.getCorrectAnswers());
        state.setPuzzleSolved(outputData.isPuzzleSolved());
        state.setAnsweredCurrentQuestion(true);

        viewModel.firePropertyChange();
    }

    @Override
    public void exitPuzzle() {
        TriviaGameState state = viewModel.getState();
        NavigateState navState = navigateViewModel.getState();

        if (state.isPuzzleSolved() && !navState.getPuzzlesSolved().contains(state.getPuzzleName())) {
            navState.addNumberOfKeys();
            navState.addPuzzleSolved(state.getPuzzleName());
            navState.setStoryText("Good job on solving the trivia puzzle.\nWhere would you like to go next?");
        }

        navState.setLocation();
        this.navigateViewModel.firePropertyChange();

        this.viewManagerModel.setState(navigateViewModel.getViewName());
        this.viewManagerModel.firePropertyChange();
    }
}