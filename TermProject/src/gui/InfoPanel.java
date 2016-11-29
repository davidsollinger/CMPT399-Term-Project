package gui;

import java.awt.GridLayout;
import javax.swing.JPanel;
import logic.GameMaster;

public class InfoPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    
    private final int ROWS = 1;

    public void displayInfo() {
        GameMaster master = GameMaster.INSTANCE;
        setLayout(new GridLayout(ROWS, master.getNumberOfPlayers()));
        for (int i = 0; i < master.getNumberOfPlayers(); i++) {
            PlayerPanel panel = new PlayerPanel(master.getPlayer(i));
            add(panel);
            panel.displayInfo();
        }
    }
}
