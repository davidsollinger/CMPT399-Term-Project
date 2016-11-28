package logic.player;

import logic.GameMaster;
import logic.cell.Cell;
import logic.cell.JailCell;
import logic.cell.PropertyCell;
import logic.cell.RailRoadCell;
import logic.cell.UtilityCell;

public class PlayerActions {

    private final Player player;

    public PlayerActions(Player player) {
        this.player = player;
    }

    public void getOutOfJail() {
        player.setMoney(player.getMoney() - JailCell.BAIL);
        if (player.isBankrupt()) {
            player.setMoney(0);
            player.getProperty().exchangeProperty(new NullPlayer());
        }
        player.setInJail(false);
        GameMaster.INSTANCE.updateGUI();
    }

    public void payRentTo(Player owner, int rentValue) {
        if (player.getMoney() < rentValue) {
            owner.addMoney(player.getMoney());
            player.setMoney(player.getMoney() - rentValue);
        } else {
            player.setMoney(player.getMoney() - rentValue);
            owner.addMoney(rentValue);
        }
        if (player.isBankrupt()) {
            player.setMoney(0);
            player.getProperty().exchangeProperty(owner);
        }
    }

    public void purchase() {
        if (player.getPosition().isAvailable()) {
            Cell c = player.getPosition();
            c.setAvailable(false);
            if (c instanceof PropertyCell) {
                PropertyCell cell = (PropertyCell) c;
                player.getProperty().buyProperty(cell, cell.getPrice());
            }
            if (c instanceof RailRoadCell) {
                RailRoadCell cell = (RailRoadCell) c;
                player.getProperty().buyProperty(cell, cell.getPrice());
            }
            if (c instanceof UtilityCell) {
                UtilityCell cell = (UtilityCell) c;
                player.getProperty().buyProperty(cell, cell.getPrice());
            }
        }
    }
}
