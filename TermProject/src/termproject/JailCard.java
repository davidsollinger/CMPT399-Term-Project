package termproject;

public class JailCard extends Card {

    private final String type;

    public JailCard(String cardType) {
        this.type = cardType;

    }

    @Override
    public void applyAction() {
        Player currentPlayer = GameMaster.INSTANCE.getCurrentPlayer();
        GameMaster.INSTANCE.sendToJail(currentPlayer);
    }

    @Override
    public String getCardType() {
        return this.type;
    }

    @Override
    public String getLabel() {
        return "Go to Jail immediately without collecting $200 when passing the"
                + " GO cell";
    }
}
