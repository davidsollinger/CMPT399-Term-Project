package termproject;

import Mocks.MockGUI;
import gameboardvariants.GameBoardCCLoseMoney;
import logic.GameController;
import logic.card.Card;
import logic.card.CardType;
import logic.card.MoneyCard;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class LoseMoneyCardTest {

    private GameController gameController;
    private Card loseMoneyCard;

    @Before
    public void setUp() {
        gameController = GameController.INSTANCE;
        gameController.getGameBoardController().setGameBoard(new GameBoardCCLoseMoney());
        gameController.setNumberOfPlayers(1);
        gameController.reset();
        gameController.getGUIController().setGUI(new MockGUI());
        loseMoneyCard = new MoneyCard("Pay 20 dollars", -20, CardType.COMMUNITY);
        gameController.getGameBoardController().getGameBoard().addCard(loseMoneyCard);
    }

    @Test
    public void testLoseMoneyCardAction() {
        int origMoney = gameController.getCurrentPlayer().getMoney();
        Card card = gameController.drawCCCard();
        assertEquals(loseMoneyCard, card);
        card.applyAction();
        assertEquals(origMoney - 20, gameController.getCurrentPlayer().getMoney());
    }

    @Test
    public void testLoseMoneyCardUI() {
        gameController.getPlayerController().movePlayer(0, 1);
        assertTrue(gameController.getGUIController().getGUI().isDrawCardButtonEnabled());
        assertFalse(gameController.getGUIController().getGUI().isEndTurnButtonEnabled());
        gameController.getGUIController().btnDrawCardClicked();
        assertFalse(gameController.getGUIController().getGUI().isDrawCardButtonEnabled());
        assertTrue(gameController.getGUIController().getGUI().isEndTurnButtonEnabled());
    }
}
