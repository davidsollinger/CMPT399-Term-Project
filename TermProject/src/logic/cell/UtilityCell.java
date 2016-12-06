package logic.cell;

import controller.GameController;
import logic.player.Player;

public class UtilityCell extends Cell {

    private static int price;

    private final int ONE_UTILITY = 1;
    private final int BOTH_UTILITIES = 2;
    private final GameController gameController;

    public UtilityCell(String name) {
        super.setName(name);
        gameController = GameController.INSTANCE;
    }

    public static void setPrice(int price) {
        UtilityCell.price = price;
    }
    
    public int getRent(int diceRoll) {
        if (ownsAllUtilities()) {
            return diceRoll * 10;
        } else if (ownsOneUtility()) {
            return diceRoll * 4;
        }
        return 0;
    }
    
    private boolean ownsOneUtility() {
        return getPlayer().getProperty().getNumberOfUtil() == ONE_UTILITY;
    }

    private boolean ownsAllUtilities() {
        return getPlayer().getProperty().getNumberOfUtil() == BOTH_UTILITIES;
    }
    
    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public String getColorGroup() {
        return "UTILITY";
    }

    @Override
    public void playAction() {
        Player currentPlayer;
        if (!isAvailable()) {
            currentPlayer = gameController.getCurrentPlayer();
            checkIfCurrentPlayer(currentPlayer);
        }
    }
    
    @Override
    protected void checkIfCurrentPlayer(Player currentPlayer) {
        if (!isCurrentPlayer(currentPlayer)) {
            gameController.utilRollDice();
            int diceRoll = gameController.getUtilDiceRoll();
            currentPlayer.getActions().payRentTo(getPlayer(), getRent(diceRoll));
        }
    }
}
