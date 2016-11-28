package termproject;

import logic.card.CardType;
import logic.card.MoneyCard;
import logic.card.Card;
import logic.GameMaster;
import Mocks.MockGUI;
import gameboardvariants.GameBoardCCLoseMoney;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class LoseMoneyCardTest {

    private GameMaster gameMaster;
    private Card loseMoneyCard;

    @Before
    public void setUp() {
        gameMaster = GameMaster.INSTANCE;
        gameMaster.setGameBoard(new GameBoardCCLoseMoney());
        gameMaster.setNumberOfPlayers(1);
        gameMaster.reset();
        gameMaster.setGUI(new MockGUI());
        loseMoneyCard = new MoneyCard("Pay 20 dollars", -20, CardType.COMMUNITY);
        gameMaster.getGameBoard().addCard(loseMoneyCard);
    }

    @Test
    public void testLoseMoneyCardAction() {
        int origMoney = gameMaster.getCurrentPlayer().getMoney();
        Card card = gameMaster.drawCCCard();
        assertEquals(loseMoneyCard, card);
        card.applyAction();
        assertEquals(origMoney - 20, gameMaster.getCurrentPlayer().getMoney());
    }

    @Test
    public void testLoseMoneyCardUI() {
        gameMaster.movePlayer(0, 1);
        assertTrue(gameMaster.getGUI().isDrawCardButtonEnabled());
        assertFalse(gameMaster.getGUI().isEndTurnButtonEnabled());
        gameMaster.btnDrawCardClicked();
        assertFalse(gameMaster.getGUI().isDrawCardButtonEnabled());
        assertTrue(gameMaster.getGUI().isEndTurnButtonEnabled());
    }
}
