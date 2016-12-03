package termproject;

import Mocks.MockGUI;
import gameboardvariants.GameBoardUtility;
import logic.GameController;
import logic.cell.UtilityCell;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import org.junit.Before;
import org.junit.Test;

public class UtilityCellTest {

    private GameController gameController;

    @Before
    public void setUp() {
        gameController = GameController.INSTANCE;
        gameController.getGameBoardController().setGameBoard(new GameBoardUtility());
        gameController.setNumberOfPlayers(2);
        gameController.reset();
        gameController.getGUIController().setGUI(new MockGUI());
    }

    @Test
    public void testMonopoly() {
        int u1CellIndex = gameController.getGameBoardController().getGameBoard().queryCellIndex("Utility 1");
        gameController.getPlayerController().movePlayer(0, u1CellIndex);
        gameController.getPlayerController().getPlayer(0).getActions().purchase();
        int u2CellIndex = gameController.getGameBoardController().getGameBoard().queryCellIndex("Utility 2");
        gameController.getPlayerController().movePlayer(0, u2CellIndex - u1CellIndex);
        gameController.getPlayerController().getPlayer(0).getActions().purchase();
        assertFalse(gameController.getPlayerController().getPlayer(0).canBuyHouse());
    }

    @Test
    public void testPlayerAction() {
        UtilityCell cell
                = (UtilityCell) gameController.getGameBoardController().getGameBoard().queryCell("Utility 1");
        int cellIndex = gameController.getGameBoardController().getGameBoard().queryCellIndex("Utility 1");
        gameController.getPlayerController().movePlayer(0, cellIndex);
        gameController.getPlayerController().getPlayer(0).getActions().purchase();
        gameController.switchTurn();
        gameController.getPlayerController().movePlayer(1, cellIndex);
        cell.playAction();
        int diceRoll = gameController.getUtilDiceRoll();
        assertEquals(1500 - cell.getRent(diceRoll), gameController.getPlayerController().getPlayer(1).getMoney());
        assertEquals(1350 + cell.getRent(diceRoll), gameController.getPlayerController().getPlayer(0).getMoney());
    }

    @Test
    public void testPurchaseUtility() {
        assertEquals(0, gameController.getPlayerController().getPlayer(0).getProperty().getNumberOfUtil());
        int cellIndex = gameController.getGameBoardController().getGameBoard().queryCellIndex("Utility 1");
        gameController.getPlayerController().movePlayer(0, cellIndex);
        gameController.getPlayerController().getPlayer(0).getActions().purchase();
        assertEquals(1350, gameController.getPlayerController().getPlayer(0).getMoney());
        assertEquals(1, gameController.getPlayerController().getPlayer(0).getProperty().getNumberOfUtil());
    }

    @Test
    public void testRent() {
        UtilityCell u1
                = (UtilityCell) gameController.getGameBoardController().getGameBoard().queryCell("Utility 1");
        int cellIndex1 = gameController.getGameBoardController().getGameBoard().queryCellIndex("Utility 1");
        gameController.getPlayerController().movePlayer(0, cellIndex1);
        gameController.getPlayerController().getPlayer(0).getActions().purchase();
        assertEquals(40, u1.getRent(10));

        UtilityCell u2
                = (UtilityCell) gameController.getGameBoardController().getGameBoard().queryCell("Utility 2");
        int cellIndex2 = gameController.getGameBoardController().getGameBoard().queryCellIndex("Utility 2");
        gameController.getPlayerController().movePlayer(0, cellIndex2 - cellIndex1);
        gameController.getPlayerController().getPlayer(0).getActions().purchase();
        assertEquals(100, u1.getRent(10));
        assertEquals(100, u2.getRent(10));
    }
}
