package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import logic.GameMaster;

public class UtilDiceRoll extends JDialog {

    private static final long serialVersionUID = 1L;
    private final JButton btnDice = new JButton("Roll the Dice!");
    private final JButton btnOK = new JButton("OK");
    private int diceValue;
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
        btnDice.addActionListener((ActionEvent e) -> {
            rollDice();
        });
        btnOK.addActionListener((ActionEvent e) -> {
            okClicked();
        });
        super.pack();
    }
    
    public static int showDialog() {
        UtilDiceRoll dialog = new UtilDiceRoll();
        dialog.setVisible(true);
        return dialog.getDiceValue();
    }
    
    public void okClicked() {
        dispose();
    }

    public void rollDice() {
        int[] diceRoll = GameMaster.INSTANCE.rollDice();
        diceValue = diceRoll[0] + diceRoll[1];
        lblPrompt.setText("You rolled " + getDiceValue());
        btnDice.setEnabled(false);
        btnOK.setEnabled(true);
    }

    public int getDiceValue() {
        return diceValue;
    }
}
