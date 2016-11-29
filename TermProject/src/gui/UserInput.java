package gui;

import java.awt.HeadlessException;
import javax.swing.JOptionPane;
import logic.GameMaster;

public class UserInput {

    private final MainWindow window;
    private boolean valid;

    public UserInput(MainWindow window) {
        this.window = window;
        valid = false;
        getPlayerInput();
    }

    public boolean isValid() {
        return valid;
    }

    private void getPlayerInput() {
        int numPlayers = getNumberOfPlayers();
        for (int i = 0; i < numPlayers; i++) {
            while (true) {
                String playerName = JOptionPane.showInputDialog(window, "Please input name for Player " + (i + 1));
                if (isCanceledClicked(playerName)) {
                    i = numPlayers; // Need explain
                    break;
                }

                if (checkPlayerName(playerName)) {
                    GameMaster.INSTANCE.getPlayer(i).setName(playerName);
                    break;
                }
            }
            if (i == numPlayers - 1) { // Need explain
                valid = true;
            }
        }

    }

    private int getNumberOfPlayers() {
        int numPlayers;
        while (true) {
            numPlayers = 0;
            String numberOfPlayers = JOptionPane.showInputDialog(window, "How many players?");
            if (isCanceledClicked(numberOfPlayers)) {
                return 0;
            }

            numPlayers = tryToGetInt(numPlayers, numberOfPlayers);
            if (checkNumberOfPlayers(numPlayers)) {
                GameMaster.INSTANCE.setNumberOfPlayers(numPlayers);
                return numPlayers;
            }
        }
    }

    private boolean isCanceledClicked(String input) {
        return input == null;
    }

    private boolean checkPlayerName(String playerName) {
        if (playerName.isEmpty() || playerName.matches(".*\\d+.*")) {
            JOptionPane.showMessageDialog(window,
                    "Please enter a string name", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    private boolean checkNumberOfPlayers(int numPlayers) {
        if (numPlayers < 2 || numPlayers > GameMaster.MAX_PLAYERS) {
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
