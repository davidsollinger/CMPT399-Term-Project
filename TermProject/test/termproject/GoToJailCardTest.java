package termproject;

import Mocks.MockGUI;
import controller.GameController;
import gameboardvariants.GameBoardCCJail;
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

    private final Card jailCard = new JailCard(CardType.COMMUNITY);
    
    private GameController gameController;

    @Before
    public void setUp() {
        gameController = GameController.INSTANCE;
        gameController.getGameBoardController().setGameBoard(new GameBoardCCJail());
        gameController.setNumberOfPlayers(1);
        gameController.reset();
        gameController.getGUIController().setGUI(new MockGUI());
        gameController.getGameBoardController().getGameBoard().addCard(jailCard);
    }

    @Test
    public void testJailCardAction() {
        Card card = gameController.drawCCCard();
        assertEquals(jailCard, card);
        card.applyAction();
        Cell cell = gameController.getCurrentPlayer().getPosition();
        assertEquals(gameController.getGameBoardController().getGameBoard().queryCell("Jail"), cell);
    }

    @Test
    public void testJailCardLabel() {
        assertEquals("Go directly to Jail. Do not pass go,"
                + " do not collect $200", jailCard.getLabel());
    }

    @Test
    public void testJailCardUI() {
        gameController.getPlayerController().movePlayer(0, 1);
        assertTrue(gameController.getGUIController().getGUI().isDrawCardButtonEnabled());
        assertFalse(gameController.getGUIController().getGUI().isEndTurnButtonEnabled());
        gameController.getGUIController().btnDrawCardClicked();
        assertFalse(gameController.getGUIController().getGUI().isDrawCardButtonEnabled());
        Cell cell = gameController.getCurrentPlayer().getPosition();
        assertEquals(gameController.getGameBoardController().getGameBoard().queryCell("Jail"), cell);
        assertTrue(gameController.getGUIController().getGUI().isEndTurnButtonEnabled());
    }
}
