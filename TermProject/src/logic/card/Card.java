package logic.card;

public abstract class Card {

    public abstract String getLabel();

    public abstract void applyAction();

    public abstract CardType getCardType();
}
