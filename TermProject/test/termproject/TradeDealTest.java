package termproject;

import junit.framework.TestCase;

public class TradeDealTest extends TestCase {

    @Override
    public void setUp() {
        GameMaster gameMaster = GameMaster.INSTANCE;
        gameMaster.reset();
        gameMaster.setNumberOfPlayers(2);
        gameMaster.getPlayer(0).setName("Buyer");
        gameMaster.getPlayer(1).setName("Seller");
    }

    public void testMakeMessage() {
        TradeDeal deal = new TradeDeal();
        deal.setAmount(200);
        deal.setPropertyName("Blue 1");
        deal.setSellerIndex(1);
        Player buyer = GameMaster.INSTANCE.getPlayer(0);
        String message = "Buyer wishes to purchase Blue 1 from Seller"
                + " for 200.  Seller, do you wish to trade your property?";
        assertEquals(message, deal.makeMessage());
    }

}
