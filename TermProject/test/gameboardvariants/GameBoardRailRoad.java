package gameboardvariants;

import termproject.FreeParkingCell;
import termproject.GameBoard;
import termproject.GoToJailCell;
import termproject.JailCell;
import termproject.PropertyCell;
import termproject.RailRoadCell;

public class GameBoardRailRoad extends GameBoard {

    public GameBoardRailRoad() {
        super();
        PropertyCell blue1 = new PropertyCell("Blue 1", 50, "blue", 10, 100);
        PropertyCell blue2 = new PropertyCell("Blue 2", 50, "blue", 10, 100);
        PropertyCell green1 = new PropertyCell("Green 1", 70, "green", 20, 200);
        PropertyCell green2 = new PropertyCell("Green 2", 70, "green", 20, 240);
        
        JailCell jail = new JailCell();
        GoToJailCell goToJail = new GoToJailCell();
        FreeParkingCell freeParking = new FreeParkingCell();
        
        RailRoadCell rr1 = new RailRoadCell("Railroad A");
        RailRoadCell rr2 = new RailRoadCell("Railroad B");
        RailRoadCell rr3 = new RailRoadCell("Railroad C");
        RailRoadCell rr4 = new RailRoadCell("Railroad D");

        RailRoadCell.setPrice(200);
        RailRoadCell.setBaseRent(25);


        super.addCell(rr1);
        super.addCell(blue1);
        super.addCell(jail);
        super.addCell(rr2);
        super.addCell(blue2);
        super.addCell(freeParking);
        super.addCell(green1);
        super.addCell(rr3);
        super.addCell(goToJail);
        super.addCell(green2);
        super.addCell(rr4);
    }
}
