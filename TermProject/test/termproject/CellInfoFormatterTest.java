package termproject;

import controller.GameController;
import gui.infoFormatter.InfoFormatter;
import logic.card.CardType;
import logic.cell.CardCell;
import logic.cell.FreeParkingCell;
import logic.cell.GoCell;
import logic.cell.GoToJailCell;
import logic.cell.JailCell;
import logic.cell.PropertyCell;
import logic.cell.RailRoadCell;
import logic.cell.UtilityCell;
import logic.gameBoard.NullGameBoard;
import logic.player.Player;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class CellInfoFormatterTest {

    private Player player;

    @Before
    public void setUp() {
        GameController gameController = GameController.INSTANCE;
        gameController.getGameBoardController().setGameBoard(new NullGameBoard());
        player = new Player();
        player.setName("Owner 1");
    }

    @Test
    public void testGoCellText() {
        GoCell cell = new GoCell();
        cell.setName("Go");
        String goLabel = "<html><font color='black'><b>Go</b></font></html>";
        assertEquals(goLabel, InfoFormatter.getCellInfo(cell));
    }

    @Test
    public void testPropertyCellText() {
        String propertyName = "Blue 1";
        String propertyColor = "blue";
        String ownerName = "Owner 1";
        int numHouses = 2;
        int propertyValue = 120;
        String propertyLabel = "<html><b><font color='"
                + propertyColor + "'>" + propertyName + "</font></b><br>"
                + "Price: $" + propertyValue
                + "<br>Owner: " + ownerName
                + "<br>Houses: " + numHouses
                + "</html>";

        PropertyCell cell = new PropertyCell(propertyName, 0, propertyColor, 0, propertyValue);

        cell.setPlayer(player);
        cell.setNumHouses(numHouses);
        assertEquals(propertyLabel, InfoFormatter.getCellInfo(cell));
    }

    @Test
    public void testRRCellText() {
        String railRoadName = "RR1";
        String railRoadColor = "lime";
        int railRoadValue = 200;
        String ownerName = "Owner 1";
        String railRoadLabel = "<html><b><font color='"
                + railRoadColor + "'>" + railRoadName + "</font></b><br>"
                + "Price: $" + railRoadValue
                + "<br>Owner: " + ownerName
                + "</html>";
        RailRoadCell cell = new RailRoadCell(railRoadName);
        RailRoadCell.setPrice(railRoadValue);
        cell.setPlayer(player);
        assertEquals(railRoadLabel, InfoFormatter.getCellInfo(cell));
    }

    @Test
    public void testUtilCellText() {
        String utilityName = "Utility 1";
        String utilityColor = "olive";
        int utilityValue = 200;
        String ownerName = "Owner 1";
        String utilityLabel = "<html><b><font color='"
                + utilityColor + "'>" + utilityName + "</font></b><br>"
                + "Price: $" + utilityValue
                + "<br>Owner: " + ownerName
                + "</html>";
        UtilityCell cell = new UtilityCell(utilityName);
        UtilityCell.setPrice(utilityValue);
        cell.setPlayer(player);
        assertEquals(utilityLabel, InfoFormatter.getCellInfo(cell));
    }

    @Test
    public void testCardCellText() {
        CardCell cell;
        String communityLabel = "<html><font color='teal'><b>Community Chest</b></font></html>";
        cell = new CardCell(CardType.COMMUNITY, "Community Chest 1");
        assertEquals(communityLabel, InfoFormatter.getCellInfo(cell));

        String chanceLabel = "<html><font color='teal'><b>Chance</b></font></html>";
        cell = new CardCell(CardType.COMMUNITY, "Chance 1");
        assertEquals(chanceLabel, InfoFormatter.getCellInfo(cell));
    }

    @Test
    public void testGoToJailCellText() {
        String goToJailLabel = "<html><font color='black'><b>Go To Jail</b></font></html>";
        GoToJailCell cell = new GoToJailCell();
        cell.setName("Go To Jail");
        assertEquals(goToJailLabel, InfoFormatter.getCellInfo(cell));
    }

    @Test
    public void testJailText() {
        String jailLabel = "<html><font color='black'><b>Jail</b></font></html>";
        JailCell cell = new JailCell();
        cell.setName("Jail");
        assertEquals(jailLabel, InfoFormatter.getCellInfo(cell));
    }

    @Test
    public void testFreeParkingText() {
        String freeParkingLabel = "<html><font color='black'><b>Free Parking</b></font></html>";
        FreeParkingCell cell = new FreeParkingCell();
        cell.setName("Free Parking");
        assertEquals(freeParkingLabel, InfoFormatter.getCellInfo(cell));
    }
}
