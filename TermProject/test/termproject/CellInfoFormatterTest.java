package termproject;

import gui.InfoFormatter;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class CellInfoFormatterTest {
    
    @Before
    public void setUp() {
        GameMaster gameMaster = GameMaster.INSTANCE;
        gameMaster.setGameBoard(new NullGameBoard());
    }

//    @Test
//    public void testGoCellTest() { // Not fully implemented
//        GoCell cell = new GoCell();
////        String goLabel = GoCellInfoFormatter.GO_CELL_LABEL;
//        String goLabel = "<html><b>Go</b></html>";
//        assertEquals(goLabel, InfoFormatter.cellInfo(cell));
//    }

    @Test
    public void testPropertyCellText() {
        String propertyName = "Blue 1";
        String propertyColor = "blue";
        String ownerName = "Owner1";
        int numHouses = 2;
        int propertyValue = 120;
        String propertyLabel = "<html><b><font color='"
                + propertyColor + "'>" + propertyName + "</font></b><br>"
                + "$" + propertyValue
                + "<br>Owner: " + ownerName
                + "<br>* " + numHouses
                + "</html>";
        PropertyCell cell = new PropertyCell();
        cell.setName(propertyName);
        cell.setPrice(propertyValue);
        cell.setColorGroup(propertyColor);
        Player p = new Player();
        p.setName(ownerName);
        cell.setPlayer(p);
        cell.setNumHouses(numHouses);
        assertEquals(propertyLabel, InfoFormatter.cellInfo(cell));
    }
}
