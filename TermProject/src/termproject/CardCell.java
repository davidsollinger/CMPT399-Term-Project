package termproject;

public class CardCell extends Cell {
    
    private final String type;

    public CardCell(String name, String type) {
        super.setName(name);
        this.type = type;
    }

    public String getType() {
        return this.type;
    }
}
