package Mocks;

import logic.GameMaster;
import logic.trade.TradeDeal;
import logic.trade.TradeDialog;

public class MockTradeDialog implements TradeDialog {

    @Override
    public TradeDeal getTradeDeal() {
        TradeDeal deal = new TradeDeal();
        deal.setAmount(200);
        deal.setSellerIndex(0);
        deal.setPropertyName(GameMaster.INSTANCE.getGameBoard().getCell(1).toString());
        return deal;
    }
}
