package gui;

import java.awt.HeadlessException;
import javax.swing.JOptionPane;
import logic.GameMaster;

public class UserInput {

    private MainWindow window;
    private boolean valid;

    public UserInput(MainWindow window) {
        this.window = window;
        valid = false;
        getPlayerInput();
    }

    public boolean getValid() {
        return valid;
    }

    private void getPlayerInput() {
        int numPlayers = getNumberOfPlayers();
        for (int i = 0; i < numPlayers; i++) {
            while (true) {
                String playerName = JOptionPane.showInputDialog(window, "Please input name for Player " + (i + 1));
                if ((checkForNoInput(playerName))) {
                    i = numPlayers;
                    break;
                }

                if (checkPlayerName(playerName)) {
                    GameMaster.INSTANCE.getPlayer(i).setName(playerName);
                    break;
                }
            }
            if (i == numPlayers - 1) {
                valid = true;
            }
        }

    }

    private int getNumberOfPlayers() {
        int numPlayers;
        while (true) {
            numPlayers = 0;
            String numberOfPlayers = JOptionPane.showInputDialog(window, "How many players?");
            if (checkForNoInput(numberOfPlayers)) {
                break;
            }

            numPlayers = tryToGetInt(numPlayers, numberOfPlayers);
            if (checkNumberOfPlayer(numPlayers)) {
                GameMaster.INSTANCE.setNumberOfPlayers(numPlayers);
                break;
            }
        }
        return numPlayers;
    }

    private boolean checkForNoInput(String input) {
        if (input == null) {
            return true;
        }

        return false;
    }

    private boolean checkPlayerName(String playerName) {
        if (playerName.isEmpty() || playerName.matches(".*\\d+.*")) {
            JOptionPane.showMessageDialog(window,
                    "Please enter a string name", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    private boolean checkNumberOfPlayer(int numPlayers) {
        if (numPlayers < 2 || numPlayers > GameMaster.INSTANCE.MAX_PLAYERS) {
            JOptionPane.showMessageDialog(window,
                    "Please input a number between two and eight", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    private int tryToGetInt(int numPlayers, String numberOfPlayers) throws HeadlessException {
        try {
            return Integer.parseInt(numberOfPlayers);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(window,
                    "Please input a number", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return numPlayers;
    }

}
