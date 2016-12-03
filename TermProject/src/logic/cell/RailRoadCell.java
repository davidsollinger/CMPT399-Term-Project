package logic.cell;

import logic.GameController;
import logic.player.Player;

public class RailRoadCell extends Cell {

    private static int baseRent;
    private static int price;

    public RailRoadCell(String name) {
        super.setName(name);
    }

    public static void setBaseRent(int baseRent) {
        RailRoadCell.baseRent = baseRent;
    }

    public static void setPrice(int price) {
        RailRoadCell.price = price;
    }

    @Override
    public String getColorGroup() {
        return "RAILROAD";
    }

    @Override
    public int getPrice() {
        return RailRoadCell.price;
    }

    @Override
    public int getRent() {
        return RailRoadCell.baseRent * (int) Math.pow(2, getPlayer().getProperty().getNumberOfRR() - 1);
    }

    @Override
    protected void checkIfCurrentPlayer(Player currentPlayer) {
        if (!isCurrentPlayer(currentPlayer)) {
            currentPlayer.getActions().payRentTo(getPlayer(), getRent());
        }
    }

    @Override
    public void playAction() {
        Player currentPlayer;
        if (!isAvailable()) {
            currentPlayer = GameController.INSTANCE.getCurrentPlayer();
            checkIfCurrentPlayer(currentPlayer);
        }
    }
}
