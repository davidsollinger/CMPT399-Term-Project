package termproject;

public abstract class Card {

    public abstract String getLabel();

    protected abstract void applyAction();

    protected abstract String getCardType();
}
