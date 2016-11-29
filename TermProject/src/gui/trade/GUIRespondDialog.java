package gui.trade;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import logic.RespondDialog;
import logic.trade.TradeDeal;

public class GUIRespondDialog extends JDialog implements RespondDialog {
    
    private static final long serialVersionUID = 1L;
    
    private boolean response;
    private final JTextArea txtMessage = new JTextArea();
    
    public GUIRespondDialog() {
        JButton btnYes = new JButton("Yes");
        JButton btnNo = new JButton("No");
        txtMessage.setPreferredSize(new Dimension(300, 200));
        txtMessage.setEditable(false);
        txtMessage.setLineWrap(true);
        
        Container contentPane = super.getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(txtMessage, BorderLayout.CENTER);
        JPanel pnlButtons = new JPanel();
        pnlButtons.add(btnYes);
        pnlButtons.add(btnNo);
        contentPane.add(pnlButtons, BorderLayout.SOUTH);
        
        btnYes.addActionListener((ActionEvent e) -> {
            response = true;
            setVisible(false);
        });
        
        btnNo.addActionListener((ActionEvent e) -> {
            response = false;
            setVisible(false);
        });
        
        super.setModal(true);
        super.pack();
    }
    
    @Override
    public boolean hasResponded() {
        return response;
    }
    
    public void setDeal(TradeDeal deal) {
        txtMessage.setText(deal.makeMessage());
    }
    
}
