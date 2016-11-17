package termproject;

public class Cell implements Nullable {

    private boolean available = true;
    private String name;
    private Player player;

    public String getName() {
        return name;
    }

    public Player getPlayer() {
        return (player == null) ? Player.createNullPlayer() : player;
    }

    protected int getPrice() {
        return 0;
    }

    protected boolean isAvailable() {
        return available;
    }

    // Used as override
    protected void playAction() {
    }

    protected void setAvailable(boolean available) {
        this.available = available;
    }

    public void setName(String name) {
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
        return getPlayer() == currentPlayer;
    }

    @Override
    public boolean isNull() {
        return false;
    }
}
