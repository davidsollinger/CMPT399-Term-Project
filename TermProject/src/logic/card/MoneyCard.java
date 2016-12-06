package logic.card;

import controller.GameController;
import logic.player.Player;

public class MoneyCard extends Card {

    private final int amount;
    private final String label;
    private final CardType cardType;

    public MoneyCard(String label, int amount, CardType cardType) {
        this.label = label;
        this.amount = amount;
        this.cardType = cardType;
    }

    @Override
    public CardType getCardType() {
        return cardType;
    }

    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public void applyAction() {
        Player currentPlayer = GameController.INSTANCE.getCurrentPlayer();
        currentPlayer.setMoney(currentPlayer.getMoney() + amount);
    }
}
