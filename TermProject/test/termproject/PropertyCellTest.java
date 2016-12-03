package termproject;

import Mocks.MockGUI;
import logic.GameController;
import logic.cell.PropertyCell;
import logic.gameBoard.SimpleGameBoard;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class PropertyCellTest {

    private GameController gameController;

    @Before
    public void setUp() {
        gameController = GameController.INSTANCE;
        gameController.getGameBoardController().setGameBoard(new SimpleGameBoard());
        gameController.setNumberOfPlayers(2);
        gameController.reset();
        gameController.getGUIController().setGUI(new MockGUI());
    }

    @Test
    public void testPlayerAction() {
        PropertyCell cell
                = (PropertyCell) gameController.getGameBoardController().getGameBoard().queryCell("Blue 3");
        int cellIndex = gameController.getGameBoardController().getGameBoard().queryCellIndex("Blue 3");
        gameController.getPlayerController().movePlayer(0, cellIndex);
        gameController.getPlayerController().getPlayer(0).getActions().purchase();
        gameController.switchTurn();
        gameController.getPlayerController().movePlayer(1, cellIndex);
        cell.playAction();
        assertEquals(1500 - cell.getRent(), gameController.getPlayerController().getPlayer(1).getMoney());
        assertEquals(1380 + cell.getRent(), gameController.getPlayerController().getPlayer(0).getMoney());
    }
}
