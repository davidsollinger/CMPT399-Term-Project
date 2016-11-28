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
    private static GameBoard gameBoard;

    public static void main(String[] args) {
        master = GameMaster.INSTANCE;
        window = new MainWindow();
        gameBoard = new GameBoardView();
        if (args.length > 0) {
            if (args[0].equals("test")) {
                master.setTestMode(true);
            }
            gameBoard = tryToGetArgClass(args);
        }

        master.setGameBoard(gameBoard);
        UserInput input = new UserInput(window);

        if (input.getValid() == false) {
            window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));
        } else {
            beginGame();
        }
    }

    private static void beginGame() {
        window.setupGameBoard(gameBoard);
        window.setVisible(true);
        window.setExtendedState(window.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        master.setGUI(window);
        master.setTestMode(true);
        master.startGame();
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
