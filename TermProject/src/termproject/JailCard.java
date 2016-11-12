package termproject;

public class JailCard extends Card {

    private final int cardType;

    public JailCard(int cardType) {
        this.cardType = cardType;
    }

    @Override
    public void applyAction() {
        Player currentPlayer = GameMaster.INSTANCE.getCurrentPlayer();
        GameMaster.INSTANCE.sendToJail(currentPlayer);
    }

    @Override
    public int getCardType() {
        return cardType;
    }

    @Override
    public String getLabel() {
        return "Go to Jail immediately without collecting $200 when passing the"
                + " GO cell";
    }
}
