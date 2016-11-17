package gui;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import termproject.Player;

public class BuyHouseDialog extends JDialog {

    private static final long serialVersionUID = 1L;

    private JComboBox<String> cboMonopoly;
    private JComboBox<Integer> cboNumber;

    private final Player player;

    public BuyHouseDialog(Player player) {
        this.player = player;
        Container c = super.getContentPane();
        c.setLayout(new GridLayout(3, 2));
        c.add(new JLabel("Select monopoly"));
        c.add(buildMonopolyComboBox());
        c.add(new JLabel("Number of houses"));
        c.add(buildNumberComboBox());
        c.add(buildOKButton());
        c.add(buildCancelButton());
        c.doLayout();
        super.pack();
    }

    private JButton buildCancelButton() {
        JButton btn = new JButton("Cancel");
        btn.addActionListener((ActionEvent e) -> {
            cancelClicked();
        });
        return btn;
    }

    private JComboBox<String> buildMonopolyComboBox() {
        cboMonopoly = new JComboBox<>(player.getMonopolies());
        return cboMonopoly;
    }

    private JComboBox<Integer> buildNumberComboBox() {
        cboNumber = new JComboBox<>(new Integer[]{
            1, 2, 3, 4, 5});
        return cboNumber;
    }

    private JButton buildOKButton() {
        JButton btn = new JButton("OK");
        btn.addActionListener((ActionEvent e) -> {
            okClicked();
        });
        return btn;
    }

    private void cancelClicked() {
        dispose();
    }

    private void okClicked() {
        String monopoly = (String) cboMonopoly.getSelectedItem();
        int number = cboNumber.getSelectedIndex() + 1;
        player.purchaseHouse(monopoly, number);
        dispose();
    }
}
