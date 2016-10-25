package termproject;

public class GameBoardFreeParking extends GameBoard {

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
