package use_case.card_return_to_home;

import entity.Card;
import entity.CardPuzzle;
import interface_adapter.ViewManagerModel;
import interface_adapter.navigate.NavigateState;
import interface_adapter.navigate.NavigateViewModel;
import interface_adapter.play_card_game.CardGamePresenter;
import interface_adapter.play_card_game.CardGameState;
import interface_adapter.play_card_game.CardGameViewModel;
import interface_adapter.return_from_card.ReturnFromCardPresenter;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class CardReturnInteracterTest {
    @Test
    public void testCardReturnInteracter() {
        // Test whether the interacter executes the return functionality successfully
        ViewManagerModel viewManagerModel  = new ViewManagerModel();
        NavigateViewModel navigateViewModel = new NavigateViewModel();
        CardGameViewModel cardGameViewModel  = new CardGameViewModel();

        CardGameState cardGameState = new CardGameState();
        cardGameState.setSolved();
        cardGameViewModel.setState(cardGameState);

        List<Card> cards = new ArrayList<>();
        cardGameState.setCardPuzzle(new CardPuzzle(cards));

        NavigateState navigateState = new NavigateState();
        navigateViewModel.setState(navigateState);

        ReturnFromCardPresenter presenter = new ReturnFromCardPresenter(
                viewManagerModel, navigateViewModel, cardGameViewModel);

        // Create interactor
        CardReturnInteractor interactor = new CardReturnInteractor(presenter);

        // This should execute without throwing exceptions
        assertDoesNotThrow(() -> interactor.execute());

        System.out.println("Success! Interactor executed without errors.");
    }
}
