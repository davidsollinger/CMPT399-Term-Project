package termproject;

public class Cell {

    private boolean available = true;
    private String name;
    private Player player;

    public String getName() {
        return this.name;
    }

    public Player getPlayer() {
        return this.player;
    }

    protected int getPrice() {
        return 0;
    }

    protected boolean isAvailable() {
        return this.available;
    }

    protected void playAction() {
    }

    protected void setAvailable(boolean available) {
        this.available = available;
    }

    protected void setName(String name) {
        this.name = name;
    }

    protected void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public String toString() {
        return name;
    }

    protected boolean isCurrentPlayer(Player currentPlayer) {
        return getPlayer() != currentPlayer;
    }
}
