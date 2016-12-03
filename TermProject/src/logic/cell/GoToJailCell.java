package logic.cell;

import controller.GameController;
import logic.player.Player;

public class GoToJailCell extends Cell {

    public GoToJailCell() {
        super.setName("Go to Jail");
    }

    @Override
    public void playAction() {
        GameController gameController = GameController.INSTANCE;
        Player currentPlayer = gameController.getCurrentPlayer();
        gameController.getPlayerController().sendToJail(currentPlayer);
    }
}
