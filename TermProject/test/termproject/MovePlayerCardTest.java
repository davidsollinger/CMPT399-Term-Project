package termproject;

import Mocks.MockGUI;
import gameboardvariants.GameBoardCCMovePlayer;
import logic.GameMaster;
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

    private GameMaster gameMaster;
    private Card movePlayerCard;

    @Before
    public void setUp() {
        gameMaster = GameMaster.INSTANCE;
        gameMaster.setGameBoard(new GameBoardCCMovePlayer());
        gameMaster.setNumberOfPlayers(1);
        gameMaster.reset();
        gameMaster.setGUI(new MockGUI());
        movePlayerCard = new MovePlayerCard("Blue 1", CardType.COMMUNITY);
        gameMaster.getGameBoard().addCard(movePlayerCard);
    }

    @Test
    public void testJailCardLabel() {
        assertEquals("Go to Blue 1", movePlayerCard.getLabel());
    }

    @Test
    public void testMovePlayerCardAction() {
        Card card = gameMaster.drawCCCard();
        assertEquals(movePlayerCard, card);

        card.applyAction();
        Cell cell = gameMaster.getCurrentPlayer().getPosition();
        assertEquals(gameMaster.getGameBoard().queryCell("Blue 1"), cell);
    }

    @Test
    public void testMovePlayerCardUI() {
        gameMaster.movePlayer(0, 2);
        assertTrue(gameMaster.getGUI().isDrawCardButtonEnabled());
        assertFalse(gameMaster.getGUI().isEndTurnButtonEnabled());

        gameMaster.btnDrawCardClicked();
        assertFalse(gameMaster.getGUI().isDrawCardButtonEnabled());

        Cell cell = gameMaster.getCurrentPlayer().getPosition();
        assertEquals(gameMaster.getGameBoard().queryCell("Blue 1"), cell);
        assertTrue(gameMaster.getGUI().isEndTurnButtonEnabled());
        assertEquals(1700, gameMaster.getCurrentPlayer().getMoney());
    }
}
