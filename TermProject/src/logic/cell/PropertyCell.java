package logic.cell;

import logic.GameMaster;
import logic.player.Player;

public class PropertyCell extends Cell {

    private String colorGroup;
    private int housePrice;
    private int numHouses;
    private int rent;
    private int price;

    // Default?
    public PropertyCell() {
        this("", 0, "", 0, 0);
    }

    public PropertyCell(String name, int housePrice, String colorGroup, int rent, int price) {
        super.setName(name);
        this.price = price;
        this.colorGroup = colorGroup;
        this.rent = rent;
        this.housePrice = housePrice;
    }

    @Override
    public String getColorGroup() {
        return colorGroup;
    }

    public int getHousePrice() {
        return housePrice;
    }

    public int getNumHouses() {
        return numHouses;
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public int getRent() {
        int rentToCharge = rent;
        String[] monopolies = getPlayer().getProperty().getMonopolies();
        for (String monopolie : monopolies) {
            rentToCharge = checkForDoubleRent(monopolie);
        }
        if (ownsHouses()) {
            rentToCharge = rent * (numHouses + 1);
        }
        return rentToCharge;
    }

    private int checkForDoubleRent(String monopolie) {
        if (monopolie.equals(colorGroup)) {
            return rent * 2;
        }
        return rent;
    }

    public void setNumHouses(int numHouses) {
        this.numHouses = numHouses;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setColorGroup(String colorGroup) {
        this.colorGroup = colorGroup;
    }

    public void setHousePrice(int housePrice) {
        this.housePrice = housePrice;
    }

    public void setRent(int rent) {
        this.rent = rent;
    }

    private boolean ownsHouses() {
        return numHouses > 0;
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
            currentPlayer = GameMaster.INSTANCE.getCurrentPlayer();
            checkIfCurrentPlayer(currentPlayer);
        }
    }

}