package use_case.play_card_game;

import entity.Card;

import java.util.List;

/**
 * The DAO interface for the Playing Card Games Use Case.
 */
public interface CardGameDataAccessInterface {
    List<Card> drawCards (int cardNum);
}