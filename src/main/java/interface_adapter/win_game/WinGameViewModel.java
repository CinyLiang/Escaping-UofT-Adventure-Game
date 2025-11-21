package interface_adapter.win_game;

import interface_adapter.ViewModel;

public class WinGameViewModel extends ViewModel<WinGameState> {

    public WinGameViewModel() {
        super("win game");
        setState(new WinGameState());
    }
}