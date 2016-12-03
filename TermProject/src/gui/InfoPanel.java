package gui;

import java.awt.GridLayout;
import javax.swing.JPanel;
import logic.GameController;

public class InfoPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private final int ROWS = 1;

    public void displayInfo() {
        GameController gameController = GameController.INSTANCE;
        setLayout(new GridLayout(ROWS, gameController.getNumberOfPlayers()));
        for (int i = 0; i < gameController.getNumberOfPlayers(); i++) {
            PlayerPanel panel = new PlayerPanel(gameController.getPlayerController().getPlayer(i));
            add(panel);
            panel.displayInfo();
        }
    }
}
