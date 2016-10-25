package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import termproject.GameMaster;

public class UtilDiceRoll extends JDialog {

    private static final long serialVersionUID = 1L;

    public static int showDialog() {
        UtilDiceRoll dialog = new UtilDiceRoll();
        dialog.setVisible(true);
        return dialog.diceValue;
    }
    private final JButton btnDice = new JButton("Roll the Dice!");
    private final JButton btnOK = new JButton("OK");
    public int diceValue;
    private final JLabel lblPrompt = new JLabel();

    public UtilDiceRoll() {
        super.setModal(true);
        btnOK.setEnabled(false);
        lblPrompt.setText("Please roll the dice to determine your utility bill.");
        Container contentPane = super.getContentPane();
        JPanel pnlButtons = new JPanel();
        pnlButtons.add(btnDice);
        pnlButtons.add(btnOK);
        contentPane.setLayout(new BorderLayout());
        contentPane.add(lblPrompt, BorderLayout.CENTER);
        contentPane.add(pnlButtons, BorderLayout.SOUTH);
        btnDice.addActionListener((ActionEvent arg0) -> {
            rollDice();
        });
        btnOK.addActionListener((ActionEvent arg0) -> {
            okClicked();
        });
        super.pack();
    }

    public void okClicked() {
        this.dispose();
    }

    public void rollDice() {
        int[] diceRoll = GameMaster.instance().rollDice();
        this.diceValue = diceRoll[0] + diceRoll[1];
        lblPrompt.setText("You rolled " + diceValue);
        btnDice.setEnabled(false);
        btnOK.setEnabled(true);
    }
}
