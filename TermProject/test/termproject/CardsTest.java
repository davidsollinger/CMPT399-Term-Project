package termproject;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class CardsTest {

    private Card ccCard, chanceCard;
    private GameMaster gameMaster;

    @Before
    public void setUp() {
        gameMaster = GameMaster.INSTANCE;
        gameMaster.setGameBoard(new GameBoardCCGainMoney());
        gameMaster.setNumberOfPlayers(1);
        gameMaster.reset();
        gameMaster.setGUI(new MockGUI());
        ccCard = new MoneyCard("Get 50 dollars", 50, Card.TYPE_CC);
        chanceCard = new MoneyCard("Lose 50 dollars", -50, Card.TYPE_CHANCE);
        gameMaster.getGameBoard().addCard(ccCard);
    }

    @Test
    public void testCardTypeCC() {
        assertEquals(Card.TYPE_CC, ccCard.getCardType());
    }

    @Test
    public void testCardTypeCHANCE() {
        assertEquals(Card.TYPE_CHANCE, chanceCard.getCardType());
    }
}
