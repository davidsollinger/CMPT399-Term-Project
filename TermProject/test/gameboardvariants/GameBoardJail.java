package gameboardvariants;

import logic.gameBoard.GameBoard;
import logic.cell.GoToJailCell;
import logic.cell.JailCell;
import logic.cell.PropertyCell;

public class GameBoardJail extends GameBoard { // Not used?

    public GameBoardJail() {
        super();
        PropertyCell blue1 = new PropertyCell("Blue 1", 50, "blue", 10, 100);
        PropertyCell blue2 = new PropertyCell("Blue 2", 50, "blue", 10, 100);
        PropertyCell blue3 = new PropertyCell("Blue 3", 50, "blue", 10, 1450);
        PropertyCell green1 = new PropertyCell("Green 1", 70, "green", 20, 200);
        PropertyCell green2 = new PropertyCell("Green 2", 70, "green", 20, 240);
        JailCell jail = new JailCell();
        GoToJailCell goToJail = new GoToJailCell();

        super.addCell(blue1);
        super.addCell(jail);
        super.addCell(blue2);
        super.addCell(blue3);
        super.addCell(green1);
        super.addCell(goToJail);
        super.addCell(green2);
    }
}
