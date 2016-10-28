package termproject;

public class MovePlayerCard extends Card {

    private final String destination;
    private final int type;

    public MovePlayerCard(String destination, int cardType) {
        this.destination = destination;
        this.type = cardType;
    }

    @Override
    public void applyAction() {
        Player currentPlayer = GameMaster.INSTANCE.getCurrentPlayer();
        Cell currentPosition = currentPlayer.getPosition();
        int newCell = GameMaster.INSTANCE.getGameBoard().queryCellIndex(this.destination);
        int currentCell = GameMaster.INSTANCE.getGameBoard().queryCellIndex(currentPosition.getName());
        int diceValue = 0;
        if (currentCell > newCell) {
            diceValue = (GameMaster.INSTANCE.getGameBoard().getCellNumber()
                    + (newCell - currentCell));
        } else if (currentCell <= newCell) {
            diceValue = newCell - currentCell;
        }
        System.out.println(diceValue);
        GameMaster.INSTANCE.movePlayer(currentPlayer, diceValue);
    }

    @Override
    public int getCardType() {
        return this.type;
    }

    @Override
    public String getLabel() {
        return "Go to " + this.destination;
    }

}
