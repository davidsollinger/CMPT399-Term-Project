package termproject;

import Mocks.MockGUI;
import java.util.List;
import controller.GameController;
import gui.MonopolyGUI;
import logic.trade.RespondDialog;
import logic.gameBoard.GameBoardView;
import logic.player.Player;
import logic.trade.TradeDeal;
import logic.trade.TradeDialog;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GameControllerTest {

    private GameController gameController;

    @Before
    public void setUp() throws Exception {
        gameController = GameController.INSTANCE;
        gameController.getGameBoardController().setGameBoard(new GameBoardView());
        gameController.setNumberOfPlayers(2);
        gameController.getPlayerController().getPlayer(0).setName("Player 1");
        gameController.getPlayerController().getPlayer(1).setName("Player 2");
        gameController.reset();
        gameController.setTestMode(true);
        gameController.getGUIController().setGUI(new MockGUI());
        gameController.startGame();
    }

    @Test
    public void testInit() {
        assertEquals(gameController.getInitAmountOfMoney(),
                gameController.getPlayerController().getPlayer(0).getMoney());
    }

    @Test
    public void testReset() {
        gameController.getPlayerController().movePlayer(0, 3);
        gameController.getPlayerController().movePlayer(1, 4);
        gameController.reset();
        for (int i = 0; i < gameController.getNumberOfPlayers(); i++) {
            Player player = gameController.getPlayerController().getPlayer(i);
            assertEquals("Go", player.getPosition().getName());
        }
        assertEquals(0, gameController.getTurn());
    }

    @Test
    public void testTradeProcess() {
        MonopolyGUI gui = gameController.getGUIController().getGUI();
        assertFalse(gui.isTradeButtonEnabled(0));
        assertFalse(gui.isTradeButtonEnabled(1));
        gameController.getPlayerController().movePlayer(0, 1);
        assertFalse(gui.isTradeButtonEnabled(0));
        assertFalse(gui.isTradeButtonEnabled(1));
        gameController.getCurrentPlayer().getActions().purchase();
        assertEquals(gameController.getGameBoardController().getGameBoard().getCell(1), gameController.getCurrentPlayer().getProperty().getAllProperties()[0]);

        gameController.getGUIController().btnEndTurnClicked();
        TradeDialog dialog = gui.openTradeDialog();
        assertEquals(1, gameController.getPlayerController().getNumberOfSellers());
        List<Player> sellerList = gameController.getPlayerController().getSellerList();
        assertEquals(gameController.getPlayerController().getPlayer(0), sellerList.get(0));
        TradeDeal deal = dialog.getTradeDeal();
        RespondDialog respond = gui.openRespondDialog(deal);
        Player player1 = gameController.getPlayerController().getPlayer(0);
        Player player2 = gameController.getPlayerController().getPlayer(1);
        assertTrue(respond.hasResponded());
        gameController.completeTrade(deal);
        assertEquals(1440 + deal.getAmount(), player1.getMoney());
        assertEquals(1500 - deal.getAmount(), player2.getMoney());
        assertFalse(player1.getProperty().checkProperty(deal.getPropertyName()));
        assertTrue(player2.getProperty().checkProperty(deal.getPropertyName()));
    }

    @Test
    public void testTurn() {
        assertEquals(0, gameController.getTurn());
        gameController.switchTurn();
        assertEquals(1, gameController.getTurn());
        gameController.switchTurn();
        assertEquals(0, gameController.getTurn());
    }

    @Test
    public void testButtonGetOutOfJailClicked() {
        MonopolyGUI gui = gameController.getGUIController().getGUI();
        gameController.getPlayerController().movePlayer(0, 30);
        gameController.getGUIController().btnEndTurnClicked();
        assertEquals("Jail", gameController.getPlayerController().getPlayer(0).getPosition().getName());
        gameController.getPlayerController().movePlayer(1, 2);
        gameController.getGUIController().btnEndTurnClicked();
        assertTrue(gui.isGetOutOfJailButtonEnabled());
        assertTrue(gameController.getPlayerController().getPlayer(0).isInJail());
        gameController.getGUIController().btnGetOutOfJailClicked();
        assertFalse(gameController.getPlayerController().getPlayer(0).isInJail());
        assertEquals(1450, gameController.getPlayerController().getPlayer(0).getMoney());
    }

    @Test
    public void testButtonPurchasePropertyClicked() {
        gameController.getPlayerController().movePlayer(0, 1);
        gameController.getGUIController().btnPurchasePropertyClicked();
        assertEquals(gameController.getGameBoardController().getGameBoard().getCell(1), gameController.getCurrentPlayer().getProperty().getAllProperties()[0]);
        assertEquals(1440, gameController.getCurrentPlayer().getMoney());
    }

    @Test
    public void testButtonRollDiceClicked() {
        gameController.reset();
        gameController.getGUIController().btnRollDiceClicked();
        assertEquals(0, gameController.getTurn());
        assertEquals(gameController.getGameBoardController().getGameBoard().getCell(5), gameController.getPlayerController().getPlayer(0).getPosition());
    }

    @Test
    public void testButtonTradeClicked() {
        gameController.getPlayerController().movePlayer(0, 1);
        gameController.getCurrentPlayer().getActions().purchase();
        gameController.getGUIController().btnEndTurnClicked();
        gameController.getGUIController().btnTradeClicked();
        assertEquals(gameController.getGameBoardController().getGameBoard().getCell(1), gameController.getPlayerController().getPlayer(1).getProperty().getAllProperties()[0]);
        assertEquals(1640, gameController.getPlayerController().getPlayer(0).getMoney());
        assertEquals(1300, gameController.getPlayerController().getPlayer(1).getMoney());
    }
}
