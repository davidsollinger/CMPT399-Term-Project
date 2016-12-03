package termproject;

import Mocks.MockGUI;
import gameboardvariants.GameBoardCCGainMoney;
import logic.GameController;
import logic.card.Card;
import logic.card.CardType;
import logic.card.MoneyCard;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class GainMoneyCardTest {

    private Card gainMoneyCard;
    private GameController gameController;

    @Before
    public void setUp() {
        gameController = GameController.INSTANCE;
        gameController.getGameBoardController().setGameBoard(new GameBoardCCGainMoney());
        gameController.setNumberOfPlayers(1);
        gameController.reset();
        gameController.getGUIController().setGUI(new MockGUI());
        gainMoneyCard = new MoneyCard("Get 50 dollars", 50, CardType.COMMUNITY);
        gameController.getGameBoardController().getGameBoard().addCard(gainMoneyCard);
    }

    @Test
    public void testGainMoneyCardAction() {
        int origMoney = gameController.getCurrentPlayer().getMoney();
        Card card = gameController.drawCCCard();
        card.applyAction();
        assertEquals(origMoney + 50, gameController.getCurrentPlayer().getMoney());
    }

    @Test
    public void testGainMoneyCard() {
        Card card = gameController.drawCCCard();
        assertEquals(gainMoneyCard, card);
    }

    @Test
    public void testGainMoneyCardDrawCardButtonEnabled() {
        gameController.getPlayerController().movePlayer(0, 1);
        assertTrue(gameController.getGUIController().getGUI().isDrawCardButtonEnabled());
    }

    @Test
    public void testGainMoneyCardEndTurnButtonDisabled() {
        gameController.getPlayerController().movePlayer(0, 1);
        assertFalse(gameController.getGUIController().getGUI().isEndTurnButtonEnabled());
    }

    @Test
    public void testGainMoneyCardDrawCardButtonDisabled() {
        gameController.getPlayerController().movePlayer(0, 1);
        gameController.getGUIController().btnDrawCardClicked();
        assertFalse(gameController.getGUIController().getGUI().isDrawCardButtonEnabled());
    }

    @Test
    public void testGainMoneyCardEndTurnButtonEnabled() {
        gameController.getPlayerController().movePlayer(0, 1);
        gameController.getGUIController().btnDrawCardClicked();
        assertTrue(gameController.getGUIController().getGUI().isEndTurnButtonEnabled());
    }
}
