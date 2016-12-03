package gui;

import java.awt.HeadlessException;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import logic.GameController;
import logic.gameBoard.GameBoard;
import logic.gameBoard.GameBoardView;
import logic.gameBoard.NullGameBoard;

public class Main {

    private static MainWindow window;
    private static GameController gameController;
    private static GameBoard gameBoard;

    public static void main(String[] args) {
        gameController = GameController.INSTANCE;
        window = new MainWindow();
        gameBoard = new GameBoardView();

        checkArgs(args);
        gameController.getGameBoardController().setGameBoard(gameBoard);
        UserInput input = new UserInput(window);

        checkUserInput(input);
    }

    private static void checkUserInput(UserInput input) {
        if (!input.isValid()) {
            window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));
        } else {
            beginGame();
        }
    }

    private static void checkArgs(String[] args) throws HeadlessException {
        if (args.length > 0) {
            checkTestMode(args);
            gameBoard = tryToGetArgClass(args);
        }
    }

    private static void checkTestMode(String[] args) {
        if (args[0].equalsIgnoreCase("test")) {
            gameController.setTestMode(true);
        }
    }

    private static void beginGame() {
        window.setupGameBoard(gameBoard);
        window.setVisible(true);
        window.setExtendedState(window.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        gameController.getGUIController().setGUI(window);
        gameController.setTestMode(true);
        gameController.startGame();
    }

    private static GameBoard tryToGetArgClass(String[] args) throws HeadlessException {
        try {
            Class<?> c = Class.forName(args[1]);
            return (GameBoard) c.newInstance();
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
