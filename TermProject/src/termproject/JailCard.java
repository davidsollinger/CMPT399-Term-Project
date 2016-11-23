package termproject;

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
        return "Go to Jail immediately without collecting $200 when passing the"
                + " GO cell";
    }
}
