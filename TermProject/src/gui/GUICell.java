package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.OverlayLayout;
import javax.swing.border.BevelBorder;
import termproject.Cell;
import termproject.GameMaster;
import termproject.Nullable;
import termproject.Player;

public class GUICell extends JPanel implements Nullable {

    private static final long serialVersionUID = 1L;

    private final Cell cell;
    private JLabel lblInfo;
    private final JLabel[] lblPlayers = new JLabel[GameMaster.MAX_PLAYER];

    public GUICell(Cell cell) {
        this.cell = cell;
        super.setLayout(new OverlayLayout(this));
        super.setBorder(new BevelBorder(BevelBorder.LOWERED));
        JPanel pnlPlayer = new JPanel();
        pnlPlayer.setLayout(new GridLayout(2, 4));
        pnlPlayer.setOpaque(false);
        createPlayerLabels(pnlPlayer);
        super.add(pnlPlayer);
        super.setPreferredSize(new Dimension(100, 100));
        addCellInfo();
        super.doLayout();
    }

    private void addCellInfo() {
        lblInfo = new JLabel();
        displayInfo();
        JPanel pnlInfo = new JPanel();
        pnlInfo.setLayout(new GridLayout(1, 1));
        pnlInfo.add(lblInfo);
        add(pnlInfo);
    }

    public void addPlayer(int index) {
        Player player = GameMaster.INSTANCE.getPlayer(index);
        lblPlayers[index].setText(player.getName().substring(0, 1));
        lblPlayers[index].setOpaque(true);
    }

    private void createPlayerLabels(JPanel pnlPlayer) {
        for (int i = 0; i < GameMaster.MAX_PLAYER; i++) {
            lblPlayers[i] = new JLabel();
            lblPlayers[i].setBackground(Color.GREEN);
            pnlPlayer.add(lblPlayers[i]);
        }
    }

    public void displayInfo() {
        lblInfo.setText(InfoFormatter.cellInfo(cell));
        invalidate();
        repaint();
    }

    public Cell getCell() {
        return cell;
    }

    public void removePlayer(int index) {
        lblPlayers[index].setText("");
        lblPlayers[index].setOpaque(false);
        repaint();
    }

    @Override
    public boolean isNull() {
        return true;
    }
}
