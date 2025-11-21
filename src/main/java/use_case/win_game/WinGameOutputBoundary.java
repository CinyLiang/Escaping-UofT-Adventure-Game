package use_case.win_game;

public interface WinGameOutputBoundary {
    void prepareWinView(WinGameOutputData outputData);

    void prepareFailView(String errorMessage);
}