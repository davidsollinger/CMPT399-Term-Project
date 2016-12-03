package termproject;

import Mocks.MockGUI;
import java.awt.Color;
import controller.GameController;
import logic.cell.Cell;
import logic.cell.PropertyCell;
import logic.gameBoard.GameBoard;
import logic.gameBoard.SimpleGameBoard;
import logic.player.Player;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class PlayerTest {

    private GameController gameController;

    @Before
    public void setUp() throws Exception {
        gameController = GameController.INSTANCE;
        gameController.getGameBoardController().setGameBoard(new SimpleGameBoard());
        gameController.getGUIController().setGUI(new MockGUI());
        gameController.setTestMode(true);
        gameController.reset();
    }

    @Test
    public void testPurchaseProperty() {
        gameController.setNumberOfPlayers(1);
        gameController.getPlayerController().movePlayer(0, 3);
        Player player = gameController.getPlayerController().getPlayer(0);
        player.getActions().purchase();
        assertEquals(1380, player.getMoney());
        assertEquals("Blue 3", player.getProperty().getPropertyCell(0).getName());
        PropertyCell cell
                = (PropertyCell) gameController.getGameBoardController().getGameBoard().queryCell("Blue 3");
        assertSame(player, cell.getPlayer());
    }

    @Test
    public void testSameGoCell() {
        GameBoard gameboard = gameController.getGameBoardController().getGameBoard();
        Player player1 = new Player();
        Player player2 = new Player();
        Cell go = gameboard.queryCell("Go");
        assertSame(go, player1.getPosition());
        assertSame(go, player2.getPosition());
    }

    @Test
    public void testPayRentTo() {
        gameController.setNumberOfPlayers(2);
        gameController.getPlayerController().movePlayer(0, 4);
        gameController.getCurrentPlayer().getActions().purchase();
        gameController.getGUIController().btnEndTurnClicked();
        gameController.getPlayerController().movePlayer(1, 4);
        gameController.getGUIController().btnEndTurnClicked();
        assertTrue(gameController.getPlayerController().getPlayer(1).isBankrupt());
        assertEquals(2800, gameController.getPlayerController().getPlayer(0).getMoney());
    }

    @Test
    public void testExchangeProperty() {
        gameController.setNumberOfPlayers(2);
        gameController.getPlayerController().movePlayer(0, 3);
        gameController.getCurrentPlayer().getActions().purchase();
        gameController.getGUIController().btnEndTurnClicked();
        gameController.getPlayerController().getPlayer(0).getProperty().
                exchangeProperty(gameController.getPlayerController().getPlayer(1));
        assertEquals(1, gameController.getCurrentPlayer().getProperty().getNumberOfProperties());
    }

    public void testPurchaseHouse() {
        gameController.setNumberOfPlayers(1);
        gameController.startGame();
        gameController.getPlayerController().movePlayer(gameController.getTurn(), 1);
        gameController.getCurrentPlayer().getActions().purchase();
        gameController.getGUIController().btnEndTurnClicked();
        gameController.getPlayerController().movePlayer(0, 1);
        gameController.getCurrentPlayer().getActions().purchase();
        gameController.getGUIController().btnEndTurnClicked();
        gameController.getPlayerController().movePlayer(0, 1);
        gameController.getCurrentPlayer().getActions().purchase();
        gameController.getGUIController().btnEndTurnClicked();
        assertEquals(true, gameController.getCurrentPlayer().canBuyHouse());
        gameController.getCurrentPlayer().getProperty().addHouse("blue", 2);
        assertEquals("blue", gameController.getCurrentPlayer().getProperty().getMonopolies()[0]);
        assertEquals(880, gameController.getCurrentPlayer().getMoney());
    }

    @Test
    public void testResetProperty() {
        gameController.setNumberOfPlayers(1);
        gameController.getPlayerController().movePlayer(0, 1);
        gameController.getCurrentPlayer().getActions().purchase();
        assertEquals(gameController.getGameBoardController().getGameBoard().getCell(1),
                gameController.getCurrentPlayer().getProperty().getAllProperties()[0]);
        gameController.getCurrentPlayer().getProperty().resetProperty();
        assertEquals(0, gameController.getCurrentPlayer().getProperty().getAllProperties().length);
    }

    @Test
    public void testPlayerColor() {
        Color playerColors = new Color(0, 128, 0, 75);
        Player player = new Player();
        player.setPlayerColor(new Color(0, 128, 0, 75));
        assertEquals(playerColors, player.getPlayerColor());
    }
}
