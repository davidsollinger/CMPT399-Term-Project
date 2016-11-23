package termproject;

import Mocks.MockGUI;
import gameboardvariants.GameBoardCCGainMoney;
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
        ccCard = new MoneyCard("Get 50 dollars", 50, CardType.COMMUNITY);
        chanceCard = new MoneyCard("Lose 50 dollars", -50, CardType.CHANCE);
        gameMaster.getGameBoard().addCard(ccCard);
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
