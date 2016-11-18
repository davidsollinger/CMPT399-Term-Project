package termproject;

import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class GameMasterTest {

    private GameMaster gameMaster;

    @Before
    public void setUp() throws Exception {
        gameMaster = GameMaster.INSTANCE;
        gameMaster.setGameBoard(new GameBoardFull());
        gameMaster.setNumberOfPlayers(2);
        gameMaster.getPlayer(0).setName("Player 1");
        gameMaster.getPlayer(1).setName("Player 2");
        gameMaster.reset();
        gameMaster.setTestMode(true);
        gameMaster.setGUI(new MockGUI());
        gameMaster.startGame();
    }

    @Test
    public void testInit() {
        assertEquals(gameMaster.getInitAmountOfMoney(),
                gameMaster.getPlayer(0).getMoney());
    }

    @Test
    public void testReset() {
        gameMaster.movePlayer(0, 3);
        gameMaster.movePlayer(1, 4);
        gameMaster.reset();
        for (int i = 0; i < gameMaster.getNumberOfPlayers(); i++) {
            Player player = gameMaster.getPlayer(i);
            assertEquals("Go", player.getPosition().getName());
        }
        assertEquals(0, gameMaster.getTurn());
    }

    @Test
    public void testTradeProcess() {
        MonopolyGUI gui = gameMaster.getGUI();
        assertTrue(gui.isTradeButtonEnabled(0));
        assertFalse(gui.isTradeButtonEnabled(1));
        gameMaster.movePlayer(0, 1);
        assertFalse(gui.isTradeButtonEnabled(0));
        assertFalse(gui.isTradeButtonEnabled(1));
        gameMaster.getCurrentPlayer().purchase();
        assertEquals(gameMaster.getGameBoard().getCell(1), gameMaster.getCurrentPlayer().getAllProperties()[0]);
        gameMaster.btnEndTurnClicked();
        TradeDialog dialog = gui.openTradeDialog();
        assertEquals(1, gameMaster.getNumberOfSellers());
        List<Player> sellerList = gameMaster.getSellerList();
        assertEquals(gameMaster.getPlayer(0), sellerList.get(0));
        TradeDeal deal = dialog.getTradeDeal();
        RespondDialog respond = gui.openRespondDialog(deal);
        Player player1 = gameMaster.getPlayer(0);
        Player player2 = gameMaster.getPlayer(1);
        assertTrue(respond.getResponse());
        gameMaster.completeTrade(deal);
        assertEquals(1440 + deal.getAmount(), player1.getMoney());
        assertEquals(1500 - deal.getAmount(), player2.getMoney());
        assertFalse(player1.checkProperty(deal.getPropertyName()));
        assertTrue(player2.checkProperty(deal.getPropertyName()));
    }

    @Test
    public void testTurn() {
        assertEquals(0, gameMaster.getTurn());
        gameMaster.switchTurn();
        assertEquals(1, gameMaster.getTurn());
        gameMaster.switchTurn();
        assertEquals(0, gameMaster.getTurn());
    }

    @Test
    public void testButtonGetOutOfJailClicked() {
        MonopolyGUI gui = gameMaster.getGUI();
        gameMaster.movePlayer(0, 30);
        gameMaster.btnEndTurnClicked();
        assertEquals("Jail", gameMaster.getPlayer(0).getPosition().getName());
        gameMaster.movePlayer(1, 2);
        gameMaster.btnEndTurnClicked();
        assertTrue(gui.isGetOutOfJailButtonEnabled());
        assertTrue(gameMaster.getPlayer(0).isInJail());
        gameMaster.btnGetOutOfJailClicked();
        assertFalse(gameMaster.getPlayer(0).isInJail());
        assertEquals(1450, gameMaster.getPlayer(0).getMoney());
    }

    @Test
    public void testButtonPurchasePropertyClicked() {
        gameMaster.movePlayer(0, 1);
        gameMaster.btnPurchasePropertyClicked();
        assertEquals(gameMaster.getGameBoard().getCell(1), gameMaster.getCurrentPlayer().getAllProperties()[0]);
        assertEquals(1440, gameMaster.getCurrentPlayer().getMoney());
    }

    @Test
    public void testButtonRollDiceClicked() {
        gameMaster.reset();
        gameMaster.btnRollDiceClicked();
        assertEquals(0, gameMaster.getCurrentPlayerIndex());
        assertEquals(gameMaster.getGameBoard().getCell(5), gameMaster.getPlayer(0).getPosition());
    }

    @Test
    public void testButtonTradeClicked() {
        gameMaster.movePlayer(0, 1);
        gameMaster.getCurrentPlayer().purchase();
        gameMaster.btnEndTurnClicked();
        gameMaster.btnTradeClicked();
        assertEquals(gameMaster.getGameBoard().getCell(1), gameMaster.getPlayer(1).getAllProperties()[0]);
        assertEquals(1640, gameMaster.getPlayer(0).getMoney());
        assertEquals(1300, gameMaster.getPlayer(1).getMoney());
    }
}
