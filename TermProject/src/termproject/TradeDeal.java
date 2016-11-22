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
        GameMaster gameMaster = GameMaster.INSTANCE;
        return gameMaster.getCurrentPlayer() + " wishes to purchase "
                + propertyName + " from " + gameMaster.getPlayer(playerIndex)
                + " for " + amount + ".  " + gameMaster.getPlayer(playerIndex)
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
