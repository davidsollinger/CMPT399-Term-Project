package gui;

import logic.trade.RespondDialog;
import logic.player.Player;
import logic.trade.TradeDeal;
import logic.trade.TradeDialog;

public interface MonopolyGUI {
    
    public int[] getDiceRoll();
    
    public void setBuyHouseEnabled(boolean b);

    public void setDrawCardEnabled(boolean b);

    public void setEndTurnEnabled(boolean enabled);

    public void setGetOutOfJailEnabled(boolean b);

    public void setPurchasePropertyEnabled(boolean enabled);

    public void setRollDiceEnabled(boolean b);

    public void setTradeEnabled(int index, boolean b);

    public RespondDialog openRespondDialog(TradeDeal deal);

    public TradeDialog openTradeDialog();

    public void enableEndTurnBtn(int playerIndex);

    public void enablePlayerTurn(int playerIndex);

    public void enablePurchaseBtn(int playerIndex);

    public void movePlayer(int index, int from, int to);

    public void showBuyHouseDialog(Player currentPlayer);

    public void showMessage(String string);

    public int showUtilDiceRoll();

    public void startGame();

    public void update();
    
    public boolean isDrawCardButtonEnabled();

    public boolean isEndTurnButtonEnabled();

    public boolean isGetOutOfJailButtonEnabled();

    public boolean isTradeButtonEnabled(int i);
}
