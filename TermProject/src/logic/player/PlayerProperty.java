package logic.player;

import controller.GameController;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import logic.cell.Cell;
import logic.cell.PropertyCell;
import logic.cell.RailRoadCell;
import logic.cell.UtilityCell;
import logic.gameBoard.GameBoard;

public class PlayerProperty {

    private final int MAX_AMOUNT_OF_HOUSES = 5;
    private final Player player;
    private final GameController gameController;
    private final Map<String, Integer> colorGroups = new HashMap<>();
    private final List<String> monopolies = new ArrayList<>();

    private List<PropertyCell> properties = new ArrayList<>();
    private List<RailRoadCell> railRoads = new ArrayList<>();
    private List<UtilityCell> utilities = new ArrayList<>();

    public PlayerProperty(Player player) {
        this.player = player;
        gameController = GameController.INSTANCE;
    }
    
    public int getNumberOfProperties() {
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

    public Cell[] getAllProperties() {
        List<Cell> list = new ArrayList<>();
        list.addAll(properties);
        list.addAll(utilities);
        list.addAll(railRoads);
        return list.toArray(new Cell[list.size()]);
    }

    public String[] getMonopolies() {
        List<String> colors = new ArrayList<>(colorGroups.keySet());
        while (!colors.isEmpty()) {
            String color = colors.remove(0);
            checkCellRRorUtil(color);
        }
        return monopolies.toArray(new String[monopolies.size()]);
    }

    public PropertyCell getPropertyCell(int index) {
        return properties.get(index);
    }
    
    public void resetProperty() {
        properties = new ArrayList<>();
        railRoads = new ArrayList<>();
        utilities = new ArrayList<>();
    }
    
    public void addHouse(String selectedMonopoly, int houses) {
        GameBoard gameBoard = gameController.getGameBoardController().getGameBoard();
        PropertyCell[] cells = gameBoard.getPropertiesInMonopoly(selectedMonopoly);
        if ((player.getMoney() >= (cells.length * (cells[0].getHousePrice() * houses)))) {
            checkCellsHouseLimit(cells, houses);
        }
    }

    public void exchangeProperty(Player player) {
        for (int i = 0; i < getNumberOfProperties(); i++) {
            PropertyCell cell = getPropertyCell(i);
            cell.setPlayer(player);
            player.getProperty().addProperty(cell);
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
    
    protected void addProperty(Cell cell) {
        properties.add((PropertyCell) cell);
        addColorGroups(cell);
    }

    protected void addRailRoad(Cell cell) {
        railRoads.add((RailRoadCell) cell);
        addColorGroups(cell);
    }

    protected void addUtility(Cell cell) {
        utilities.add((UtilityCell) cell);
        addColorGroups(cell);
    }
    
    protected void removeProperty(Cell cell) {
        properties.remove((PropertyCell) cell);
    }

    protected void removeRailRoad(Cell cell) {
        railRoads.remove((RailRoadCell) cell);
    }

    protected void removeUtility(Cell cell) {
        utilities.remove((UtilityCell) cell);
    }
    
    private int getPropertyNumberForColor(String name) {
        Integer number = colorGroups.get(name);
        if (number != null) {
            return number;
        }
        return 0;
    }

    private void addColorGroups(Cell cell) {
        colorGroups.put(cell.getColorGroup(),
                getPropertyNumberForColor(cell.getColorGroup()) + 1);
    }

    private void checkCellRRorUtil(String color) {
        RailRoadCell rrCell = new RailRoadCell("");
        UtilityCell utilCell = new UtilityCell("");
        if (!(color.equals(rrCell.getColorGroup())) && !(color.equals(utilCell.getColorGroup()))) {
            int num = colorGroups.get(color);
            GameBoard gameBoard = gameController.getGameBoardController().getGameBoard();
            checkSameColorGroup(num, gameBoard, color);
        }
    }

    private void checkSameColorGroup(Integer num, GameBoard gameBoard, String color) {
        if (num == gameBoard.getPropertyNumberForColor(color)) {
            monopolies.add(color);
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
            player.subtractMoney((cell.getHousePrice() * houses));
            gameController.getGUIController().updateGUI();
        }
    }
}
