package termproject;

import logic.cell.Cell;
import logic.cell.PropertyCell;
import logic.gameBoard.GameBoard;
import logic.gameBoard.SimpleGameBoard;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import org.junit.Before;
import org.junit.Test;

public class GameboardTest {

    private Cell cell;
    private GameBoard gameBoard;

    @Before
    public void setUp() throws Exception {
        gameBoard = new GameBoard();
        cell = new PropertyCell("TempCell", 0, "blue", 0, 0);
    }

    @Test
    public void testAddCell() {
        assertEquals(1, gameBoard.getCellNumber());
        gameBoard.addCell(cell);
        assertEquals(2, gameBoard.getCellNumber());
    }

    @Test
    public void testCellsForMonopoly() {
        GameBoard gb = new SimpleGameBoard();
        PropertyCell[] properties = gb.getPropertiesInMonopoly("blue");
        assertEquals("Blue 1", properties[0].getName());
        assertEquals("Blue 2", properties[1].getName());
        assertEquals("Blue 3", properties[2].getName());
        assertEquals(3, properties.length);
    }

    @Test
    public void testPropertyNumberForColor() {
        PropertyCell blue1 = new PropertyCell("Blue 1", 0, "blue", 0, 0);
        PropertyCell blue2 = new PropertyCell("Blue 2", 0, "blue", 0, 0);
        PropertyCell green1 = new PropertyCell("Green 1", 0, "green", 0, 0);

        gameBoard.addPropertyCell(blue1);
        gameBoard.addPropertyCell(blue2);
        gameBoard.addPropertyCell(green1);
        assertEquals(2, gameBoard.getPropertyNumberForColor("blue"));
        assertEquals(1, gameBoard.getPropertyNumberForColor("green"));
    }

    @Test
    public void testQueryCell() {
        gameBoard.addCell(cell);
        assertSame(cell, gameBoard.queryCell("TempCell"));
    }

    @Test
    public void testQueryCellIndex() {
        gameBoard.addCell(cell);
        assertEquals(0, gameBoard.queryCellIndex("Go"));
        assertEquals(1, gameBoard.queryCellIndex("TempCell"));
    }
}
