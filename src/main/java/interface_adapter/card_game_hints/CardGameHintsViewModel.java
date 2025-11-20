package interface_adapter.card_game_hints;

import interface_adapter.ViewModel;
import interface_adapter.play_card_game.CardGameState;

/**
 * The View Model for the Get Hints Use Case.
 */
public class CardGameHintsViewModel extends ViewModel<CardGameHintsState> {

    public CardGameHintsViewModel() {
        super("Get hints");
        setState(new CardGameHintsState());
    }
}
