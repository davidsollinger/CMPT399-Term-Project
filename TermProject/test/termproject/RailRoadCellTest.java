package termproject;

import Mocks.MockGUI;
import gameboardvariants.GameBoardRailRoad;
import logic.GameController;
import logic.cell.RailRoadCell;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class RailRoadCellTest {

    private GameController gameController;

    @Before
    public void setUp() {
        gameController = GameController.INSTANCE;
        gameController.getGameBoardController().setGameBoard(new GameBoardRailRoad());
        gameController.setNumberOfPlayers(2);
        gameController.reset();
        gameController.getGUIController().setGUI(new MockGUI());
    }

    @Test
    public void testPlayerAction() {
        RailRoadCell cell
                = (RailRoadCell) gameController.getGameBoardController().getGameBoard().queryCell("Railroad A");
        int cellIndex = gameController.getGameBoardController().getGameBoard().queryCellIndex("Railroad A");
        gameController.getPlayerController().movePlayer(0, cellIndex);
        gameController.getPlayerController().getPlayer(0).getActions().purchase();
        gameController.switchTurn();
        gameController.getPlayerController().movePlayer(1, cellIndex);
        cell.playAction();
        assertEquals(1500 - cell.getRent(), gameController.getPlayerController().getPlayer(1).getMoney());
        assertEquals(1300 + cell.getRent(), gameController.getPlayerController().getPlayer(0).getMoney());
    }

    @Test
    public void testPurchaseRailroad() {
        assertEquals(0, gameController.getPlayerController().getPlayer(0).getProperty().getNumberOfRR());
        int cellIndex = gameController.getGameBoardController().getGameBoard().queryCellIndex("Railroad A");
        gameController.getPlayerController().movePlayer(0, cellIndex);
        gameController.getPlayerController().getPlayer(0).getActions().purchase();
        assertEquals(1300, gameController.getPlayerController().getPlayer(0).getMoney());
        assertEquals(1, gameController.getPlayerController().getPlayer(0).getProperty().getNumberOfRR());
    }

    @Test
    public void testRent() {
        RailRoadCell rr1
                = (RailRoadCell) gameController.getGameBoardController().getGameBoard().queryCell("Railroad A");
        int cellIndex1 = gameController.getGameBoardController().getGameBoard().queryCellIndex("Railroad A");
        gameController.getPlayerController().movePlayer(0, cellIndex1);
        gameController.getPlayerController().getPlayer(0).getActions().purchase();
        assertEquals(25, rr1.getRent());

        RailRoadCell rr2
                = (RailRoadCell) gameController.getGameBoardController().getGameBoard().queryCell("Railroad B");
        int cellIndex2 = gameController.getGameBoardController().getGameBoard().queryCellIndex("Railroad B");
        gameController.getPlayerController().movePlayer(0, cellIndex2 - cellIndex1);
        gameController.getPlayerController().getPlayer(0).getActions().purchase();
        assertEquals(50, rr1.getRent());
        assertEquals(50, rr2.getRent());
    }
}
