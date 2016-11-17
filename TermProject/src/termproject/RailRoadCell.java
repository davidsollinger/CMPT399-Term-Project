package termproject;

public class RailRoadCell extends Cell {

    private static int baseRent;
    private static int price;
    
    private static final String COLOR_GROUP = "RAILROAD";

    public static void setBaseRent(int baseRent) {
        RailRoadCell.baseRent = baseRent;
    }

    public static void setPrice(int price) {
        RailRoadCell.price = price;
    }

    @Override
    public int getPrice() {
        return RailRoadCell.price;
    }

    public int getRent() {
        return RailRoadCell.baseRent * (int) Math.pow(2, getPlayer().numberOfRR() - 1);
    }
    
    public static String getColorGroup () {
        return COLOR_GROUP;
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
}
