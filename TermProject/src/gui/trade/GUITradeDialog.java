package gui.trade;

import controller.GameController;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import logic.cell.Cell;
import logic.player.Player;
import logic.trade.NullTradeDeal;
import logic.trade.TradeDeal;
import logic.trade.TradeDialog;

public class GUITradeDialog extends JDialog implements TradeDialog {

    private static final long serialVersionUID = 1L;

    private final int ROWS = 4;
    private final int COLS = 2;
    private final JButton btnOK, btnCancel;
    private final JComboBox<Player> comboSellers;
    private final JComboBox<Cell> comboProperties;
    private final JTextField txtAmount;
    private final GameController gameController;

    private TradeDeal deal;

    public GUITradeDialog(Frame parent) {
        super(parent);
        super.setTitle("Trade Property");
        super.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);

        gameController = GameController.INSTANCE;

        deal = new NullTradeDeal();
        comboSellers = new JComboBox<>();
        comboProperties = new JComboBox<>();
        txtAmount = new JTextField();
        btnOK = new JButton("OK");
        btnCancel = new JButton("Cancel");

        btnOK.setEnabled(false);
        buildTradeProperty();
        buildTradePropertyContentPane();

        addActionListeners();

        comboSellers.addItemListener((ItemEvent e) -> {
            Player player = (Player) e.getItem();
            updatePropertiesCombo(player);
        });

        super.pack();
        super.setLocationRelativeTo(null);
    }

    private static boolean curPlayerHasEnough(int amount, Player currentPlayer) {
        return amount > 0 && amount < currentPlayer.getMoney();
    }

    private int tryToGetInt() throws HeadlessException {
        try {
            return Integer.parseInt(txtAmount.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(GUITradeDialog.this,
                    "Amount should be an integer", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return -1;
    }
    
    private List<Player> addAllSellers() {
        List<Player> sellers = gameController.getPlayerController().getSellerList();
        sellers.stream().forEach((player) -> {
            comboSellers.addItem(player);
        });
        return sellers;
    }

    private void buildTradeProperty() {
        List<Player> sellers = addAllSellers();
        if (!sellers.isEmpty()) {
            updatePropertiesCombo(sellers.get(0));
        }
    }
    
    private void addActionListeners() {
        btnCancel.addActionListener((ActionEvent e) -> {
            GUITradeDialog.this.setVisible(false);
        });

        btnOK.addActionListener((ActionEvent e) -> {
            int amount = tryToGetInt();
            Cell cell = (Cell) comboProperties.getSelectedItem();
            Player player = (Player) comboSellers.getSelectedItem();
            Player currentPlayer = gameController.getCurrentPlayer();
            if (!curPlayerHasEnough(amount, currentPlayer)) {
                JOptionPane.showMessageDialog(GUITradeDialog.this,
                        "Amount should be greater than zero but less than "
                        + currentPlayer.getMoney(), "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            createTradeDealText(amount, cell, player);
            setVisible(false);
        });
    }
    
    private void buildTradePropertyContentPane() {
        Container contentPane = super.getContentPane();
        contentPane.setLayout(new GridLayout(ROWS, COLS));
        contentPane.add(new JLabel("Sellers"));
        contentPane.add(comboSellers);
        contentPane.add(new JLabel("Properties"));
        contentPane.add(comboProperties);
        contentPane.add(new JLabel("Amount"));
        contentPane.add(txtAmount);
        contentPane.add(btnOK);
        contentPane.add(btnCancel);
    }
    
    private void createTradeDealText(int amount, Cell cell, Player player) {
        deal = new TradeDeal();
        deal.setAmount(amount);
        deal.setPropertyName(cell.getName());
        deal.setSellerIndex(gameController.getPlayerController().getPlayerIndex(player));
    }
    
    private void updatePropertiesCombo(Player player) {
        comboProperties.removeAllItems();
        Cell[] cells = player.getProperty().getAllProperties();
        btnOK.setEnabled(cells.length > 0);
        for (Cell cell : cells) {
            comboProperties.addItem(cell);
        }
    }

    @Override
    public TradeDeal getTradeDeal() {
        return deal;
    }

}
