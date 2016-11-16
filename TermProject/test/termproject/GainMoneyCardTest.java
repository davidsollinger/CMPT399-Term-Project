package termproject;

import gameboardvariants.GameBoardCCGainMoney;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class GainMoneyCardTest {

    private Card gainMoneyCard;
    private GameMaster gameMaster;

    @Before
    public void setUp() {
        gameMaster = GameMaster.INSTANCE;
        gameMaster.setGameBoard(new GameBoardCCGainMoney());
        gameMaster.setNumberOfPlayers(1);
        gameMaster.reset();
        gameMaster.setGUI(new MockGUI());
        gainMoneyCard = new MoneyCard("Get 50 dollars", 50, Card.TYPE_CC);
        gameMaster.getGameBoard().addCard(gainMoneyCard);
    }

    @Test
    public void testGainMoneyCardAction() {
        int origMoney = gameMaster.getCurrentPlayer().getMoney();
        Card card = gameMaster.drawCCCard();
        card.applyAction();
        assertEquals(origMoney + 50, gameMaster.getCurrentPlayer().getMoney());
    }
    
    @Test
    public void testGainMoneyCard() {
        Card card = gameMaster.drawCCCard();
        assertEquals(gainMoneyCard, card);
    }

    @Test
    public void testGainMoneyCardDrawCardButtonEnabled() {
        gameMaster.movePlayer(0, 1);
        assertTrue(gameMaster.getGUI().isDrawCardButtonEnabled());
    }
    
    @Test
    public void testGainMoneyCardEndTurnButtonDisabled() {
        gameMaster.movePlayer(0, 1);
        assertFalse(gameMaster.getGUI().isEndTurnButtonEnabled());
    }
    
    @Test
    public void testGainMoneyCardDrawCardButtonDisabled() {
        gameMaster.movePlayer(0, 1);
        gameMaster.btnDrawCardClicked();
        assertFalse(gameMaster.getGUI().isDrawCardButtonEnabled());
    }
    
    @Test
    public void testGainMoneyCardEndTurnButtonEnabled() {
        gameMaster.movePlayer(0, 1);
        gameMaster.btnDrawCardClicked();
        assertTrue(gameMaster.getGUI().isEndTurnButtonEnabled());
    }
}
