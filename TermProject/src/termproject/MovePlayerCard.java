package termproject;

public class MovePlayerCard extends Card {

    private final int type;
    private final String destination;

    public MovePlayerCard(String destination, int cardType) {
        this.destination = destination;
        this.type = cardType;
    }

    @Override
    public void applyAction() {
        Player currentPlayer = GameMaster.INSTANCE.getCurrentPlayer();
        Cell currentPosition = currentPlayer.getPosition();
        int diceValue = calculateDiceValue(currentPosition);
        GameMaster.INSTANCE.movePlayer(currentPlayer, diceValue);
    }

    private int calculateDiceValue(Cell currentPosition) {
        int newCell = GameMaster.INSTANCE.getGameBoard().queryCellIndex(destination);
        int currentCell = GameMaster.INSTANCE.getGameBoard().queryCellIndex(currentPosition.getName());
        if (iscurrentCellGreaterThanNewCell(currentCell, newCell)) {
            int boardCell = GameMaster.INSTANCE.getGameBoard().getCellNumber();
            return (boardCell + (newCell - currentCell));
        }
        return newCell - currentCell;
    }

    private static boolean iscurrentCellGreaterThanNewCell(int currentCell, int newCell) {
        return currentCell > newCell;
    }

    @Override
    public int getCardType() {
        return type;
    }

    @Override
    public String getLabel() {
        return "Go to " + destination;
    }

}
