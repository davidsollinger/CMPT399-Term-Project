package gameboardvariants;

import logic.cell.FreeParkingCell;
import logic.gameBoard.GameBoard;
import logic.cell.GoToJailCell;
import logic.cell.JailCell;
import logic.cell.PropertyCell;
import logic.cell.RailRoadCell;
import logic.cell.UtilityCell;

public class GameBoardUtility extends GameBoard {

    public GameBoardUtility() {
        super();
        PropertyCell blue1 = new PropertyCell("Blue 1", 50, "blue", 10, 100);
        PropertyCell blue2 = new PropertyCell("Blue 2", 50, "blue", 10, 100);
        PropertyCell green1 = new PropertyCell("Green 1", 70, "green", 20, 200);
        PropertyCell green2 = new PropertyCell("Green 2", 70, "green", 20, 240);
        
        JailCell jail = new JailCell();
        GoToJailCell goToJail = new GoToJailCell();
        FreeParkingCell freeParking = new FreeParkingCell();
        
        RailRoadCell rr1 = new RailRoadCell("Railroad A");
        RailRoadCell rr3 = new RailRoadCell("Railroad C");
        UtilityCell u1 = new UtilityCell("Utility 1");
        UtilityCell u2 = new UtilityCell("Utility 2");

        RailRoadCell.setPrice(200);
        RailRoadCell.setBaseRent(25);

        UtilityCell.setPrice(150);

        super.addCell(rr1);
        super.addCell(blue1);
        super.addCell(jail);
        super.addCell(u1);
        super.addCell(blue2);
        super.addCell(freeParking);
        super.addCell(green1);
        super.addCell(rr3);
        super.addCell(goToJail);
        super.addCell(green2);
        super.addCell(u2);
    }
}
