package logic.card;

import logic.cell.Cell;
import logic.GameMaster;
import logic.player.Player;

public class MovePlayerCard extends Card {

    private final String destination;
    private final CardType cardType;


    public MovePlayerCard(String destination, CardType cardType) {
        this.destination = destination;
        this.cardType = cardType;
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
    public CardType getCardType() {
        return cardType;
    }

    @Override
    public String getLabel() {
        return "Go to " + destination;
    }

}
