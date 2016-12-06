package gui;

import controller.GameController;
import java.awt.HeadlessException;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public class UserInput {

    private final MainWindow window;
    private final GameController gameController;
    
    private boolean valid;
    private int numberOfPlayers;

    public UserInput(MainWindow window) {
        this.window = window;
        gameController = GameController.INSTANCE;
        valid = false;
        setNumberOfPlayers();
        if (0 != numberOfPlayers) {
            setPlayersNames();
        }
    }

    public boolean isValid() {
        return valid;
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

    private void setNumberOfPlayers() {
        numberOfPlayers = getNumberOfPlayersInput();
        gameController.setNumberOfPlayers(numberOfPlayers);
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
                    gameController.getPlayerController().getPlayer(i).setName(playerName);
                    setPlayerColor(i);
                } else {
                    JOptionPane.showMessageDialog(window, "Please enter a string name", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        valid = true;
    }

    private void setPlayerColor(int index) {
        JComboBox colorComboBox = new JComboBox(gameController.getPlayerController().getPlayerColors().toArray());
        colorComboBox.setRenderer(new CellRenderer());
        JOptionPane.showMessageDialog(window, colorComboBox, "Select a player color", JOptionPane.QUESTION_MESSAGE);
        gameController.getPlayerController().setPlayerColor((PlayerColor) colorComboBox.getSelectedItem(), index);
    }

    private int tryToGetInt(String numberOfPlayers) throws HeadlessException {
        try {
            return Integer.parseUnsignedInt(numberOfPlayers);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(window, "Please input a number between two and eight", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return -1;
    }
    
    private boolean isCanceledClicked(String input) {
        return null == input;
    }

    private boolean isValidPlayerName(String playerName) {
        return (!playerName.isEmpty()) && (!playerName.matches(".*\\d+.*"));
    }

    private boolean isValidNumberOfPlayers(int numPlayers) {
        return (numPlayers >= 2) && (numPlayers <= GameController.MAX_PLAYERS);
    }

}
