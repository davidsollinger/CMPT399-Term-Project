package gui;

import java.awt.Container;
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
import termproject.Cell;
import termproject.GameMaster;
import termproject.NullTradeDeal;
import termproject.Player;
import termproject.TradeDeal;
import termproject.TradeDialog;

public class GUITradeDialog extends JDialog implements TradeDialog {

    private static final long serialVersionUID = 1L;

    private JButton btnOK, btnCancel;
    private JComboBox<Player> cboSellers;
    private JComboBox<Cell> cboProperties;
    private TradeDeal deal;
    private JTextField txtAmount;

    public GUITradeDialog(Frame parent) {
        super(parent);

        super.setTitle("Trade Property");
        deal = new NullTradeDeal();
        cboSellers = new JComboBox<>();
        cboProperties = new JComboBox<>();
        txtAmount = new JTextField();
        btnOK = new JButton("OK");
        btnCancel = new JButton("Cancel");

        btnOK.setEnabled(false);

        buildSellersCombo();
        super.setModal(true);

        Container contentPane = super.getContentPane();
        contentPane.setLayout(new GridLayout(4, 2));
        contentPane.add(new JLabel("Sellers"));
        contentPane.add(cboSellers);
        contentPane.add(new JLabel("Properties"));
        contentPane.add(cboProperties);
        contentPane.add(new JLabel("Amount"));
        contentPane.add(txtAmount);
        contentPane.add(btnOK);
        contentPane.add(btnCancel);

        btnCancel.addActionListener((ActionEvent e) -> {
            GUITradeDialog.this.setVisible(false);
        });

        cboSellers.addItemListener((ItemEvent e) -> {
            Player player = (Player) e.getItem();
            updatePropertiesCombo(player);
        });

        btnOK.addActionListener((ActionEvent e) -> {
            int amount = tryToGetInt();
            if (amount == -1) {
                return;
            }
            Cell cell = (Cell) cboProperties.getSelectedItem();
            if (cell.isNull()) {
                return;
            }
            Player player = (Player) cboSellers.getSelectedItem();
            Player currentPlayer = GameMaster.INSTANCE.getCurrentPlayer();
            if (currentPlayer.getMoney() > amount) {
                deal = new TradeDeal();
                deal.setAmount(amount);
                deal.setPropertyName(cell.getName());
                deal.setSellerIndex(GameMaster.INSTANCE.getPlayerIndex(player));
            }
            setVisible(false);
        });

        super.pack();
    }

    private int tryToGetInt() throws HeadlessException {
        try {
            return Integer.parseInt(txtAmount.getText());
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(GUITradeDialog.this,
                    "Amount should be an integer", "Error", JOptionPane.ERROR_MESSAGE);
            return -1;
        }
    }

    private void buildSellersCombo() {
        List<Player> sellers = GameMaster.INSTANCE.getSellerList();
        sellers.stream().forEach((player) -> {
            cboSellers.addItem(player);
        });
        if (sellers.size() > 0) {
            updatePropertiesCombo(sellers.get(0));
        }
    }

    @Override
    public TradeDeal getTradeDeal() {
        return deal;
    }

    private void updatePropertiesCombo(Player player) {
        cboProperties.removeAllItems();
        Cell[] cells = player.getAllProperties();
        btnOK.setEnabled(cells.length > 0);
        for (Cell cell : cells) {
            cboProperties.addItem(cell);
        }
    }

}
