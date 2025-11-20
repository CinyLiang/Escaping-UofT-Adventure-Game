package use_case.card_game_hints;

import java.util.*;
import entity.CardPuzzle;

/**
 * The DAO interface for the Get Hints Use Case.
 */
public interface CardGameHintsDataAccessInterface {
    String generateHint(CardPuzzle cardPuzzle);
}
