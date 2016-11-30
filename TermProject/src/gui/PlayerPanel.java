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
    private final JLabel moneyLabel;
    private final JLabel nameLabel;
    private final Player player;
    private final JTextArea propertyText;

    public PlayerPanel(Player player) {
        this.player = player;
        btnRollDice = new JButton("Roll Dice");
        btnPurchaseProperty = new JButton("Purchase Property");
        btnEndTurn = new JButton("End Turn");
        btnBuyHouse = new JButton("Buy House");
        btnGetOutOfJail = new JButton("Get Out of Jail");
        btnDrawCard = new JButton("Draw Card");
        btnTrade = new JButton("Trade");
        nameLabel = new JLabel();
        moneyLabel = new JLabel();
        propertyText = new JTextArea(TEXT_AREA_ROWS, TEXT_AREA_COLS);
        setUpPanels();
        propertyText.setEnabled(false);

        initPlayerPanelButtons();
        addActionListeners();
    }
    
    private void setUpPanels() {
        JPanel actionPanel = new JPanel();
        JPanel infoPanel = new JPanel();
        JPanel namePanel = new JPanel();
        //namePanel.setBackground(player.getPlayerColor());
        JPanel panelProperties = new JPanel();
        infoPanel.setLayout(new BorderLayout());
        infoPanel.add(namePanel, BorderLayout.NORTH);
        infoPanel.add(panelProperties, BorderLayout.CENTER);
        
        panelProperties.setLayout(new OverlayLayout(panelProperties));

        namePanel.add(nameLabel);
        namePanel.add(moneyLabel);
        panelProperties.add(propertyText);

        initPanelAction(actionPanel);
        infoPanel.validate();
        namePanel.validate();
        panelProperties.validate();
        addElementsToJPanel(infoPanel, actionPanel);
    }

    private void addElementsToJPanel(JPanel infoPanel, JPanel actionPanel) {
        super.validate();
        super.setLayout(new BorderLayout());
        super.add(infoPanel, BorderLayout.CENTER);
        super.add(actionPanel, BorderLayout.SOUTH);
        super.setBorder(new BevelBorder(BevelBorder.RAISED));
    }

    private void initPanelAction(JPanel actionPanel) {
        actionPanel.setLayout(new GridLayout(PNL_ACTION_ROWS, PNL_ACTION_COLS));
        actionPanel.add(btnBuyHouse);
        actionPanel.add(btnRollDice);
        actionPanel.add(btnPurchaseProperty);
        actionPanel.add(btnGetOutOfJail);
        actionPanel.add(btnEndTurn);
        actionPanel.add(btnDrawCard);
        actionPanel.add(btnTrade);
        actionPanel.validate();
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
        setPlayerLabels();
        StringBuilder buf = new StringBuilder();
        Cell[] cells = player.getProperty().getAllProperties();
        for (Cell cell : cells) {
            buf.append(cell).append("\n");
        }
        propertyText.setText(buf.toString());
    }
    
    private void setPlayerLabels() {
        nameLabel.setText(player.getName());
        moneyLabel.setText("$ " + player.getMoney());
        
        nameLabel.setForeground(player.getPlayerColor());
        moneyLabel.setForeground(player.getPlayerColor());
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
