package Mocks;

import logic.trade.RespondDialog;
import logic.trade.TradeDeal;

public class MockRespondDialog implements RespondDialog {

    public MockRespondDialog(TradeDeal deal) {
    }

    @Override
    public boolean hasResponded() {
        return true;
    }
}
