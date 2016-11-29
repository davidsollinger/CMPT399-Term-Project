package logic.player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import logic.GameMaster;
import logic.cell.Cell;
import logic.cell.PropertyCell;
import logic.cell.RailRoadCell;
import logic.cell.UtilityCell;
import logic.gameBoard.GameBoard;

public class PlayerProperty {

    private final int MAX_AMOUNT_OF_HOUSES = 5;
    private final Map<String, Integer> colorGroups = new HashMap<>();
    private final Player player;
    
    private List<PropertyCell> properties = new ArrayList<>();
    private List<RailRoadCell> railroads = new ArrayList<>();
    private List<UtilityCell> utilities = new ArrayList<>();

    public PlayerProperty(Player player) {
        this.player = player;
    }

    public Cell[] getAllProperties() {
        List<Cell> list = new ArrayList<>();
        list.addAll(properties);
        list.addAll(utilities);
        list.addAll(railroads);
        return list.toArray(new Cell[list.size()]);
    }

    public String[] getMonopolies() {
        List<String> monopolies = new ArrayList<>();
        List<String> colors = new ArrayList<>(colorGroups.keySet());
        while (!colors.isEmpty()) {
            String color = colors.remove(0);
            checkCellRRorUtil(color, monopolies);
        }
        return monopolies.toArray(new String[monopolies.size()]);
    }

    private void checkCellRRorUtil(String color, List<String> monopolies) {
        RailRoadCell rrCell = new RailRoadCell("");
        UtilityCell utilCell = new UtilityCell("");
        if (!(color.equals(rrCell.getColorGroup())) && !(color.equals(utilCell.getColorGroup()))) {
            Integer num = colorGroups.get(color);
            GameBoard gameBoard = GameMaster.INSTANCE.getGameBoard();
            checkSameColorGroup(num, gameBoard, color, monopolies);
        }
    }

    private void checkSameColorGroup(Integer num, GameBoard gameBoard, String color, List<String> monopolies) {
        if (num == gameBoard.getPropertyNumberForColor(color)) {
            monopolies.add(color);
        }
    }

    public PropertyCell getPropertyCell(int index) {
        return properties.get(index);
    }

    public int getPropertyNumber() {
        return properties.size();
    }

    public int getNumberOfRR() {
        RailRoadCell rrCell = new RailRoadCell("");
        return getPropertyNumberForColor(rrCell.getColorGroup());
    }

    public int getNumberOfUtil() {
        UtilityCell utilCell = new UtilityCell("");
        return getPropertyNumberForColor(utilCell.getColorGroup());
    }

    private int getPropertyNumberForColor(String name) {
        Integer number = colorGroups.get(name);
        if (number != null) {
            return number;
        }
        return 0;
    }

    public void buyProperty(Cell property, int amount) {
        property.setPlayer(player);
        if (property instanceof PropertyCell) {
            PropertyCell cell = (PropertyCell) property;
            properties.add(cell);
            colorGroups.put(cell.getColorGroup(), getPropertyNumberForColor(cell.getColorGroup()) + 1);
        }
        if (property instanceof RailRoadCell) {
            RailRoadCell cell = (RailRoadCell) property;
            railroads.add(cell);
            colorGroups.put(cell.getColorGroup(), getPropertyNumberForColor(cell.getColorGroup()) + 1);
        }
        if (property instanceof UtilityCell) {
            UtilityCell cell = (UtilityCell) property;
            utilities.add(cell);
            colorGroups.put(cell.getColorGroup(), getPropertyNumberForColor(cell.getColorGroup()) + 1);
        }
        player.setMoney(player.getMoney() - amount);
    }

    public void exchangeProperty(Player player) {
        for (int i = 0; i < getPropertyNumber(); i++) {
            PropertyCell cell = getPropertyCell(i);
            cell.setPlayer(player);
            player.getProperty().addProperty(cell);
            colorGroups.put(cell.getColorGroup(), getPropertyNumberForColor(cell.getColorGroup()) + 1);
            cell.setAvailable(true);
            cell.setNumHouses(0);
        }
        properties.clear();
    }

    public boolean checkProperty(String property) {
        for (int i = 0; i < properties.size(); i++) {
            Cell cell = properties.get(i);
            if (cell.getName().equals(property)) {
                return true;
            }
        }
        return false;
    }

    protected void addProperty(PropertyCell propertyCell) {
        properties.add(propertyCell);
    }

    public void purchaseHouse(String selectedMonopoly, int houses) {
        GameBoard gb = GameMaster.INSTANCE.getGameBoard();
        PropertyCell[] cells = gb.getPropertiesInMonopoly(selectedMonopoly);
        if ((player.getMoney() >= (cells.length * (cells[0].getHousePrice() * houses)))) {
            checkCellsHouseLimit(cells, houses);
        }
    }

    private void checkCellsHouseLimit(PropertyCell[] cells, int houses) {
        for (PropertyCell cell : cells) {
            int newNumber = cell.getNumHouses() + houses;
            checkOverHouseLimit(newNumber, cell, houses);
        }
    }

    private void checkOverHouseLimit(int newNumber, PropertyCell cell, int houses) {
        if (newNumber <= MAX_AMOUNT_OF_HOUSES) {
            cell.setNumHouses(newNumber);
            player.setMoney(player.getMoney() - (cell.getHousePrice() * houses));
            GameMaster.INSTANCE.updateGUI();
        }
    }

    public void sellProperty(Cell property, int amount) {
        property.setPlayer(new NullPlayer());
        if (property instanceof PropertyCell) {
            PropertyCell propertyCell = (PropertyCell) property;
            properties.remove(propertyCell);
        }
        if (property instanceof RailRoadCell) {
            RailRoadCell rrCell = (RailRoadCell) property;
            railroads.remove(rrCell);
        }
        if (property instanceof UtilityCell) {
            UtilityCell utilCell = (UtilityCell) property;
            utilities.remove(utilCell);
        }
        player.setMoney(player.getMoney() + amount);
    }

    public void resetProperty() {
        properties = new ArrayList<>();
        railroads = new ArrayList<>();
        utilities = new ArrayList<>();
    }
}
