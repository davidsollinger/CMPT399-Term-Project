package controller;

import gui.PlayerColor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import logic.player.Player;

public class PlayerController {

    private final List<Player> players = new ArrayList<>();

    private final List<PlayerColor> playerColors = new ArrayList<>(
            Arrays.asList(PlayerColor.GREEN, PlayerColor.BLUE,
                PlayerColor.ORANGE, PlayerColor.RED, PlayerColor.TAN,
                PlayerColor.PEACH, PlayerColor.TEAL, PlayerColor.PINK));

    public List<Player> getPlayerList() {
        return Collections.unmodifiableList(players);
    }
    
    public int getNumberOfSellers() {
        return players.size() - 1;
    }
    
    public int getPlayerIndex(Player player) {
        return players.indexOf(player);
    }

    public Player getPlayer(int index) {
        return players.get(index);
    }

    public List<PlayerColor> getPlayerColors() {
        return Collections.unmodifiableList(playerColors);
    }

    public PlayerColor getPlayerColor(int index) {
        return playerColors.get(index);
    }

    public List<Player> getSellerList() {
        List<Player> sellers = new ArrayList<>();
        players.stream().filter((player)
                -> (player != GameController.INSTANCE.getCurrentPlayer())).forEach((player) -> {
            sellers.add(player);
        });
        return sellers;
    }
    
    public void setPlayerColor(PlayerColor color, int index) {
        getPlayer(index).setPlayerColor(color.getColor());
        removePlayerColor(color);
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void clearPlayers() {
        players.clear();
    }
    
    public void movePlayer(int playerIndex, int diceValue) {
        Player player = players.get(playerIndex);
        GameController.INSTANCE.movePlayer(player, diceValue);
    }
    
    public void sendToJail(Player player) {
        int oldPosition = GameController.INSTANCE.getGameBoardController().getGameBoard().queryCellIndex(GameController.INSTANCE.getCurrentPlayer().getPosition().getName());
        player.setPosition(GameController.INSTANCE.getGameBoardController().getGameBoard().queryCell("Jail"));
        player.setInJail(true);
        int jailIndex = GameController.INSTANCE.getGameBoardController().getGameBoard().queryCellIndex("Jail");
        GameController.INSTANCE.getGUIController().getGUI().movePlayer(getPlayerIndex(player), oldPosition, jailIndex);
    }

    public void removePlayerColor(PlayerColor color) {
        playerColors.remove(color);
    }

    public void removePlayer(Player player) {
        players.remove(player);
    }
}
