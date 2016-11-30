package termproject;

import Mocks.MockGUI;
import gameboardvariants.GameBoardCCJail;
import logic.GameMaster;
import logic.card.Card;
import logic.card.CardType;
import logic.card.JailCard;
import logic.cell.Cell;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class GoToJailCardTest {

    private GameMaster gameMaster;
    private final Card jailCard = new JailCard(CardType.COMMUNITY);

    @Before
    public void setUp() {
        gameMaster = GameMaster.INSTANCE;
        gameMaster.setGameBoard(new GameBoardCCJail());
        gameMaster.setNumberOfPlayers(1);
        gameMaster.reset();
        gameMaster.setGUI(new MockGUI());
        gameMaster.getGameBoard().addCard(jailCard);
    }

    @Test
    public void testJailCardAction() {
        Card card = gameMaster.drawCCCard();
        assertEquals(jailCard, card);
        card.applyAction();
        Cell cell = gameMaster.getCurrentPlayer().getPosition();
        assertEquals(gameMaster.getGameBoard().queryCell("Jail"), cell);
    }

    @Test
    public void testJailCardLabel() {
        assertEquals("Go directly to Jail. Do not pass go,"
                + " do not collect $200", jailCard.getLabel());
    }

    @Test
    public void testJailCardUI() {
        gameMaster.movePlayer(0, 1);
        assertTrue(gameMaster.getGUI().isDrawCardButtonEnabled());
        assertFalse(gameMaster.getGUI().isEndTurnButtonEnabled());
        gameMaster.btnDrawCardClicked();
        assertFalse(gameMaster.getGUI().isDrawCardButtonEnabled());
        Cell cell = gameMaster.getCurrentPlayer().getPosition();
        assertEquals(gameMaster.getGameBoard().queryCell("Jail"), cell);
        assertTrue(gameMaster.getGUI().isEndTurnButtonEnabled());
    }
}
