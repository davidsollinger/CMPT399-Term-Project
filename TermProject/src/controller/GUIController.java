package controller;

import logic.Die;
import gui.MonopolyGUI;
import logic.trade.RespondDialog;
import logic.card.Card;
import logic.cell.CardCell;
import logic.player.Player;
import logic.trade.TradeDeal;
import logic.trade.TradeDialog;

public class GUIController {

    private final Die[] dice;
    
    private MonopolyGUI gui;

    public GUIController() {
        dice = new Die[]{new Die(), new Die()};
    }

    public MonopolyGUI getGUI() {
        return gui;
    }

    public int[] getNewDiceRoll() {
        return new int[]{dice[0].getRoll(), dice[1].getRoll()};
    }
    
    public int[] rollDice() {
        if (GameController.INSTANCE.isTestModeEnabled()) {
            return gui.getDiceRoll();
        }
        return getNewDiceRoll();
    }

    public void setGUI(MonopolyGUI gui) {
        this.gui = gui;
    }
    
    public Card btnDrawCardClicked() {
        gui.setDrawCardEnabled(false);
        CardCell cell = (CardCell) GameController.INSTANCE.getCurrentPlayer().getPosition();
        Card card = GameController.INSTANCE.getCardAction(cell);
        gui.setEndTurnEnabled(true);
        return card;
    }

    public void btnBuyHouseClicked() {
        gui.showBuyHouseDialog(GameController.INSTANCE.getCurrentPlayer());
    }

    public void btnEndTurnClicked() {
        setAllButtonEnabled(false);
        GameController.INSTANCE.getCurrentPlayer().getPosition().playAction();
        if (!GameController.INSTANCE.getCurrentPlayer().isBankrupt()) {
            GameController.INSTANCE.switchTurn();
        }
        updateGUI();
    }

    public void btnGetOutOfJailClicked() {
        GameController.INSTANCE.getCurrentPlayer().getActions().getOutOfJail();
        if (GameController.INSTANCE.getCurrentPlayer().isBankrupt()) {
            setAllButtonEnabled(false);
        } else {
            playerIsOutOfJail();
        }
    }

    public void btnPurchasePropertyClicked() {
        Player player = GameController.INSTANCE.getCurrentPlayer();
        player.getActions().purchase();
        gui.setPurchasePropertyEnabled(false);
        updateGUI();
    }

    public void btnRollDiceClicked() {
        int[] rolls = rollDice();
        if (isRollAmountPositive(rolls)) {
            Player player = GameController.INSTANCE.getCurrentPlayer();
            gui.setRollDiceEnabled(false);
            StringBuilder msg = new StringBuilder();
            msg.append(player.getName())
                    .append(", you rolled ")
                    .append(rolls[0])
                    .append(" and ")
                    .append(rolls[1]);
            gui.showMessage(msg.toString());
            GameController.INSTANCE.movePlayer(player, rolls[0] + rolls[1]);
            gui.setBuyHouseEnabled(false);
        }
    }

    public void btnTradeClicked() {
        TradeDialog dialog = gui.openTradeDialog();
        TradeDeal deal = dialog.getTradeDeal();
        if (checkDeal(deal)) {
            RespondDialog rDialog = gui.openRespondDialog(deal);
            if (rDialog.hasResponded()) {
                GameController.INSTANCE.completeTrade(deal);
                updateGUI();
            }
        }
    }
    
    public void updateGUI() {
        gui.update();
    }
    
    private void playerIsOutOfJail() {
        gui.setRollDiceEnabled(true);
        gui.setBuyHouseEnabled(GameController.INSTANCE.getCurrentPlayer().canBuyHouse());
        gui.setGetOutOfJailEnabled(GameController.INSTANCE.getCurrentPlayer().isInJail());
    }
    
    private void setAllButtonEnabled(boolean enabled) {
        gui.setRollDiceEnabled(enabled);
        gui.setPurchasePropertyEnabled(enabled);
        gui.setEndTurnEnabled(enabled);
        gui.setTradeEnabled(GameController.INSTANCE.getTurn(), enabled);
        gui.setBuyHouseEnabled(enabled);
        gui.setDrawCardEnabled(enabled);
        gui.setGetOutOfJailEnabled(enabled);
    }
    
    private boolean checkDeal(TradeDeal deal) {
        return deal.getAmount() > 0;
    }
    
    private static boolean isRollAmountPositive(int[] rolls) {
        return (rolls[0] + rolls[1]) > 0;
    }

}
