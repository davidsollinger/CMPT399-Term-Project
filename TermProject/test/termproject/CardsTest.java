package termproject;

import Mocks.MockGUI;
import gameboardvariants.GameBoardCCGainMoney;
import logic.GameController;
import logic.card.Card;
import logic.card.CardType;
import logic.card.MoneyCard;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class CardsTest {

    private Card ccCard, chanceCard;
    private GameController gameController;

    @Before
    public void setUp() {
        gameController = GameController.INSTANCE;
        gameController.getGameBoardController().setGameBoard(new GameBoardCCGainMoney());
        gameController.setNumberOfPlayers(1);
        gameController.reset();
        gameController.getGUIController().setGUI(new MockGUI());
        ccCard = new MoneyCard("Get 50 dollars", 50, CardType.COMMUNITY);
        chanceCard = new MoneyCard("Lose 50 dollars", -50, CardType.CHANCE);
        gameController.getGameBoardController().getGameBoard().addCard(ccCard);
    }

    @Test
    public void testCardTypeCC() {
        assertEquals(CardType.COMMUNITY, ccCard.getCardType());
    }

    @Test
    public void testCardTypeCHANCE() {
        assertEquals(CardType.CHANCE, chanceCard.getCardType());
    }
}
