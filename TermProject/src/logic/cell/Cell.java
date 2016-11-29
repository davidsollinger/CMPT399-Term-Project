package logic.cell;

import logic.player.Player;

public class Cell {

    private boolean available = true;
    private String name;
    private Player player;

    public String getName() {
        return name;
    }

    public Player getPlayer() {
        return (player == null) ? Player.createNullPlayer() : player;
    }

    public int getPrice() {
        return 0;
    }

    public boolean isAvailable() {
        return available;
    }

    // Used as override
    public String getColorGroup() {
        return "";
    }

    // Used as override
    public void playAction() {
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public String toString() {
        return getName();
    }

    protected boolean isCurrentPlayer(Player currentPlayer) {
        return getPlayer() == currentPlayer;
    }

    // used as override
    protected int getRent() {
        return 0;
    }

    // Used as override
    protected void checkIfCurrentPlayer(Player currentPlayer) {
    }

}
