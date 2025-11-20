package interface_adapter.play_card_game;
import interface_adapter.ViewModel;

/**
 * The View Model for the Play Card Game.
 */
public class CardGameViewModel extends ViewModel<CardGameState> {

    public CardGameViewModel() {
        super("Card Game");
        setState(new CardGameState());
    }
}