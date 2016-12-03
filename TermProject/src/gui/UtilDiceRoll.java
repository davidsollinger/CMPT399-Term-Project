package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import controller.GameController;

public class UtilDiceRoll extends JDialog {

    private static final long serialVersionUID = 1L;

    private final JButton btnDice = new JButton("Roll the Dice!");
    private final JButton btnOK = new JButton("OK");
    private final JLabel labelPrompt = new JLabel();

    private int diceValue;

    public UtilDiceRoll() {
        super.setModal(true);

        btnOK.setEnabled(false);
        labelPrompt.setText("Please roll the dice to determine your utility bill.");
        Container contentPane = super.getContentPane();
        JPanel panelBtns = new JPanel();
        panelBtns.add(btnDice);
        panelBtns.add(btnOK);
        contentPane.setLayout(new BorderLayout());
        contentPane.add(labelPrompt, BorderLayout.CENTER);
        contentPane.add(panelBtns, BorderLayout.SOUTH);
        btnDice.addActionListener((ActionEvent e) -> {
            rollDice();
        });
        btnOK.addActionListener((ActionEvent e) -> {
            okClicked();
        });
        super.pack();
    }

    public static int showDialog() {
        UtilDiceRoll diceRollDialog = new UtilDiceRoll();
        diceRollDialog.setVisible(true);
        return diceRollDialog.getDiceValue();
    }

    public void okClicked() {
        dispose();
    }

    public void rollDice() {
        GameController gameController = GameController.INSTANCE;
        int[] diceRoll = gameController.getGUIController().rollDice();
        diceValue = diceRoll[0] + diceRoll[1];
        labelPrompt.setText("You rolled " + getDiceValue());
        btnDice.setEnabled(false);
        btnOK.setEnabled(true);
    }

    public int getDiceValue() {
        return diceValue;
    }
}
