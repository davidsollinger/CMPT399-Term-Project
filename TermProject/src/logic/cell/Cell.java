package logic.cell;

import logic.player.NullPlayer;
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
    
    public void setName(String name) {
        this.name = name;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
    
    public void setAvailable(boolean available) {
        this.available = available;
    }
    
    
    public void reset() {
        setPlayer(new NullPlayer());
        setAvailable(false);
    }

    public boolean isAvailable() {
        return available;
    }
    
    protected boolean isCurrentPlayer(Player currentPlayer) {
        return getPlayer() == currentPlayer;
    }
    
    // Used as Override
    public String getColorGroup() {
        return "";
    }
    
    // Used as override
    public void playAction() {
    }
    
    // used as override
    protected int getRent() {
        return 0;
    }

    // Used as override
    protected void checkIfCurrentPlayer(Player currentPlayer) {
    }
    
    @Override
    public String toString() {
        return getName();
    }


}
