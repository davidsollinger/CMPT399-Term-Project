package termproject;

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
        if (name.contains("Community Chest")) {
            return "Community Chest";
        }
        return "Chance";
    }
}
