package termproject;

import controller.GameController;
import debugging.DiceRollDialog;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DiceRollDialogTest {

    private DiceRollDialog diceRollDialog;

    @Before
    public void setUp() {
        diceRollDialog = new DiceRollDialog(GameController.INSTANCE.getGUIController().getGUI());
    }

    @Test
    public void testDiceRollDialog() {
        diceRollDialog.setDiceRollValues(4);
        int[] roll = {2, 2};
        Assert.assertArrayEquals(diceRollDialog.getDiceRoll(), roll);
    }

}
