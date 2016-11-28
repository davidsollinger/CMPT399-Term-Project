package gui;

import java.awt.HeadlessException;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import termproject.GameBoard;
import termproject.GameBoardView;
import termproject.GameMaster;
import termproject.NullGameBoard;

public class Main {

    private static MainWindow window;
    private static GameMaster master;

    private static String getPlayerName() {
        boolean flag;
        String playerName = null;
        int numPlayers = getNumberOfPlayers();
        for (int i = 0; i < numPlayers; i++) {
            flag = false;
            while (!flag) {
                playerName = JOptionPane.showInputDialog(window, "Please input name for Player " + (i + 1));
                if ((playerName == null)) {
                    i = numPlayers;
                    break;
                } else if (playerName.isEmpty() || playerName.matches(".*\\d+.*")) {
                    JOptionPane.showMessageDialog(window, "Please enter a name");
                } else {
                    master.getPlayer(i).setName(playerName);
                    flag = true;
                }
            }
        }
        return playerName;
    }

    private static int getNumberOfPlayers() {
        int numPlayers = 0;
        while (numPlayers < 2 || numPlayers > master.MAX_PLAYERS) {
            String numberOfPlayers = JOptionPane.showInputDialog(window, "How many players?");
            if (numberOfPlayers == null) {
                break;
            } else {
                numPlayers = tryToGetInt(numPlayers, numberOfPlayers);
            }
            if (numPlayers < 2 || numPlayers > master.MAX_PLAYERS) {
                System.out.println("numPlayers: " + numPlayers);
                JOptionPane.showMessageDialog(window, "Please input a number between two and eight");
            } else {
                master.setNumberOfPlayers(numPlayers);
            }
        }
        return numPlayers;
    }

    private static int tryToGetInt(int numPlayers, String numberOfPlayers) throws HeadlessException {
        try {
            return Integer.parseInt(numberOfPlayers);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(window, "Please input a number");
        }
        return numPlayers;
    }

    public static void main(String[] args) {
        master = GameMaster.INSTANCE;
        window = new MainWindow();
        GameBoard gameBoard = new GameBoardView();
        if (args.length > 0) {
            if (args[0].equals("test")) {
                master.setTestMode(true);
            }
            gameBoard = tryToGetArgClass(args);
        }

        master.setGameBoard(gameBoard);
        String playerName = getPlayerName();
        if (playerName == null) {
            window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));
        } else {
            window.setupGameBoard(gameBoard);
            window.setVisible(true);
            window.setExtendedState(window.getExtendedState() | JFrame.MAXIMIZED_BOTH);
            master.setGUI(window);
            master.setTestMode(true);
            master.startGame();
        }
    }

    private static GameBoard tryToGetArgClass(String[] args) throws HeadlessException {
        GameBoard gameBoard;
        try {
            Class<?> c = Class.forName(args[1]);
            gameBoard = (GameBoard) c.newInstance();
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(window, "Class Not Found.  Program will exit");
            System.exit(0);
        } catch (IllegalAccessException e) {
            JOptionPane.showMessageDialog(window, "Illegal Access of Class.  Program will exit");
            System.exit(0);
        } catch (InstantiationException e) {
            JOptionPane.showMessageDialog(window, "Class Cannot be Instantiated.  Program will exit");
            System.exit(0);
        }
        return new NullGameBoard();
    }
}
