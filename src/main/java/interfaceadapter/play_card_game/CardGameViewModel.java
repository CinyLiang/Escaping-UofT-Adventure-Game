package interfaceadapter.play_card_game;

import interfaceadapter.ViewModel;

/**
 * The View Model for the Play Card Game.
 */
public class CardGameViewModel extends ViewModel<CardGameState> {

    public CardGameViewModel() {
        super("Card Game");
        setState(new CardGameState());
    }
}
