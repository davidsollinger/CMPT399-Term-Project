package logic.cell;

import logic.card.CardType;

public class CardCell extends Cell {
    
    private final CardType type;

    public CardCell(CardType type, String name) {
        super.setName(name);
        this.type = type;
    }

    public CardType getType() {
        return type;
    }
    
    @Override
    public String getName() {
        if (super.getName().contains("Community Chest")) {
            return "Community Chest";
        }
        return "Chance";
    }
}
