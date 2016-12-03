package logic.player;

import java.awt.Color;
import controller.GameController;
import logic.cell.Cell;
import logic.gameBoard.GameBoard;

public class Player {

    private final PlayerProperty property;
    private final PlayerActions actions;

    private boolean inJail;
    private int money;
    private String name;
    private Cell position;
    private Color playerColor;

    public Player() {
        GameController gameController = GameController.INSTANCE;
        GameBoard gameBoard = gameController.getGameBoardController().getGameBoard();
        setInJail(false);
        position = gameBoard.queryCell("Go");
        property = new PlayerProperty(this);
        actions = new PlayerActions(this);
    }

    public static Player createNullPlayer() {
        return new NullPlayer();
    }

    public final void setInJail(boolean inJail) {
        this.inJail = inJail;
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

    public Color getPlayerColor() {
        return new Color(playerColor.getRed(), playerColor.getGreen(),
                playerColor.getBlue(), 75);
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

    public void setPlayerColor(Color playerColor) {
        this.playerColor = playerColor;
    }

    public boolean isBankrupt() {
        return money <= 0;
    }

    public boolean isInJail() {
        return inJail;
    }

    public boolean isColorSet() {
        return null != playerColor;
    }

    public boolean canBuyHouse() {
        return (property.getMonopolies().length != 0);
    }

    public void addMoney(int amount) {
        money += amount;
    }

    public void subtractMoney(int amount) {
        money -= amount;
    }

    @Override
    public String toString() {
        return name;
    }
}
