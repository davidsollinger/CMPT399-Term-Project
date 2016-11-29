package gui;

import gui.infoFormatter.InfoFormatter;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.OverlayLayout;
import javax.swing.border.BevelBorder;
import logic.GameMaster;
import logic.cell.Cell;
import logic.player.Player;

public class GUICell extends JPanel {

    private static final long serialVersionUID = 1L;
    
    private final int CELL_ROWS = 2;
    private final int CELL_COLS = 4;
    private final int LABEL_ROWS = 1;
    private final int LABEL_COLS = 1;
    private final int CELL_WIDTH = 100;
    private final int CELL_HEIGHT = 100;
    private final Cell cell;
    private final JLabel[] lblPlayers = new JLabel[GameMaster.MAX_PLAYERS];
    
    private JLabel lblInfo;

    public GUICell(Cell cell) {
        this.cell = cell;
        super.setLayout(new OverlayLayout(this));
        super.setBorder(new BevelBorder(BevelBorder.LOWERED));
        JPanel pnlPlayer = new JPanel();
        pnlPlayer.setLayout(new GridLayout(CELL_ROWS, CELL_COLS));
        pnlPlayer.setOpaque(false);
        createPlayerLabels(pnlPlayer);
        super.add(pnlPlayer);
        super.setPreferredSize(new Dimension(CELL_WIDTH, CELL_HEIGHT));
        addCellInfo();
        super.doLayout();
    }

    private void addCellInfo() {
        lblInfo = new JLabel();
        displayInfo();
        JPanel pnlInfo = new JPanel();
        pnlInfo.setLayout(new GridLayout(LABEL_ROWS, LABEL_COLS));
        pnlInfo.add(lblInfo);
        add(pnlInfo);
    }

    public void addPlayer(int index) {
        Player player = GameMaster.INSTANCE.getPlayer(index);
        lblPlayers[index].setText(player.getName().substring(0, 1));
        lblPlayers[index].setOpaque(true);
    }

    private void createPlayerLabels(JPanel pnlPlayer) {
        for (int i = 0; i < GameMaster.MAX_PLAYERS; i++) {
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

}
