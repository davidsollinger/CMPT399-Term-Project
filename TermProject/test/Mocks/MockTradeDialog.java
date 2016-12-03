package Mocks;

import controller.GameController;
import logic.trade.TradeDeal;
import logic.trade.TradeDialog;

public class MockTradeDialog implements TradeDialog {

    @Override
    public TradeDeal getTradeDeal() {
        GameController gameController = GameController.INSTANCE;
        TradeDeal deal = new TradeDeal();
        deal.setAmount(200);
        deal.setSellerIndex(0);
        deal.setPropertyName(gameController.getGameBoardController().getGameBoard().getCell(1).toString());
        return deal;
    }
}
