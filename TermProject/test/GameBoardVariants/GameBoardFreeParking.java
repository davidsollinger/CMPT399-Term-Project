package GameBoardVariants;

import termproject.FreeParkingCell;
import termproject.GameBoard;
import termproject.GoToJailCell;
import termproject.JailCell;

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
