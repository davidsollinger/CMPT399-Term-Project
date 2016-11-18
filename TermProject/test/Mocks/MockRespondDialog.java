package Mocks;

import termproject.RespondDialog;
import termproject.TradeDeal;

public class MockRespondDialog implements RespondDialog {

    public MockRespondDialog(TradeDeal deal) {
    }

    @Override
    public boolean getResponse() {
        return true;
    }
}
