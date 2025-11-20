package use_case.play_card_game;

public class PlayCardGameInputData {
    /**
     * The input data for the Play Card Game Use Case.
     */
    String puzzleId;

    public PlayCardGameInputData(String puzzleId) {
        this.puzzleId = puzzleId;
    }

    public String getPuzzleId() {return puzzleId;}
}