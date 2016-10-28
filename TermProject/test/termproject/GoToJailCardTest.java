package termproject;

import junit.framework.TestCase;

public class GoToJailCardTest extends TestCase {

    private GameMaster gameMaster;
    private final Card jailCard = new JailCard(Card.TYPE_CC);

    @Override
    protected void setUp() {
        gameMaster = GameMaster.INSTANCE;
        gameMaster.setGameBoard(new GameBoardCCJail());
        gameMaster.setNumberOfPlayers(1);
        gameMaster.reset();
        gameMaster.setGUI(new MockGUI());
        gameMaster.getGameBoard().addCard(jailCard);
    }

    public void testJailCardAction() {
        Card card = gameMaster.drawCCCard();
        assertEquals(jailCard, card);
        card.applyAction();
        Cell cell = gameMaster.getCurrentPlayer().getPosition();
        assertEquals(gameMaster.getGameBoard().queryCell("Jail"), cell);
    }

    public void testJailCardLabel() {
        assertEquals("Go to Jail immediately without collecting"
                + " $200 when passing the GO cell", jailCard.getLabel());
    }

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
