package gui;

import controller.GameController;
import debugging.DiceRollDialog;
import gui.trade.GUIRespondDialog;
import gui.trade.GUITradeDialog;
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
import logic.trade.RespondDialog;
import logic.cell.Cell;
import logic.gameBoard.GameBoard;
import logic.player.Player;
import logic.trade.TradeDeal;
import logic.trade.TradeDialog;

public class MainWindow extends JFrame implements MonopolyGUI {

    private static final long serialVersionUID = 1L;

    private final int GAMEBOARD_ROWS = 1;
    private final int GAMEBOARD_COLS = 1;
    private final int PLAYER_PANEL_ROWS = 2;
    private final JPanel eastPanel = new JPanel();
    private final JPanel northPanel = new JPanel();
    private final JPanel southPanel = new JPanel();
    private final JPanel westPanel = new JPanel();
    private final GameController gameController;
    private final List<GUICell> guiCells = new ArrayList<>();
    
    private PlayerPanel[] playerPanels;

    public MainWindow() {
        gameController = GameController.INSTANCE;
        northPanel.setBorder(new LineBorder(Color.BLACK));
        southPanel.setBorder(new LineBorder(Color.BLACK));
        westPanel.setBorder(new LineBorder(Color.BLACK));
        eastPanel.setBorder(new LineBorder(Color.BLACK));

        Container container = super.getContentPane();
        Toolkit toolKit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolKit.getScreenSize();

        super.setSize(dimension);
        container.add(northPanel, BorderLayout.NORTH);
        container.add(southPanel, BorderLayout.SOUTH);
        container.add(eastPanel, BorderLayout.EAST);
        container.add(westPanel, BorderLayout.WEST);

        super.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
    
    public void setUpGameBoard(GameBoard board) {
        Dimension dimension = GameBoardUtil.calculateDimension(board.getCellNumber());
        northPanel.setLayout(new GridLayout(GAMEBOARD_ROWS, dimension.width + 2));
        southPanel.setLayout(new GridLayout(GAMEBOARD_ROWS, dimension.width + 2));
        westPanel.setLayout(new GridLayout(dimension.height, GAMEBOARD_COLS));
        eastPanel.setLayout(new GridLayout(dimension.height, GAMEBOARD_COLS));
        addCells(northPanel, GameBoardUtil.getNorthCells(board));
        addCells(southPanel, GameBoardUtil.getSouthCells(board));
        addCells(eastPanel, GameBoardUtil.getEastCells(board));
        addCells(westPanel, GameBoardUtil.getWestCells(board));
        buildPlayerPanels();
    }
    
    private GUICell queryCell(int index) {
        Cell cell = gameController.getGameBoardController().getGameBoard().getCell(index);
        for (int i = 0; i < guiCells.size(); i++) {
            GUICell guiCell = guiCells.get(i);
            if (guiCell.getCell() == cell) {
                return guiCell;
            }
        }
        return new NullGUICell(cell);
    }

    private void addCells(JPanel panel, List<Cell> cells) {
        for (int x = 0; x < cells.size(); x++) {
            GUICell cell = new GUICell(cells.get(x));
            panel.add(cell);
            guiCells.add(cell);
        }
    }

    private void buildPlayerPanels() {
        JPanel infoPanel = new JPanel();
        int players = gameController.getNumberOfPlayers();
        infoPanel.setLayout(new GridLayout(PLAYER_PANEL_ROWS, (players + 1) / 2));
        getContentPane().add(infoPanel, BorderLayout.CENTER);
        playerPanels = new PlayerPanel[gameController.getNumberOfPlayers()];
        for (int i = 0; i < gameController.getNumberOfPlayers(); i++) {
            playerPanels[i] = new PlayerPanel(gameController.getPlayerController().getPlayer(i));
            infoPanel.add(playerPanels[i]);
            playerPanels[i].displayInfo();
        }
    }
    
    @Override
    public int[] getDiceRoll() {
        DiceRollDialog dialog = new DiceRollDialog(this);
        dialog.setVisible(true);
        return dialog.getDiceRoll();
    }
    
    @Override
    public void setEndTurnEnabled(boolean enabled) {
        int currentPlayerIndex = gameController.getTurn();
        playerPanels[currentPlayerIndex].setEndTurnEnabled(enabled);
    }

    @Override
    public void setGetOutOfJailEnabled(boolean enabled) {
        int currentPlayerIndex = gameController.getTurn();
        playerPanels[currentPlayerIndex].setGetOutOfJailEnabled(enabled);
    }

    @Override
    public void setPurchasePropertyEnabled(boolean enabled) {
        int currentPlayerIndex = gameController.getTurn();
        playerPanels[currentPlayerIndex].setPurchasePropertyEnabled(enabled);
    }

    @Override
    public void setRollDiceEnabled(boolean enabled) {
        int currentPlayerIndex = gameController.getTurn();
        playerPanels[currentPlayerIndex].setRollDiceEnabled(enabled);
    }

    @Override
    public void setTradeEnabled(int index, boolean enabled) {
        playerPanels[index].setTradeEnabled(enabled);
    }
    
    @Override
    public void setBuyHouseEnabled(boolean enabled) {
        int currentPlayerIndex = gameController.getTurn();
        playerPanels[currentPlayerIndex].setBuyHouseEnabled(enabled);
    }

    @Override
    public void setDrawCardEnabled(boolean enabled) {
        int currentPlayerIndex = gameController.getTurn();
        playerPanels[currentPlayerIndex].setDrawCardEnabled(enabled);
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
    
    @Override
    public int showUtilDiceRoll() {
        return UtilDiceRoll.showDialog();
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
    public void movePlayer(int index, int from, int to) {
        GUICell fromCell = queryCell(from);
        GUICell toCell = queryCell(to);
        fromCell.removePlayer(index);
        toCell.setPlayerLabelsOpaque(index);
    }

    @Override
    public void startGame() {
        int numberOfPlayers = gameController.getNumberOfPlayers();
        for (int i = 0; i < numberOfPlayers; i++) {
            movePlayer(i, 0, 0);
        }
    }

    @Override
    public void update() {
        for (PlayerPanel playerPanel : playerPanels) {
            playerPanel.displayInfo();
        }
        for (int i = 0; i < guiCells.size(); i++) {
            GUICell cell = guiCells.get(i);
            cell.displayInfo();
        }
    }
    
    @Override
    public boolean isDrawCardButtonEnabled() {
        int currentPlayerIndex = gameController.getTurn();
        return playerPanels[currentPlayerIndex].isDrawCardButtonEnabled();
    }

    @Override
    public boolean isEndTurnButtonEnabled() {
        int currentPlayerIndex = gameController.getTurn();
        return playerPanels[currentPlayerIndex].isEndTurnButtonEnabled();
    }

    @Override
    public boolean isGetOutOfJailButtonEnabled() {
        int currentPlayerIndex = gameController.getTurn();
        return playerPanels[currentPlayerIndex].isGetOutOfJailButtonEnabled();
    }

    @Override
    public boolean isTradeButtonEnabled(int i) {
        return playerPanels[i].isTradeButtonEnabled();
    }
}
