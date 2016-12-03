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

    private void checkPlayerBankrupt(Player otherPlayer) {
        if (player.isBankrupt()) {
            player.setMoney(NONE);
            exchangePropertyToPlayer(otherPlayer);
        }
    }

    private void exchangePropertyToPlayer(Player otherPlayer) {
        player.getProperty().exchangeProperty(otherPlayer);
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
        if (player.getPosition().isAvailable()) {
            Cell cell = player.getPosition();
            cell.setAvailable(false);
            buyProperty(cell, cell.getPrice());
        }
    }

    public void buyProperty(Cell property, int amount) {
        property.setPlayer(player);
        if (property instanceof PropertyCell) {
            player.getProperty().addProperty(property);
        }
        if (property instanceof RailRoadCell) {
            player.getProperty().addRailRoad(property);
        }
        if (property instanceof UtilityCell) {
            player.getProperty().addUtility(property);
        }
        player.subtractMoney(amount);
    }

    public void sellProperty(Cell property, int amount) {
        property.setPlayer(new NullPlayer());
        if (property instanceof PropertyCell) {
            player.getProperty().removeProperty(property);
        }
        if (property instanceof RailRoadCell) {
            player.getProperty().removeRailRoad(property);
        }
        if (property instanceof UtilityCell) {
            player.getProperty().removeUtility(property);
        }
        player.addMoney(amount);
    }
}
