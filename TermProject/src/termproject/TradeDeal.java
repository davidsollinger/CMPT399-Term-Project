package termproject;

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
        String message = GameMaster.INSTANCE.getCurrentPlayer()
                + " wishes to purchase "
                + propertyName + " from "
                + GameMaster.INSTANCE.getPlayer(playerIndex)
                + " for " + amount + ".  "
                + GameMaster.INSTANCE.getPlayer(playerIndex)
                + ", do you wish to trade your property?";
        return message;
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
