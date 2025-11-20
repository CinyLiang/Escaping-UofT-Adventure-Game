package data_access;

import java.util.*;
import entity.Card;
import entity.CardPuzzle;
import use_case.card_game_hints.CardGameHintsDataAccessInterface;

public class CardHintsDataAccessObject implements CardGameHintsDataAccessInterface {
    /**
     * The DAO for the Get Hints Use Case.
     */

    @Override
    public String generateHint(CardPuzzle cardPuzzle) {
        return cardPuzzle.giveHint();
    }
}
