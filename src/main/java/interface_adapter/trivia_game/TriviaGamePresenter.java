package interface_adapter.trivia_game;

import use_case.trivia_game.TriviaGameOutputBoundary;
import use_case.trivia_game.TriviaGameOutputData;

public class TriviaGamePresenter implements TriviaGameOutputBoundary {
    private final TriviaGameViewModel viewModel;

    public TriviaGamePresenter(TriviaGameViewModel viewModel) {
        this.viewModel = viewModel;
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
}