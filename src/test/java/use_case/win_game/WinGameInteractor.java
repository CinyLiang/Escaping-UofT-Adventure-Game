package use_case.win_game;

import entity.Location;
import entity.Player;
import entity.WinCondition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WinGameInteractorTest {
    private WinGameInteractor interactor;
    private MockWinGamePresenter mockPresenter;
    private WinCondition winCondition;
    private Location startLocation;

    @BeforeEach
    void setUp() {
        mockPresenter = new MockWinGamePresenter();
        winCondition = new WinCondition(2);
        interactor = new WinGameInteractor(mockPresenter, winCondition);
        startLocation = new Location(
                "Kings College Circle",
                "The central lawn of the university.",
                false,
                "center",
                null
        );
    }

    @Test
    void testPlayerWithEnoughKeysWins() {
        Player player = new Player(startLocation);
        player.addKey();
        player.addKey();

        WinGameInputData inputData = new WinGameInputData(player);
        interactor.attemptWin(inputData);

        assertTrue(mockPresenter.winViewPrepared);
        assertNotNull(mockPresenter.lastOutputData);
    }

    @Test
    void testPlayerWithoutEnoughKeysFails() {
        Player player = new Player(startLocation);

        WinGameInputData inputData = new WinGameInputData(player);
        interactor.attemptWin(inputData);

        assertTrue(mockPresenter.failViewPrepared);
    }

    @Test
    void testPlayerWithOneKeyFails() {
        Player player = new Player(startLocation);

        WinGameInputData inputData = new WinGameInputData(player);
        interactor.attemptWin(inputData);

        assertTrue(mockPresenter.failViewPrepared);
        assertEquals("You need 2 more key(s) to enter Convocation Hall.",
                mockPresenter.lastErrorMessage);
    }

    @Test
    void testWinOutputDataContainsCorrectKeyCount() {
        Player player = new Player(startLocation);
        player.addKey();
        player.addKey();

        WinGameInputData inputData = new WinGameInputData(player);
        interactor.attemptWin(inputData);

        assertTrue(mockPresenter.winViewPrepared);
        assertEquals(2, mockPresenter.lastOutputData.getKeysCollected());
    }

    @Test
    void testGetRequiredKeys() {
        assertEquals(2, winCondition.getRequiredKeys());
    }

    @Test
    void testOutputDataCorrectlyStoresWinState() {
        WinGameOutputData outputData = new WinGameOutputData(
                true,
                2,
                2,
                "You win!"
        );

        assertTrue(outputData.isWon());
        assertEquals(2, outputData.getKeysCollected());
    }

    private static class MockWinGamePresenter implements WinGameOutputBoundary {
        boolean winViewPrepared = false;
        boolean failViewPrepared = false;
        WinGameOutputData lastOutputData = null;
        String lastErrorMessage = null;

        @Override
        public void prepareWinView(WinGameOutputData outputData) {
            winViewPrepared = true;
            lastOutputData = outputData;
        }

        @Override
        public void prepareFailView(String errorMessage) {
            failViewPrepared = true;
            lastErrorMessage = errorMessage;
        }
    }
}