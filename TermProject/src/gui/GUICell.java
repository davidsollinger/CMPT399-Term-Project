package gui;

import gui.infoFormatter.InfoFormatter;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.OverlayLayout;
import javax.swing.border.BevelBorder;
import controller.GameController;
import logic.cell.Cell;

public class GUICell extends JPanel {

    private static final long serialVersionUID = 1L;

    private final int CELL_ROWS = 2;
    private final int CELL_COLS = 4;
    private final int LABEL_ROWS = 1;
    private final int LABEL_COLS = 1;
    private final int CELL_WIDTH = 100;
    private final int CELL_HEIGHT = 100;
    private final Cell cell;
    private final JLabel[] playerLabels = new JLabel[GameController.MAX_PLAYERS];

    private JLabel infoLabel;

    private GameController gameController;

    public GUICell(Cell cell) {
        this.cell = cell;
        gameController = GameController.INSTANCE;
        super.setLayout(new OverlayLayout(this));
        super.setBorder(new BevelBorder(BevelBorder.LOWERED));
        setPlayerPanel();
        super.setPreferredSize(new Dimension(CELL_WIDTH, CELL_HEIGHT));
        addCellInfo();
        super.doLayout();
    }

    private void setPlayerPanel() {
        JPanel playerPanel = new JPanel();
        playerPanel.setLayout(new GridLayout(CELL_ROWS, CELL_COLS));
        playerPanel.setOpaque(false);
        createPlayerLabels(playerPanel);
        super.add(playerPanel);
    }

    private void addCellInfo() {
        infoLabel = new JLabel();
        displayInfo();
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(LABEL_ROWS, LABEL_COLS));
        infoPanel.add(infoLabel);
        add(infoPanel);
    }

    public void setPlayerLabelsOpaque(int index) {
        playerLabels[index].setOpaque(true);
    }

    private void createPlayerLabels(JPanel playerPanel) {
        for (int i = 0; i < GameController.MAX_PLAYERS; i++) {
            playerLabels[i] = new JLabel();
            if (checkPlayerColor(i)) {
                playerLabels[i].setBackground(gameController.getPlayerController().
                        getPlayer(i).getPlayerColor());
            }
            playerPanel.add(playerLabels[i]);
        }
    }

    private boolean checkPlayerColor(int index) {
        return (index < gameController.getNumberOfPlayers())
                && (gameController.getPlayerController().getPlayer(index).isColorSet());
    }

    public void displayInfo() {
        infoLabel.setText(InfoFormatter.getCellInfo(cell));
        invalidate();
        repaint();
    }

    public Cell getCell() {
        return cell;
    }

    public void removePlayer(int index) {
        playerLabels[index].setOpaque(false);
        repaint();
    }

}
