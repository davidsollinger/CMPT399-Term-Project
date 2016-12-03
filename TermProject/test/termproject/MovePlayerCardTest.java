package termproject;

import Mocks.MockGUI;
import gameboardvariants.GameBoardCCMovePlayer;
import controller.GameController;
import logic.card.Card;
import logic.card.CardType;
import logic.card.MovePlayerCard;
import logic.cell.Cell;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class MovePlayerCardTest {

    private GameController gameController;
    private Card movePlayerCard;

    @Before
    public void setUp() {
        gameController = GameController.INSTANCE;
        gameController.getGameBoardController().setGameBoard(new GameBoardCCMovePlayer());
        gameController.setNumberOfPlayers(1);
        gameController.reset();
        gameController.getGUIController().setGUI(new MockGUI());
        movePlayerCard = new MovePlayerCard("Blue 1", CardType.COMMUNITY);
        gameController.getGameBoardController().getGameBoard().addCard(movePlayerCard);
    }

    @Test
    public void testJailCardLabel() {
        assertEquals("Go to Blue 1", movePlayerCard.getLabel());
    }

    @Test
    public void testMovePlayerCardAction() {
        Card card = gameController.drawCCCard();
        assertEquals(movePlayerCard, card);

        card.applyAction();
        Cell cell = gameController.getCurrentPlayer().getPosition();
        assertEquals(gameController.getGameBoardController().getGameBoard().queryCell("Blue 1"), cell);
    }

    @Test
    public void testMovePlayerCardUI() {
        gameController.getPlayerController().movePlayer(0, 2);
        assertTrue(gameController.getGUIController().getGUI().isDrawCardButtonEnabled());
        assertFalse(gameController.getGUIController().getGUI().isEndTurnButtonEnabled());

        gameController.getGUIController().btnDrawCardClicked();
        assertFalse(gameController.getGUIController().getGUI().isDrawCardButtonEnabled());

        Cell cell = gameController.getCurrentPlayer().getPosition();
        assertEquals(gameController.getGameBoardController().getGameBoard().queryCell("Blue 1"), cell);
        assertTrue(gameController.getGUIController().getGUI().isEndTurnButtonEnabled());
        assertEquals(1700, gameController.getCurrentPlayer().getMoney());
    }
}
