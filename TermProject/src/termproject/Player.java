package termproject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Player implements Nullable {

    private final Map<String, Integer> colorGroups = new HashMap<>();
    private boolean inJail;
    private int money;
    protected String name;
    private Cell position;
    private List<PropertyCell> properties = new ArrayList<>();
    private List<RailRoadCell> railroads = new ArrayList<>();
    private List<UtilityCell> utilities = new ArrayList<>();

    protected Player() {
        GameBoard gb = GameMaster.INSTANCE.getGameBoard();
        inJail = false;
        if (gb != null) {
            position = gb.queryCell("Go");
        }
    }
    
    public static Player nullPlayer() {
        return new NullPlayer();
    }

    public void buyProperty(Cell property, int amount) {
        property.setPlayer(this);
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
        setMoney(getMoney() - amount);
    }

    public boolean canBuyHouse() {
        return (getMonopolies().length != 0);
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

    public void exchangeProperty(Player player) {
        for (int i = 0; i < getPropertyNumber(); i++) {
            PropertyCell cell = getProperty(i);
            cell.setPlayer(player);
            if (player == null) {
                cell.setAvailable(true);
                cell.setNumHouses(0);
            } else {
                player.properties.add(cell);
//                player.addProperty(cell);
                colorGroups.put(cell.getColorGroup(), getPropertyNumberForColor(cell.getColorGroup()) + 1);
            }
        }
        properties.clear();
    }

    private void addProperty(PropertyCell propertyCell) {
        properties.add(propertyCell);
    }

    public Cell[] getAllProperties() {
        List<Cell> list = new ArrayList<>();
        list.addAll(properties);
        list.addAll(utilities);
        list.addAll(railroads);
        return list.toArray(new Cell[list.size()]);
    }

    public int getMoney() {
        return money;
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

    public String getName() {
        return name;
    }

    public void getOutOfJail() {
        money -= JailCell.BAIL;
        if (isBankrupt()) {
            money = 0;
            exchangeProperty(null);
        }
        inJail = false;
        GameMaster.INSTANCE.updateGUI();
    }

    public Cell getPosition() {
        return position;
    }

    public PropertyCell getProperty(int index) {
        return properties.get(index);
    }

    public int getPropertyNumber() {
        return properties.size();
    }

    private int getPropertyNumberForColor(String name) {
        Integer number = colorGroups.get(name);
        if (number != null) {
            return number;
        }
        return 0;
    }

    public boolean isBankrupt() {
        return money <= 0;
    }

    public boolean isInJail() {
        return inJail;
    }

    public int numberOfRR() {
        return getPropertyNumberForColor(RailRoadCell.COLOR_GROUP);
    }

    public int numberOfUtil() {
        return getPropertyNumberForColor(UtilityCell.COLOR_GROUP);
    }

    public void payRentTo(Player owner, int rentValue) {
        if (money < rentValue) {
            owner.addMoney(money);
            money -= rentValue;
        } else {
            money -= rentValue;
            owner.addMoney(rentValue);
        }
        if (isBankrupt()) {
            money = 0;
            exchangeProperty(owner);
        }
    }

    public void addMoney(int amount) {
        money += amount;
    }

    public void purchase() {
        if (getPosition().isAvailable()) {
            Cell c = getPosition();
            c.setAvailable(false);
            if (c instanceof PropertyCell) {
                PropertyCell cell = (PropertyCell) c;
                purchaseProperty(cell);
            }
            if (c instanceof RailRoadCell) {
                RailRoadCell cell = (RailRoadCell) c;
                purchaseRailRoad(cell);
            }
            if (c instanceof UtilityCell) {
                UtilityCell cell = (UtilityCell) c;
                purchaseUtility(cell);
            }
        }
    }

    public void purchaseHouse(String selectedMonopoly, int houses) {
        GameBoard gb = GameMaster.INSTANCE.getGameBoard();
        PropertyCell[] cells = gb.getPropertiesInMonopoly(selectedMonopoly);
        if ((money >= (cells.length * (cells[0].getHousePrice() * houses)))) {
            for (PropertyCell cell : cells) {
                int newNumber = cell.getNumHouses() + houses;
                if (newNumber <= 5) {
                    cell.setNumHouses(newNumber);
                    setMoney(money - (cell.getHousePrice() * houses));
                    GameMaster.INSTANCE.updateGUI();
                }
            }
        }
    }

    private void purchaseProperty(PropertyCell cell) {
        buyProperty(cell, cell.getPrice());
    }

    private void purchaseRailRoad(RailRoadCell cell) {
        buyProperty(cell, cell.getPrice());
    }

    private void purchaseUtility(UtilityCell cell) {
        buyProperty(cell, cell.getPrice());
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
        setMoney(getMoney() + amount);
    }

    public void setInJail(boolean inJail) {
        this.inJail = inJail;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPosition(Cell newPosition) {
        this.position = newPosition;
    }

    @Override
    public String toString() {
        return name;
    }

    public void resetProperty() {
        properties = new ArrayList<>();
        railroads = new ArrayList<>();
        utilities = new ArrayList<>();
    }

    @Override
    public boolean isNull() {
        return false;
    }
}
