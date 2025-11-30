package interfaceadapter.trivia_game;

import interfaceadapter.ViewModel;

public class TriviaGameViewModel extends ViewModel<TriviaGameState> {

    public TriviaGameViewModel() {
        super("trivia game");
        setState(new TriviaGameState());
    }
}