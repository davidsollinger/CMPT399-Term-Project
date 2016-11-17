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
        Player currentPlayer = GameMaster.INSTANCE.getCurrentPlayer();
        currentPlayer.setMoney(currentPlayer.getMoney() + amount);
    }

    @Override
    public int getCardType() {
        return cardType;
    }

    @Override
    public String getLabel() {
        return label;
    }
}
