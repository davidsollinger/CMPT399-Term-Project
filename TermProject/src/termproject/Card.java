package termproject;

public abstract class Card {

    public static final int TYPE_CHANCE = 1;
    public static final int TYPE_CC = 2;

    public abstract String getLabel();

    protected abstract void applyAction();

    protected abstract int getCardType();
}
