package termproject;

public class MoneyCard extends Card {

    private final int amount;
    private final int cardType;

    private final String label;

    public MoneyCard(String label, int amount, int cardType) {
        this.label = label;
        this.amount = amount;
        this.cardType = cardType;
    }

    @Override
    public void applyAction() {
        Player currentPlayer = GameMaster.instance().getCurrentPlayer();
        currentPlayer.setMoney(currentPlayer.getMoney() + this.amount);
    }

    @Override
    public int getCardType() {
        return this.cardType;
    }

    @Override
    public String getLabel() {
        return this.label;
    }
}
