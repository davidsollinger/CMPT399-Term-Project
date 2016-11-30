package gui;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import logic.player.Player;

public class BuyHouseDialog extends JDialog {

    private static final long serialVersionUID = 1L;
    
    private final int ROWS = 3;
    private final int COLS = 2;

    private JComboBox<String> monopolyComboBox;
    private JComboBox<Integer> numberComboBox;

    private final Player player;

    public BuyHouseDialog(Player player) {
        this.player = player;
        Container container = super.getContentPane();
        container.setLayout(new GridLayout(ROWS, COLS));
        container.add(new JLabel("Select monopoly"));
        container.add(buildMonopolyComboBox());
        container.add(new JLabel("Number of houses"));
        container.add(buildNumberComboBox());
        container.add(buildOKButton());
        container.add(buildCancelButton());
        container.doLayout();
        super.pack();
    }

    private JButton buildCancelButton() {
        JButton button =  new JButton("Cancel");
        button.addActionListener((ActionEvent e) -> {
            cancelClicked();
        });
        return button;
    }

    private JComboBox<String> buildMonopolyComboBox() {
        monopolyComboBox = new JComboBox<>(player.getProperty().getMonopolies());
        return monopolyComboBox;
    }

    private JComboBox<Integer> buildNumberComboBox() {
        numberComboBox = new JComboBox<>(new Integer[]{1, 2, 3, 4, 5});
        return numberComboBox;
    }

    private JButton buildOKButton() {
        JButton button = new JButton("OK");
        button.addActionListener((ActionEvent e) -> {
            okClicked();
        });
        return button;
    }

    private void cancelClicked() {
        dispose();
    }

    private void okClicked() {
        String monopoly = (String) monopolyComboBox.getSelectedItem();
        int number = numberComboBox.getSelectedIndex() + 1;
        player.getProperty().addHouse(monopoly, number);
        dispose();
    }
}
