package logic.player;

import logic.GameMaster;
import logic.cell.Cell;
import logic.gameBoard.GameBoard;

public class Player {
    
    private boolean inJail;
    private int money;
    private String name;
    private Cell position;
    private final PlayerProperty property;
    private final PlayerActions actions;

    public Player() {
        GameBoard gb = GameMaster.INSTANCE.getGameBoard();
        setInJail(false);
        position = gb.queryCell("Go");
        property = new PlayerProperty(this);
        actions = new PlayerActions(this);
    }

    public static Player createNullPlayer() {
        return new NullPlayer();
    }

    public String getName() {
        return name;
    }

    public int getMoney() {
        return money;
    }

    public PlayerProperty getProperty() {
        return property;
    }

    public Cell getPosition() {
        return position;
    }

    public PlayerActions getActions() {
        return actions;
    }

    public final void setInJail(boolean inJail) {
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

    public boolean isBankrupt() {
        return getMoney() <= 0;
    }

    public boolean isInJail() {
        return inJail;
    }

    public boolean canBuyHouse() {
        return (property.getMonopolies().length != 0);
    }

    public void addMoney(int amount) {
        setMoney(getMoney() + amount);
    }

    @Override
    public String toString() {
        return getName();
    }
}
