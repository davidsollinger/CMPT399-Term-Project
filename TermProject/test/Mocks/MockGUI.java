package Mocks;

import logic.trade.RespondDialog;
import gui.MainWindow;
import logic.player.Player;
import logic.trade.TradeDeal;
import logic.trade.TradeDialog;

public class MockGUI extends MainWindow {

    private final boolean[] btnTradeState = new boolean[2];
    
    private boolean btnDrawCardState, btnEndTurnState, btnGetOutOfJailState;

    @Override
    public int[] getDiceRoll() {
        int roll[] = new int[2];
        roll[0] = 2;
        roll[1] = 3;
        return roll;
    }
    
    @Override
    public void setBuyHouseEnabled(boolean b) {
    }

    @Override
    public void setDrawCardEnabled(boolean b) {
        btnDrawCardState = b;
    }

    @Override
    public void setEndTurnEnabled(boolean enabled) {
        btnEndTurnState = enabled;
    }

    @Override
    public void setGetOutOfJailEnabled(boolean b) {
        btnGetOutOfJailState = b;
    }

    @Override
    public void setTradeEnabled(int index, boolean b) {
        btnTradeState[index] = b;
    }
    
    @Override
    public void enableEndTurnBtn(int playerIndex) {
    }

    @Override
    public void enablePlayerTurn(int playerIndex) {
    }

    @Override
    public void enablePurchaseBtn(int playerIndex) {
    }

    @Override
    public RespondDialog openRespondDialog(TradeDeal deal) {
        RespondDialog dialog = new MockRespondDialog(deal);
        return dialog;
    }

    @Override
    public TradeDialog openTradeDialog() {
        TradeDialog dialog = new MockTradeDialog();
        return dialog;
    }

    @Override
    public int showUtilDiceRoll() {
        return 10;
    }
    
    @Override
    public void movePlayer(int index, int from, int to) {
    }
    
    @Override
    public boolean isDrawCardButtonEnabled() {
        return btnDrawCardState;
    }

    @Override
    public boolean isEndTurnButtonEnabled() {
        return btnEndTurnState;
    }

    @Override
    public boolean isGetOutOfJailButtonEnabled() {
        return btnGetOutOfJailState;
    }

    @Override
    public boolean isTradeButtonEnabled(int i) {
        return btnTradeState[i];
    }
    
    // Ignore override
    @Override
    public void setPurchasePropertyEnabled(boolean enabled) {
    }

    // Ignore override
    @Override
    public void setRollDiceEnabled(boolean b) {
    }
    
    // Ignore override
    @Override
    public void showBuyHouseDialog(Player currentPlayer) {
    }

    // Ignore overide
    @Override
    public void showMessage(String string) {
    }

    // Ignore override
    @Override
    public void startGame() {
    }

    // Ignore override
    @Override
    public void update() {
    }
}
