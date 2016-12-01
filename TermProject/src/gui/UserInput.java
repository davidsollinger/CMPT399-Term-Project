package gui;

import java.awt.HeadlessException;
import javax.swing.JOptionPane;
import logic.GameMaster;

public class UserInput {

    private final MainWindow window;
    
    private boolean valid;
    private int numberOfPlayers;

    public UserInput(MainWindow window) {
        this.window = window;
        valid = false;
        setNumberOfPlayers();
        if (0 != numberOfPlayers) {
            setPlayersNames();
        }
    }

    public boolean isValid() {
        return valid;
    }

    private void setNumberOfPlayers() {
        numberOfPlayers = getNumberOfPlayersInput();
        GameMaster.INSTANCE.setNumberOfPlayers(numberOfPlayers);
    }

    private int getNumberOfPlayersInput() {
        int numOfPlayers = 0;
        while (!isValidNumberOfPlayers(numOfPlayers)) {
            String numberOfPlayersInput = JOptionPane.showInputDialog(window, "How many players?");
            if (isCanceledClicked(numberOfPlayersInput)) {
                return 0;
            }
            numOfPlayers = tryToGetInt(numberOfPlayersInput);
            if ((numOfPlayers != -1) && !isValidNumberOfPlayers(numOfPlayers)) {
                JOptionPane.showMessageDialog(window, "Please input a number between two and eight", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        return numOfPlayers;
    }

    private void setPlayersNames() throws HeadlessException {
        String playerName;
        for (int i = 0; i < numberOfPlayers; i++) {
            playerName = "";
            while (!isValidPlayerName(playerName)) {
                playerName = JOptionPane.showInputDialog(window, "Please input name for Player " + (i + 1));
                if (isCanceledClicked(playerName)) {
                    return;
                }
                if (isValidPlayerName(playerName)) {
                    GameMaster.INSTANCE.getPlayer(i).setName(playerName);
                } else {
                    JOptionPane.showMessageDialog(window, "Please enter a string name", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        valid = true;
    }

    private boolean isCanceledClicked(String input) {
        return null == input;
    }

    private boolean isValidPlayerName(String playerName) {
        return (!playerName.isEmpty()) && (!playerName.matches(".*\\d+.*"));
    }

    private boolean isValidNumberOfPlayers(int numPlayers) {
        return (numPlayers >= 2) && (numPlayers <= GameMaster.MAX_PLAYERS);
    }

    private int tryToGetInt(String numberOfPlayers) throws HeadlessException {
        try {
            return Integer.parseUnsignedInt(numberOfPlayers);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(window, "Please input a number between two and eight", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return -1;
    }

}
