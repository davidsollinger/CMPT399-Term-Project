package logic.card;

import logic.GameMaster;
import logic.player.Player;

public class JailCard extends Card {

    private final CardType cardType;

    public JailCard(CardType cardType) {
        this.cardType = cardType;
    }

    @Override
    public void applyAction() {
        Player currentPlayer = GameMaster.INSTANCE.getCurrentPlayer();
        GameMaster.INSTANCE.sendToJail(currentPlayer);
    }

    @Override
    public CardType getCardType() {
        return cardType;
    }

    @Override
    public String getLabel() {
        return "Go directly to Jail. Do not pass go, do not collect $200";
    }
}
