package termproject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class PlayerTest {

    private GameMaster gameMaster;

    @Before
    public void setUp() throws Exception {
        gameMaster = GameMaster.INSTANCE;
        gameMaster.setGameBoard(new SimpleGameBoard());
        gameMaster.setGUI(new MockGUI());
        gameMaster.setTestMode(true);
        gameMaster.reset();
    }
    
    @Test
    public void testPurchaseProperty() {
        gameMaster.setNumberOfPlayers(1);
        gameMaster.movePlayer(0, 3);
        Player player = gameMaster.getPlayer(0);
        player.purchase();
        assertEquals(1380, player.getMoney());
        assertEquals("Blue 3", player.getProperty(0).getName());
        PropertyCell cell
                = (PropertyCell) gameMaster.getGameBoard().queryCell("Blue 3");
        assertSame(player, cell.getPlayer());
    }

    @Test
    public void testSameGoCell() {
        GameBoard gameboard = gameMaster.getGameBoard();
        Player player1 = new Player();
        Player player2 = new Player();
        Cell go = gameboard.queryCell("Go");
        assertSame(go, player1.getPosition());
        assertSame(go, player2.getPosition());
    }

    @Test
    public void testPayRentTo() { // Not working
        gameMaster.setNumberOfPlayers(2);
        gameMaster.movePlayer(0, 4);
        gameMaster.getCurrentPlayer().purchase();
        gameMaster.btnEndTurnClicked();
        gameMaster.movePlayer(1, 4);
        gameMaster.btnEndTurnClicked();
        assertTrue(gameMaster.getPlayer(1).isBankrupt());
        assertEquals(2800, gameMaster.getPlayer(0).getMoney());
    }

    @Test
    public void testExchangeProperty() {
        gameMaster.setNumberOfPlayers(2);
        gameMaster.movePlayer(0, 3);
        gameMaster.getCurrentPlayer().purchase();
        gameMaster.btnEndTurnClicked();
        gameMaster.getPlayer(0).exchangeProperty(gameMaster.getPlayer(1));
        assertEquals(1, gameMaster.getCurrentPlayer().getPropertyNumber());
    }

    public void testPurchaseHouse() {
        gameMaster.setNumberOfPlayers(1);
        gameMaster.startGame();
        gameMaster.movePlayer(gameMaster.getCurrentPlayerIndex(), 1);
        gameMaster.getCurrentPlayer().purchase();
        gameMaster.btnEndTurnClicked();
        gameMaster.movePlayer(0, 1);
        gameMaster.getCurrentPlayer().purchase();
        gameMaster.btnEndTurnClicked();
        gameMaster.movePlayer(0, 1);
        gameMaster.getCurrentPlayer().purchase();
        gameMaster.btnEndTurnClicked();
        gameMaster.getCurrentPlayer().purchaseHouse("blue", 2);
        assertEquals("blue", gameMaster.getCurrentPlayer().getMonopolies()[0]);
        assertEquals(880, gameMaster.getCurrentPlayer().getMoney());
    }

    @Test
    public void testResetProperty() {
        gameMaster.setNumberOfPlayers(1);
        gameMaster.movePlayer(0, 1);
        gameMaster.getCurrentPlayer().purchase();
        assertEquals(gameMaster.getGameBoard().getCell(1), gameMaster.getCurrentPlayer().getAllProperties()[0]);
        gameMaster.getCurrentPlayer().resetProperty();
        assertEquals(0, gameMaster.getCurrentPlayer().getAllProperties().length);
    }
}
