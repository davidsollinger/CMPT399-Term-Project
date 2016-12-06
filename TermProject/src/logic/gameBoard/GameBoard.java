package logic.gameBoard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import logic.card.Card;
import logic.card.CardType;
import logic.cell.Cell;
import logic.cell.GoCell;
import logic.cell.NullCell;
import logic.cell.PropertyCell;

public class GameBoard {

    private final List<Cell> cells = new ArrayList<>();
    private final Map<String, Integer> colorGroups = new HashMap<>();
    private final List<Card> communityChestCards = new ArrayList<>();
    private final List<Card> chanceCards = new ArrayList<>();

    public GameBoard() {
        Cell go = new GoCell();
        addCell(go);
    }

    public int getCellNumber() {
        return cells.size();
    }

    public int getPropertyNumberForColor(String name) {
        Integer number = colorGroups.get(name);
        if (number != null) {
            return number;
        }
        return 0;
    }

    public Cell getCell(int newIndex) {
        return cells.get(newIndex);
    }

    public PropertyCell[] getPropertiesInMonopoly(String color) {
        PropertyCell[] monopolyCells
                = new PropertyCell[getPropertyNumberForColor(color)];
        int counter = 0;
        for (int i = 0; i < getCellNumber(); i++) {
            Cell c = getCell(i);
            if (c.getColorGroup().equals(color)) {
                monopolyCells[counter] = (PropertyCell) c;
                counter++;
            }
        }
        return monopolyCells;
    }

    public void addCard(Card card) {
        if (card.getCardType().equals(CardType.COMMUNITY)) {
            communityChestCards.add(card);
            return;
        }
        chanceCards.add(card);
    }

    public final void addCell(Cell cell) {
        cells.add(cell);
    }

    public void addPropertyCell(PropertyCell cell) {
        setPropertyCellColor(cell);
        cells.add(cell);
    }

    public Card drawCommunityChestCard() {
        Card card = communityChestCards.get(0);
        communityChestCards.remove(0);
        addCard(card);
        return card;
    }

    public Card drawChanceCard() {
        Card card = chanceCards.get(0);
        chanceCards.remove(0);
        addCard(card);
        return card;
    }

    public Cell queryCell(String cellName) {
        for (int i = 0; i < cells.size(); i++) {
            Cell cell = cells.get(i);
            if (cell.getName().equals(cellName)) {
                return cell;
            }
        }
        return new NullCell();
    }

    public int queryCellIndex(String cellName) {
        for (int i = 0; i < cells.size(); i++) {
            Cell cell = cells.get(i);
            if (cell.getName().equals(cellName)) {
                return i;
            }
        }
        return -1;
    }

    public void removeCards() {
        communityChestCards.clear();
    }

    protected void shuffleCardPiles() {
        Collections.shuffle(communityChestCards);
        Collections.shuffle(chanceCards);
    }

    private void setPropertyCellColor(PropertyCell cell) {
        int propertyNumber = getPropertyNumberForColor(cell.getColorGroup());
        colorGroups.put(cell.getColorGroup(), propertyNumber + 1);
    }

}
