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
        assertTrue(mockPresenter.lastOutputData.isWon());
        assertEquals("Congratulations! You've collected all the keys and escaped campus!",
                mockPresenter.lastOutputData.getMessage());
    }

    @Test
    void testPlayerWithoutEnoughKeysFails() {
        Player player = new Player(startLocation);

        WinGameInputData inputData = new WinGameInputData(player);
        interactor.attemptWin(inputData);

        assertTrue(mockPresenter.failViewPrepared);
        assertEquals("You need 2 more key(s) to enter Convocation Hall.",
                mockPresenter.lastErrorMessage);
    }

    @Test
    void testPlayerWithOneKeyFails() {
        Player player = new Player(startLocation);
        player.addKey();

        WinGameInputData inputData = new WinGameInputData(player);
        interactor.attemptWin(inputData);

        assertTrue(mockPresenter.failViewPrepared);
        assertEquals("You need 1 more key(s) to enter Convocation Hall.",
                mockPresenter.lastErrorMessage);
    }

    @Test
    void testWinConditionWithDifferentRequiredKeys() {
        WinCondition threeKeyCondition = new WinCondition(3);
        WinGameInteractor interactorWithThreeKeys = new WinGameInteractor(
                mockPresenter,
                threeKeyCondition
        );

        Player player = new Player(startLocation);

        WinGameInputData inputData = new WinGameInputData(player);
        interactorWithThreeKeys.attemptWin(inputData);

        assertTrue(mockPresenter.failViewPrepared);
        assertTrue(mockPresenter.lastErrorMessage.contains("3 more key(s)"));
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
        assertEquals(2, mockPresenter.lastOutputData.getRequiredKeys());
    }

    @Test
    void testWinConditionIsMet() {
        Player playerWithNoKeys = new Player(startLocation);
        assertFalse(winCondition.isMet(playerWithNoKeys));

        playerWithNoKeys.addKey();
        playerWithNoKeys.addKey();
        assertTrue(winCondition.isMet(playerWithNoKeys));
    }

    @Test
    void testGetRequiredKeys() {
        assertEquals(2, winCondition.getRequiredKeys());
    }

    @Test
    void testInputDataStoresPlayer() {
        Player player = new Player(startLocation);
        WinGameInputData inputData = new WinGameInputData(player);

        assertEquals(player, inputData.getPlayer());
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
        assertEquals(2, outputData.getRequiredKeys());
        assertEquals("You win!", outputData.getMessage());
    }

    @Test
    void testOutputDataCorrectlyStoresLoseState() {
        WinGameOutputData outputData = new WinGameOutputData(
                false,
                1,
                2,
                "Not enough keys"
        );

        assertFalse(outputData.isWon());
        assertEquals(1, outputData.getKeysCollected());
        assertEquals(2, outputData.getRequiredKeys());
        assertEquals("Not enough keys", outputData.getMessage());
    }

    @Test
    void testCanEnterConvocationHall() {
        Player player = new Player(startLocation);
        assertFalse(player.canEnterConvocationHall());

        player.addKey();
        assertFalse(player.canEnterConvocationHall());

        player.addKey();
        assertTrue(player.canEnterConvocationHall());
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