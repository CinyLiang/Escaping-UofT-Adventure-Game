package use_case.play_card_game;

import java.util.ArrayList;

/**
 * Output Data for the Play Card Game Use Case.
 */
public class PlayCardGameOutputData {
    private final boolean success;
    private final ArrayList<Integer> cardVals;

    public PlayCardGameOutputData(boolean success,  ArrayList<Integer> cardVals) {
        this.success = success;
        this.cardVals = cardVals;
    }

    public boolean getSuccess() {
        return this.success;
    }
    public ArrayList<Integer> getCardVals() {return this.cardVals;}
}
