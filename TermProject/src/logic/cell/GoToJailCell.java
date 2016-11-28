package termproject;

public class GoToJailCell extends Cell {

    public GoToJailCell() {
        super.setName("Go to Jail");
    }

    @Override
    public void playAction() {
        Player currentPlayer = GameMaster.INSTANCE.getCurrentPlayer();
        GameMaster.INSTANCE.sendToJail(currentPlayer);
    }
}
