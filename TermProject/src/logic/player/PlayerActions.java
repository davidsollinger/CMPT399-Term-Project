package logic.player;

import controller.GameController;
import logic.cell.Cell;
import logic.cell.JailCell;
import logic.cell.PropertyCell;
import logic.cell.RailRoadCell;
import logic.cell.UtilityCell;

public class PlayerActions {

    private final int NONE = 0;
    private final Player player;

    public PlayerActions(Player player) {
        this.player = player;
    }

    public void getOutOfJail() {
        GameController gameController = GameController.INSTANCE;
        player.subtractMoney(JailCell.BAIL);
        checkPlayerBankrupt(new NullPlayer());
        player.setInJail(false);
        gameController.getGUIController().updateGUI();
    }

    public void payRentTo(Player owner, int rentValue) {
        if (player.getMoney() < rentValue) {
            owner.addMoney(player.getMoney());
        } else {
            owner.addMoney(rentValue);
        }
        player.subtractMoney(rentValue);
        checkPlayerBankrupt(owner);
    }

    public void purchase() {
        Cell cell = player.getPosition();
        if (player.getPosition().isAvailable()) {
            cell.setAvailable(false);
            buyProperty(cell, cell.getPrice());
        }
    }

    public void buyProperty(Cell cell, int amount) {
        cell.setPlayer(player);
        if (isCellInstanceOfPropertyCell(cell)) {
            player.getProperty().addProperty(cell);
        }
        if (isCellInstanceOfRRCell(cell)) {
            player.getProperty().addRailRoad(cell);
        }
        if (isCellInstanceOfUtilityCell(cell)) {
            player.getProperty().addUtility(cell);
        }
        player.subtractMoney(amount);
    }

    public void sellProperty(Cell cell, int amount) {
        cell.setPlayer(new NullPlayer());
        if (isCellInstanceOfPropertyCell(cell)) {
            player.getProperty().removeProperty(cell);
        }
        if (isCellInstanceOfRRCell(cell)) {
            player.getProperty().removeRailRoad(cell);
        }
        if (isCellInstanceOfUtilityCell(cell)) {
            player.getProperty().removeUtility(cell);
        }
        player.addMoney(amount);
    }
    
    private void exchangePropertyToPlayer(Player otherPlayer) {
        player.getProperty().exchangeProperty(otherPlayer);
    }
    
    private void checkPlayerBankrupt(Player otherPlayer) {
        if (player.isBankrupt()) {
            player.setMoney(NONE);
            exchangePropertyToPlayer(otherPlayer);
            Cell [] properties = player.getProperty().getAllProperties();
            for (Cell propertie : properties) {
                propertie.reset();
            }
        }
    }
    
    private boolean isCellInstanceOfPropertyCell(Cell cell) {
        return cell instanceof PropertyCell;
    }
    
    private boolean isCellInstanceOfRRCell(Cell cell) {
        return cell instanceof RailRoadCell;
    }
    
    private boolean isCellInstanceOfUtilityCell(Cell cell) {
        return cell instanceof UtilityCell;
    }
}
