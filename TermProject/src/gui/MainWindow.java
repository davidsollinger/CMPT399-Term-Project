package gui;

import debugging.DiceRollDialog;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import termproject.Cell;
import termproject.GameBoard;
import termproject.GameMaster;
import termproject.MonopolyGUI;
import termproject.Player;
import termproject.RespondDialog;
import termproject.TradeDeal;
import termproject.TradeDialog;

public class MainWindow extends JFrame implements MonopolyGUI {

    private static final long serialVersionUID = 1L;

    private final JPanel eastPanel = new JPanel();
    private final List<GUICell> guiCells = new ArrayList<>();

    private final JPanel northPanel = new JPanel();
    private PlayerPanel[] playerPanels;
    private final JPanel southPanel = new JPanel();
    private final JPanel westPanel = new JPanel();

    public MainWindow() {
        northPanel.setBorder(new LineBorder(Color.BLACK));
        southPanel.setBorder(new LineBorder(Color.BLACK));
        westPanel.setBorder(new LineBorder(Color.BLACK));
        eastPanel.setBorder(new LineBorder(Color.BLACK));

        Container c = super.getContentPane();
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension d = tk.getScreenSize();
        super.setSize(d);
        c.add(northPanel, BorderLayout.NORTH);
        c.add(southPanel, BorderLayout.SOUTH);
        c.add(eastPanel, BorderLayout.EAST);
        c.add(westPanel, BorderLayout.WEST);

        super.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    private void addCells(JPanel panel, List<Cell> cells) {
        for (int x = 0; x < cells.size(); x++) {
            GUICell cell = new GUICell(cells.get(x));
            panel.add(cell);            
            guiCells.add(cell);
        }
    }

    private void buildPlayerPanels() {
        GameMaster master = GameMaster.INSTANCE;
        JPanel infoPanel = new JPanel();
        int players = master.getNumberOfPlayers();
        infoPanel.setLayout(new GridLayout(2, (players + 1) / 2));
        getContentPane().add(infoPanel, BorderLayout.CENTER);
        playerPanels = new PlayerPanel[master.getNumberOfPlayers()];
        for (int i = 0; i < master.getNumberOfPlayers(); i++) {
            playerPanels[i] = new PlayerPanel(master.getPlayer(i));
            infoPanel.add(playerPanels[i]);
            playerPanels[i].displayInfo();
        }
    }

    @Override
    public void enableEndTurnBtn(int playerIndex) {
        playerPanels[playerIndex].setEndTurnEnabled(true);
    }

    @Override
    public void enablePlayerTurn(int playerIndex) {
        playerPanels[playerIndex].setRollDiceEnabled(true);

    }

    @Override
    public void enablePurchaseBtn(int playerIndex) {
        playerPanels[playerIndex].setPurchasePropertyEnabled(true);
    }

    @Override
    public int[] getDiceRoll() {
        DiceRollDialog dialog = new DiceRollDialog(this);
        dialog.setVisible(true);
        return dialog.getDiceRoll();
    }

    @Override
    public boolean isDrawCardButtonEnabled() {
        int currentPlayerIndex = GameMaster.INSTANCE.getCurrentPlayerIndex();
        return playerPanels[currentPlayerIndex].isDrawCardButtonEnabled();
    }

    @Override
    public boolean isEndTurnButtonEnabled() {
        int currentPlayerIndex = GameMaster.INSTANCE.getCurrentPlayerIndex();
        return playerPanels[currentPlayerIndex].isEndTurnButtonEnabled();
    }

    @Override
    public boolean isGetOutOfJailButtonEnabled() {
        int currentPlayerIndex = GameMaster.INSTANCE.getCurrentPlayerIndex();
        return playerPanels[currentPlayerIndex].isGetOutOfJailButtonEnabled();
    }

    @Override
    public boolean isTradeButtonEnabled(int i) {
        return playerPanels[i].isTradeButtonEnabled();
    }

    @Override
    public void movePlayer(int index, int from, int to) {
        GUICell fromCell = queryCell(from);
        GUICell toCell = queryCell(to);
        fromCell.removePlayer(index);
        toCell.addPlayer(index);
    }

    @Override
    public RespondDialog openRespondDialog(TradeDeal deal) {
        GUIRespondDialog dialog = new GUIRespondDialog();
        dialog.setDeal(deal);
        dialog.setVisible(true);
        return dialog;
    }

    @Override
    public TradeDialog openTradeDialog() {
        GUITradeDialog dialog = new GUITradeDialog(this);
        dialog.setVisible(true);
        return dialog;
    }

    private GUICell queryCell(int index) {
        Cell cell = GameMaster.INSTANCE.getGameBoard().getCell(index);
        for (int x = 0; x < guiCells.size(); x++) {
            GUICell guiCell = guiCells.get(x);
            if (guiCell.getCell() == cell) {
                return guiCell;
            }
        }
        return new NullGUICell(cell);
    }

    @Override
    public void setBuyHouseEnabled(boolean b) {
        int currentPlayerIndex = GameMaster.INSTANCE.getCurrentPlayerIndex();
        playerPanels[currentPlayerIndex].setBuyHouseEnabled(b);
    }

    @Override
    public void setDrawCardEnabled(boolean b) {
        int currentPlayerIndex = GameMaster.INSTANCE.getCurrentPlayerIndex();
        playerPanels[currentPlayerIndex].setDrawCardEnabled(b);
    }

    @Override
    public void setEndTurnEnabled(boolean enabled) {
        int currentPlayerIndex = GameMaster.INSTANCE.getCurrentPlayerIndex();
        playerPanels[currentPlayerIndex].setEndTurnEnabled(enabled);
    }

    @Override
    public void setGetOutOfJailEnabled(boolean b) {
        int currentPlayerIndex = GameMaster.INSTANCE.getCurrentPlayerIndex();
        playerPanels[currentPlayerIndex].setGetOutOfJailEnabled(b);
    }

    @Override
    public void setPurchasePropertyEnabled(boolean enabled) {
        int currentPlayerIndex = GameMaster.INSTANCE.getCurrentPlayerIndex();
        playerPanels[currentPlayerIndex].setPurchasePropertyEnabled(enabled);
    }

    @Override
    public void setRollDiceEnabled(boolean b) {
        int currentPlayerIndex = GameMaster.INSTANCE.getCurrentPlayerIndex();
        playerPanels[currentPlayerIndex].setRollDiceEnabled(b);
    }

    @Override
    public void setTradeEnabled(int index, boolean b) {
        playerPanels[index].setTradeEnabled(b);
    }

    public void setupGameBoard(GameBoard board) {
        Dimension dimension = GameBoardUtil.calculateDimension(board.getCellNumber());
        northPanel.setLayout(new GridLayout(1, dimension.width + 2));
        southPanel.setLayout(new GridLayout(1, dimension.width + 2));
        westPanel.setLayout(new GridLayout(dimension.height, 1));
        eastPanel.setLayout(new GridLayout(dimension.height, 1));
        addCells(northPanel, GameBoardUtil.getNorthCells(board));
        addCells(southPanel, GameBoardUtil.getSouthCells(board));
        addCells(eastPanel, GameBoardUtil.getEastCells(board));
        addCells(westPanel, GameBoardUtil.getWestCells(board));
        buildPlayerPanels();
    }

    @Override
    public void showBuyHouseDialog(Player currentPlayer) {
        BuyHouseDialog dialog = new BuyHouseDialog(currentPlayer);
        dialog.setVisible(true);
    }

    @Override
    public void showMessage(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }

    @Override
    public int showUtilDiceRoll() {
        return UtilDiceRoll.showDialog();
    }

    @Override
    public void startGame() {
        int numberOfPlayers = GameMaster.INSTANCE.getNumberOfPlayers();
        for (int i = 0; i < numberOfPlayers; i++) {
            movePlayer(i, 0, 0);
        }
    }

    @Override
    public void update() {
        for (PlayerPanel playerPanel : playerPanels) {
            playerPanel.displayInfo();
        }
        for (int j = 0; j < guiCells.size(); j++) {
            GUICell cell = guiCells.get(j);
            cell.displayInfo();
        }
    }
}
