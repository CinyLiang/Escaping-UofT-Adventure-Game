package interfaceadapter.win_game;

import interfaceadapter.ViewModel;

public class WinGameViewModel extends ViewModel<WinGameState> {

    public WinGameViewModel() {
        super("win game");
        setState(new WinGameState());
    }
}