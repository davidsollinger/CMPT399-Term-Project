package logic.trade;

import controller.GameController;

public class TradeDeal {

    private int amount;
    private int playerIndex;
    private String propertyName;

    public int getAmount() {
        return amount;
    }

    public int getPlayerIndex() {
        return playerIndex;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public String makeMessage() {
        GameController gameController = GameController.INSTANCE;
        return gameController.getCurrentPlayer() + " wishes to purchase "
                + propertyName + " from " + gameController.getPlayerController().getPlayer(playerIndex)
                + " for " + amount + ".  " + gameController.getPlayerController().getPlayer(playerIndex)
                + ", do you wish to trade your property?";
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public void setSellerIndex(int playerIndex) {
        this.playerIndex = playerIndex;
    }

}
