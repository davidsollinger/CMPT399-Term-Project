package logic.card;

public abstract class Card {

    public abstract String getLabel();

    public abstract CardType getCardType();
    
    public abstract void applyAction();
}
