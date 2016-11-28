package termproject;

public class Player {

    private boolean inJail;
    private int money;
    private String name;
    private Cell position;
    private PlayerProperty property;

    protected Player() {
        GameBoard gb = GameMaster.INSTANCE.getGameBoard();
        inJail = false;
        position = gb.queryCell("Go");
        property = new PlayerProperty(this);
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

    public void getOutOfJail() {
        money -= JailCell.BAIL;
        if (isBankrupt()) {
            money = 0;
            property.exchangeProperty(new NullPlayer());
        }
        inJail = false;
        GameMaster.INSTANCE.updateGUI();
    }

    public PlayerProperty getProperty() {
        return property;
    }

    public Cell getPosition() {
        return position;
    }

    public void setInJail(boolean inJail) {
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
        return money <= 0;
    }

    public boolean isInJail() {
        return inJail;
    }

    public boolean canBuyHouse() {
        return (property.getMonopolies().length != 0);
    }

    public void addMoney(int amount) {
        money += amount;
    }

    public void payRentTo(Player owner, int rentValue) {
        if (getMoney() < rentValue) {
            owner.addMoney(getMoney());
            setMoney(getMoney() - rentValue);
        } else {
            setMoney(getMoney() - rentValue);
            owner.addMoney(rentValue);
        }
        if (isBankrupt()) {
            setMoney(0);
            property.exchangeProperty(owner);
        }
    }

    public void purchase() {
        if (getPosition().isAvailable()) {
            Cell c = getPosition();
            c.setAvailable(false);
            if (c instanceof PropertyCell) {
                PropertyCell cell = (PropertyCell) c;
                property.buyProperty(cell, cell.getPrice());
            }
            if (c instanceof RailRoadCell) {
                RailRoadCell cell = (RailRoadCell) c;
                property.buyProperty(cell, cell.getPrice());
            }
            if (c instanceof UtilityCell) {
                UtilityCell cell = (UtilityCell) c;
                property.buyProperty(cell, cell.getPrice());
            }
        }
    }

    @Override
    public String toString() {
        return getName();
    }
}
