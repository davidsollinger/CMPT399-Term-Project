package termproject;

import logic.GameController;
import logic.gameBoard.NullGameBoard;
import logic.trade.TradeDeal;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class TradeDealTest {

    @Before
    public void setUp() {
        GameController gameController = GameController.INSTANCE;
        gameController.getGameBoardController().setGameBoard(new NullGameBoard());
        gameController.reset();
        gameController.setNumberOfPlayers(2);
        gameController.getPlayerController().getPlayer(0).setName("Buyer");
        gameController.getPlayerController().getPlayer(1).setName("Seller");
    }

    @Test
    public void testMakeMessage() {
        TradeDeal deal = new TradeDeal();
        deal.setAmount(200);
        deal.setPropertyName("Blue 1");
        deal.setSellerIndex(1);
        String message = "Buyer wishes to purchase Blue 1 from Seller"
                + " for 200.  Seller, do you wish to trade your property?";
        assertEquals(message, deal.makeMessage());
    }

}
