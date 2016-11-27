package termproject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerProperty {

    private final Map<String, Integer> colorGroups = new HashMap<>();
    private List<PropertyCell> properties = new ArrayList<>();
    private List<RailRoadCell> railroads = new ArrayList<>();
    private List<UtilityCell> utilities = new ArrayList<>();

    private Player player;

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
            if (!(color.equals(RailRoadCell.COLOR_GROUP)) && !(color.equals(UtilityCell.COLOR_GROUP))) {
                Integer num = colorGroups.get(color);
                GameBoard gameBoard = GameMaster.INSTANCE.getGameBoard();
                if (num == gameBoard.getPropertyNumberForColor(color)) {
                    monopolies.add(color);
                }
            }
        }
        return monopolies.toArray(new String[monopolies.size()]);
    }

    public PropertyCell getPropertyCell(int index) {
        return properties.get(index);
    }

    public int getPropertyNumber() {
        return properties.size();
    }

    public int getNumberOfRR() {
        return getPropertyNumberForColor(RailRoadCell.COLOR_GROUP);
    }

    public int getNumberOfUtil() {
        return getPropertyNumberForColor(UtilityCell.COLOR_GROUP);
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
            colorGroups.put(RailRoadCell.COLOR_GROUP, getPropertyNumberForColor(RailRoadCell.COLOR_GROUP) + 1);
        }
        if (property instanceof UtilityCell) {
            UtilityCell cell = (UtilityCell) property;
            utilities.add(cell);
            colorGroups.put(UtilityCell.COLOR_GROUP, getPropertyNumberForColor(UtilityCell.COLOR_GROUP) + 1);
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
            for (PropertyCell cell : cells) {
                int newNumber = cell.getNumHouses() + houses;
                if (newNumber <= 5) {
                    cell.setNumHouses(newNumber);
                    player.setMoney(player.getMoney() - (cell.getHousePrice() * houses));
                    GameMaster.INSTANCE.updateGUI();
                }
            }
        }
    }

    public void sellProperty(Cell property, int amount) {
        property.setPlayer(new NullPlayer());
        if (property instanceof PropertyCell) {
            properties.remove(property);
        }
        if (property instanceof RailRoadCell) {
            railroads.remove(property);
        }
        if (property instanceof UtilityCell) {
            utilities.remove(property);
        }
        player.setMoney(player.getMoney() + amount);
    }

    public void resetProperty() {
        properties = new ArrayList<>();
        railroads = new ArrayList<>();
        utilities = new ArrayList<>();
    }
}
