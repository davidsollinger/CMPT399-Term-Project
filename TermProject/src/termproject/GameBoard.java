package termproject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameBoard {

    private final List<Cell> cells = new ArrayList<>();
    //the key of colorGroups is the name of the color group.
    private final Map<String, Integer> colorGroups = new HashMap<>();
    private final List<Card> communityChestCards = new ArrayList<>();
    private final List<Card> chanceCards = new ArrayList<>();

    public GameBoard() {
        Cell go = new GoCell();
        addCell(go);
    }

    protected void addCard(Card card) {
        if (card.getCardType() == Card.TYPE_CC) {
            communityChestCards.add(card);
        } else {
            chanceCards.add(card);
        }
    }

    protected final void addCell(Cell cell) {
        cells.add(cell);
    }

    protected void addPropertyCell(PropertyCell cell) {
        setPropertyCellColor(cell);
        cells.add(cell);
    }
    
    private void setPropertyCellColor (PropertyCell cell) {
        int propertyNumber = getPropertyNumberForColor(cell.getColorGroup());
        colorGroups.put(cell.getColorGroup(), propertyNumber + 1);
    }

    protected Card drawCCCard() {
        Card card = communityChestCards.get(0);
        communityChestCards.remove(0);
        addCard(card);
        return card;
    }

    protected Card drawChanceCard() {
        Card card = chanceCards.get(0);
        chanceCards.remove(0);
        addCard(card);
        return card;
    }

    public Cell getCell(int newIndex) {
        return cells.get(newIndex);
    }

    public int getCellNumber() {
        return cells.size();
    }

    protected PropertyCell[] getPropertiesInMonopoly(String color) {
        PropertyCell[] monopolyCells
                = new PropertyCell[getPropertyNumberForColor(color)];
        int counter = 0;
        for (int i = 0; i < getCellNumber(); i++) {
            Cell c = getCell(i);
            if (c instanceof PropertyCell) {
                PropertyCell pc = (PropertyCell) c;
                if (pc.getColorGroup().equals(color)) {
                    monopolyCells[counter] = pc;
                    counter++;
                }
            }
        }
        return monopolyCells;
    }

    protected int getPropertyNumberForColor(String name) {
        Integer number = colorGroups.get(name);
        if (number != null) {
            return number;
        }
        return 0;
    }

    protected Cell queryCell(String string) {
        for (int i = 0; i < cells.size(); i++) {
            Cell temp = cells.get(i);
            if (temp.getName().equals(string)) {
                return temp;
            }
        }
        return new NullCell();
    }

    protected int queryCellIndex(String string) {
        for (int i = 0; i < cells.size(); i++) {
            Cell temp = cells.get(i);
            if (temp.getName().equals(string)) {
                return i;
            }
        }
        return -1;
    }

    protected void removeCards() {
        communityChestCards.clear();
    }
    
    protected void shuffleCardPiles() {
        Collections.shuffle(communityChestCards);
        Collections.shuffle(chanceCards);
    }
}
