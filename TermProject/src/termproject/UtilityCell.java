package termproject;

public class UtilityCell extends Cell {

    private static final String COLOR_GROUP = "UTILITY";
    
    private static int price;
    private final int ONE_UTILITY = 1;
    private final int BOTH_UTILITIES = 2;

    public static void setPrice(int price) {
        UtilityCell.price = price;
    }

    @Override
    public int getPrice() {
        return price;
    }
    
    public static String getColorGroup() {
        return COLOR_GROUP;
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
        return getPlayer().numberOfUtil() == ONE_UTILITY;
    }

    private boolean ownsAllUtilities() {
        return getPlayer().numberOfUtil() == BOTH_UTILITIES;
    }

    @Override
    public void playAction() {
        Player currentPlayer;
        if (!isAvailable()) {
            currentPlayer = GameMaster.INSTANCE.getCurrentPlayer();
            if (!isCurrentPlayer(currentPlayer)) {
                GameMaster.INSTANCE.utilRollDice();
                int diceRoll = GameMaster.INSTANCE.getUtilDiceRoll();
                currentPlayer.payRentTo(getPlayer(), getRent(diceRoll));
            }
        }
    }
}
