package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.OverlayLayout;
import javax.swing.border.BevelBorder;
import logic.card.Card;
import logic.cell.Cell;
import logic.GameMaster;
import logic.player.Player;

public class PlayerPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private final int TEXT_AREA_ROWS = 30;
    private final int TEXT_AREA_COLS = 70;
    private final int PNL_ACTION_ROWS = 3;
    private final int PNL_ACTION_COLS = 3;

    private final JButton btnBuyHouse;
    private final JButton btnDrawCard;
    private final JButton btnEndTurn;
    private final JButton btnGetOutOfJail;
    private final JButton btnPurchaseProperty;
    private final JButton btnRollDice;
    private final JButton btnTrade;
    private final JLabel lableMoney;
    private final JLabel lableName;
    private final Player player;
    private final JTextArea txtProperty;

    public PlayerPanel(Player player) {
        this.player = player;
        btnRollDice = new JButton("Roll Dice");
        btnPurchaseProperty = new JButton("Purchase Property");
        btnEndTurn = new JButton("End Turn");
        btnBuyHouse = new JButton("Buy House");
        btnGetOutOfJail = new JButton("Get Out of Jail");
        btnDrawCard = new JButton("Draw Card");
        btnTrade = new JButton("Trade");
        lableName = new JLabel();
        lableMoney = new JLabel();
        txtProperty = new JTextArea(TEXT_AREA_ROWS, TEXT_AREA_COLS);
        
        JPanel pnlAction = new JPanel();
        JPanel pnlInfo = new JPanel();
        JPanel pnlName = new JPanel();
        JPanel pnlProperties = new JPanel();

        txtProperty.setEnabled(false);

        pnlInfo.setLayout(new BorderLayout());
        pnlInfo.add(pnlName, BorderLayout.NORTH);
        pnlInfo.add(pnlProperties, BorderLayout.CENTER);
        
        pnlProperties.setLayout(new OverlayLayout(pnlProperties));

        pnlName.add(lableName);
        pnlName.add(lableMoney);
        pnlProperties.add(txtProperty);

        initPanelAction(pnlAction);
        pnlInfo.validate();
        pnlName.validate();
        pnlProperties.validate();
        addElementsToJPanel(pnlInfo, pnlAction);
        initPlayerPanelButtons();
        addActionListeners();
    }

    private void addElementsToJPanel(JPanel pnlInfo, JPanel pnlAction) {
        super.validate();
        super.setLayout(new BorderLayout());
        super.add(pnlInfo, BorderLayout.CENTER);
        super.add(pnlAction, BorderLayout.SOUTH);
        super.setBorder(new BevelBorder(BevelBorder.RAISED));
    }

    private void initPanelAction(JPanel pnlAction) {
        pnlAction.setLayout(new GridLayout(PNL_ACTION_ROWS, PNL_ACTION_COLS));
        pnlAction.add(btnBuyHouse);
        pnlAction.add(btnRollDice);
        pnlAction.add(btnPurchaseProperty);
        pnlAction.add(btnGetOutOfJail);
        pnlAction.add(btnEndTurn);
        pnlAction.add(btnDrawCard);
        pnlAction.add(btnTrade);
        pnlAction.validate();
    }

    private void initPlayerPanelButtons() {
        btnRollDice.setEnabled(false);
        btnPurchaseProperty.setEnabled(false);
        btnEndTurn.setEnabled(false);
        btnBuyHouse.setEnabled(false);
        btnGetOutOfJail.setEnabled(false);
        btnDrawCard.setEnabled(false);
        btnTrade.setEnabled(false);
    }

    private void addActionListeners() {
        btnRollDice.addActionListener((ActionEvent e) -> {
            GameMaster.INSTANCE.btnRollDiceClicked();
        });

        btnEndTurn.addActionListener((ActionEvent e) -> {
            GameMaster.INSTANCE.btnEndTurnClicked();
        });

        btnPurchaseProperty.addActionListener((ActionEvent e) -> {
            GameMaster.INSTANCE.btnPurchasePropertyClicked();
        });

        btnBuyHouse.addActionListener((ActionEvent e) -> {
            GameMaster.INSTANCE.btnBuyHouseClicked();
        });

        btnGetOutOfJail.addActionListener((ActionEvent e) -> {
            GameMaster.INSTANCE.btnGetOutOfJailClicked();
        });

        btnDrawCard.addActionListener((ActionEvent e) -> {
            Card card = GameMaster.INSTANCE.btnDrawCardClicked();
            JOptionPane
                    .showMessageDialog(PlayerPanel.this, card.getLabel());
            displayInfo();
        });

        btnTrade.addActionListener((ActionEvent e) -> {
            GameMaster.INSTANCE.btnTradeClicked();
        });
    }

    public void displayInfo() {
        lableName.setText(player.getName());
        lableMoney.setText("$ " + player.getMoney());
        StringBuilder buf = new StringBuilder();
        Cell[] cells = player.getProperty().getAllProperties();
        for (Cell cell : cells) {
            buf.append(cell).append("\n");
        }
        txtProperty.setText(buf.toString());
    }

    public boolean isBuyHouseButtonEnabled() {
        return btnBuyHouse.isEnabled();
    }

    public boolean isDrawCardButtonEnabled() {
        return btnDrawCard.isEnabled();
    }

    public boolean isEndTurnButtonEnabled() {
        return btnEndTurn.isEnabled();
    }

    public boolean isGetOutOfJailButtonEnabled() {
        return btnGetOutOfJail.isEnabled();
    }

    public boolean isPurchasePropertyButtonEnabled() {
        return btnPurchaseProperty.isEnabled();
    }

    public boolean isRollDiceButtonEnabled() {
        return btnRollDice.isEnabled();
    }

    public boolean isTradeButtonEnabled() {
        return btnTrade.isEnabled();
    }

    public void setBuyHouseEnabled(boolean b) {
        btnBuyHouse.setEnabled(b);
    }

    public void setDrawCardEnabled(boolean b) {
        btnDrawCard.setEnabled(b);
    }

    public void setEndTurnEnabled(boolean enabled) {
        btnEndTurn.setEnabled(enabled);
    }

    public void setGetOutOfJailEnabled(boolean b) {
        btnGetOutOfJail.setEnabled(b);
    }

    public void setPurchasePropertyEnabled(boolean enabled) {
        btnPurchaseProperty.setEnabled(enabled);
    }

    public void setRollDiceEnabled(boolean enabled) {
        btnRollDice.setEnabled(enabled);
    }

    public void setTradeEnabled(boolean b) {
        btnTrade.setEnabled(b);
    }
}
