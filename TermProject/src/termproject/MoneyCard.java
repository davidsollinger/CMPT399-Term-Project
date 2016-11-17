package termproject;

public class MoneyCard extends Card {

    private final int amount;

    private final String label;
    private final String cardType;

    public MoneyCard(String label, int amount, String cardType) {
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
    public String getCardType() {
        return this.cardType;
    }

    @Override
    public String getLabel() {
        return label;
    }
}
