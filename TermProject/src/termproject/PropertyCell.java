package termproject;

public class PropertyCell extends Cell {

    private String colorGroup;
    private int housePrice;
    private int numHouses;
    private int rent;
    private int sellPrice;

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
        return sellPrice;
    }

    public int getRent() {
        int rentToCharge = rent;
        String[] monopolies = getPlayer().getMonopolies();
        for (String monopolie : monopolies) {
            if (monopolie.equals(colorGroup)) {
                rentToCharge = rent * 2;
            }
        }
        if (ownsHouses()) {
            rentToCharge = rent * (numHouses + 1);
        }
        return rentToCharge;
    }

    private boolean ownsHouses() {
        return numHouses > 0;
    }

    @Override
    public void playAction() {
        Player currentPlayer;
        if (!isAvailable()) {
            currentPlayer = GameMaster.INSTANCE.getCurrentPlayer();
            if (!isCurrentPlayer(currentPlayer)) {
                currentPlayer.payRentTo(getPlayer(), getRent());
            }
        }
    }

    public void setColorGroup(String colorGroup) {
        this.colorGroup = colorGroup;
    }

    public void setHousePrice(int housePrice) {
        this.housePrice = housePrice;
    }

    public void setNumHouses(int numHouses) {
        this.numHouses = numHouses;
    }

    public void setPrice(int sellPrice) {
        this.sellPrice = sellPrice;
    }

    public void setRent(int rent) {
        this.rent = rent;
    }
}
