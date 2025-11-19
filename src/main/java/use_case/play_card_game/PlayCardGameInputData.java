package use_case.play_card_game;

public class PlayCardGameInputData {
    String puzzleId;

    public PlayCardGameInputData(String puzzleId) {
        this.puzzleId = puzzleId;
    }

    public String getPuzzleId() {return puzzleId;}
}