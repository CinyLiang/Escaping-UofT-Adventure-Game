package interface_adapter.win_game;

import interface_adapter.ViewManagerModel;
import use_case.win_game.WinGameOutputBoundary;
import use_case.win_game.WinGameOutputData;

public class WinGamePresenter implements WinGameOutputBoundary {
    private final WinGameViewModel winGameViewModel;
    private final ViewManagerModel viewManagerModel;

    public WinGamePresenter(WinGameViewModel winGameViewModel, ViewManagerModel viewManagerModel) {
        this.winGameViewModel = winGameViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareWinView(WinGameOutputData outputData) {
        WinGameState state = winGameViewModel.getState();
        state.setWon(true);
        state.setMessage(outputData.getMessage());
        state.setKeysCollected(outputData.getKeysCollected());

        winGameViewModel.firePropertyChange();

        viewManagerModel.setState(winGameViewModel.getViewName());
        viewManagerModel.firePropertyChange();
    }

    @Override
    public void prepareFailView(String errorMessage) {
        WinGameState state = winGameViewModel.getState();
        state.setWon(false);
        state.setMessage(errorMessage);

        winGameViewModel.firePropertyChange();

        viewManagerModel.setState(winGameViewModel.getViewName());
        viewManagerModel.firePropertyChange();
    }
}