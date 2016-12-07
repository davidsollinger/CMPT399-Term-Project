package logic.cell;

import controller.GameController;
import logic.player.NullPlayer;
import logic.player.Player;

public class PropertyCell extends Cell {

    private int housePrice;
    private int numHouses;
    private int rent;
    private int price;
    private String colorGroup;

    public PropertyCell(String name, int housePrice, String colorGroup, int rent, int price) {
        super.setName(name);
        this.price = price;
        this.colorGroup = colorGroup;
        this.rent = rent;
        this.housePrice = housePrice;
    }

    public int getHousePrice() {
        return housePrice;
    }

    public int getNumHouses() {
        return numHouses;
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
    
    private int checkForDoubleRent(String monopolie) {
        if (monopolie.equals(colorGroup)) {
            return rent * 2;
        }
        return rent;
    }

    private boolean ownsHouses() {
        return numHouses > 0;
    }

    @Override
    public String getColorGroup() {
        return colorGroup;
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
    
    @Override
    public void reset() {
        setPlayer(new NullPlayer());
        setAvailable(false);
        setNumHouses(0);
    }

    @Override
    public void playAction() {
        Player currentPlayer;
        if (!isAvailable()) {
            currentPlayer = GameController.INSTANCE.getCurrentPlayer();
            checkIfCurrentPlayer(currentPlayer);
        }
    }

    @Override
    protected void checkIfCurrentPlayer(Player currentPlayer) {
        if (!isCurrentPlayer(currentPlayer)) {
            currentPlayer.getActions().payRentTo(getPlayer(), getRent());
        }
    }

}
