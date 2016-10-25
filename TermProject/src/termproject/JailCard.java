package termproject;

public class JailCard extends Card {

    private final int type;

    public JailCard(int cardType) {
        this.type = cardType;
    }

    @Override
    public void applyAction() {
        Player currentPlayer = GameMaster.instance().getCurrentPlayer();
        //JailCell jail = (JailCell) (GameMaster.instance().getGameBoard().queryCell("Jail"));
        GameMaster.instance().sendToJail(currentPlayer);
    }

    @Override
    public int getCardType() {
        return this.type;
    }

    @Override
    public String getLabel() {
        return "Go to Jail immediately without collecting"
                + " $200 when passing the GO cell";
    }
}
