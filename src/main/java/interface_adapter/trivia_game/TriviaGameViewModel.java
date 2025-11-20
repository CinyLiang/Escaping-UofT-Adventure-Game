package interface_adapter.trivia_game;

import interface_adapter.ViewModel;

public class TriviaGameViewModel extends ViewModel<TriviaGameState> {

    public TriviaGameViewModel() {
        super("trivia game");
        setState(new TriviaGameState());
    }
}