package gameboardvariants;

import logic.cell.FreeParkingCell;
import logic.gameBoard.GameBoard;
import logic.cell.GoToJailCell;
import logic.cell.JailCell;

public class GameBoardFreeParking extends GameBoard { // Not used?

    public GameBoardFreeParking() {
        super();
        JailCell jail = new JailCell();
        FreeParkingCell freeParking = new FreeParkingCell();
        GoToJailCell goToJail = new GoToJailCell();
        super.addCell(jail);
        super.addCell(freeParking);
        super.addCell(goToJail);

    }
}
