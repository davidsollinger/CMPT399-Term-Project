package gui;

import java.awt.HeadlessException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import termproject.GameBoard;
import termproject.GameBoardFull;
import termproject.GameMaster;
import termproject.NullGameBoard;

public class Main {
    
    private static int getNumberOfPlayers(MainWindow window) {
        int numPlayers = 0;
        while (numPlayers < 2 || numPlayers > GameMaster.MAX_PLAYER) {
            String numberOfPlayers = JOptionPane.showInputDialog(window, "How many players");
            numPlayers = tryToGetInt(numPlayers, numberOfPlayers, window);
            if (numPlayers < 2 || numPlayers > GameMaster.MAX_PLAYER) {
                JOptionPane.showMessageDialog(window, "Please input a number between two and eight");
            } else {
                GameMaster.INSTANCE.setNumberOfPlayers(numPlayers);
            }
        }
        return numPlayers;
    }

    private static int tryToGetInt(int numPlayers, String numberOfPlayers, MainWindow window) throws HeadlessException {
        try {
            return Integer.parseInt(numberOfPlayers);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(window, "Please input a number");
        }
        return numPlayers;
    }

    public static void main(String[] args) {
        GameMaster master = GameMaster.INSTANCE;
        MainWindow window = new MainWindow();
        GameBoard gameBoard = new GameBoardFull();
        if (args.length > 0) {
            if (args[0].equals("test")) {
                master.setTestMode(true);
            }
            gameBoard = tryToGetArgClass(args, window);
        }

        master.setGameBoard(gameBoard);
        int numPlayers = getNumberOfPlayers(window);
        for (int i = 0; i < numPlayers; i++) {
            String name
                    = JOptionPane.showInputDialog(window, "Please input name for Player " + (i + 1));
            GameMaster.INSTANCE.getPlayer(i).setName(name);
        }
        window.setupGameBoard(gameBoard);
        window.setVisible(true);
        window.setExtendedState(window.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        master.setGUI(window);
        master.setTestMode(true);
        master.startGame();
    }

    private static GameBoard tryToGetArgClass(String[] args, MainWindow window) throws HeadlessException {
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
