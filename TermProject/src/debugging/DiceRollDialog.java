package debugging;

import java.awt.Container;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class DiceRollDialog extends JDialog {

    private static final long serialVersionUID = 1L;

    private JButton btnOK, btnCancel;
    private JTextField txtDiceRoll;
    private int[] diceRoll;

    public DiceRollDialog(Frame parent) {
        super(parent);

        super.setTitle("Dice Roll Dialog");
        txtDiceRoll = new JTextField(2);
        btnOK = new JButton("OK");
        btnCancel = new JButton("Cancel");

        super.setModal(true);

        Container contentPane = super.getContentPane();
        contentPane.setLayout(new GridLayout(2, 2));
        contentPane.add(new JLabel("Amount"));
        contentPane.add(txtDiceRoll);
        contentPane.add(btnOK);
        contentPane.add(btnCancel);

        btnCancel.addActionListener((ActionEvent e) -> {
            setVisible(false);
            diceRoll = new int[2];
            diceRoll[0] = 0;
            diceRoll[1] = 0;
        });

        btnOK.addActionListener((ActionEvent e) -> {
            int amount = tryToGetInt();
            if (amount == -1) {
                return;
            }
            if (amount > 0) {
                diceRoll = new int[2];
                if ((amount % 2) == 0) {
                    diceRoll[0] = amount / 2;
                    diceRoll[1] = amount / 2;
                } else {
                    diceRoll[0] = amount / 2;
                    diceRoll[1] = (amount / 2) + 1;
                }
            }
            setVisible(false);
        });

        super.pack();
    }

    private int tryToGetInt() throws HeadlessException {
        try {
            return Integer.parseInt(txtDiceRoll.getText());
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(DiceRollDialog.this,
                    "Amount should be an integer", "Error", JOptionPane.ERROR_MESSAGE);
            return -1;
        }
    }

    public int[] getDiceRoll() {
        return Arrays.copyOf(diceRoll, diceRoll.length);
    }
}
