package controller;

import gui.PlayerColor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import logic.cell.Cell;
import logic.player.Player;

public class PlayerController {

    private final List<Player> players = new ArrayList<>();

    private final List<PlayerColor> playerColors = new ArrayList<>(Arrays.asList(PlayerColor.GREEN,
            PlayerColor.BLUE, PlayerColor.ORANGE, PlayerColor.RED, PlayerColor.TAN, PlayerColor.PEACH,
            PlayerColor.TEAL, PlayerColor.PINK));

    public int getNumberOfSellers() {
        return players.size() - 1;
    }

    public List<Player> getPlayerList() {
        return players;
    }

    public Player getPlayer(int index) {
        return players.get(index);
    }

    public int getPlayerIndex(Player player) {
        return players.indexOf(player);
    }

    public List<PlayerColor> getPlayerColors() {
        return playerColors;
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

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void clearPlayers() {
        players.clear();
    }

    public void setPlayerColor(PlayerColor color, int index) {
        System.out.println(color.getColor().getAlpha());
        getPlayer(index).setPlayerColor(color.getColor());
        removePlayerColor(color);
    }

    public void movePlayer(int playerIndex, int diceValue) {
        Player player = players.get(playerIndex);
        movePlayer(player, diceValue);
    }

    public void movePlayer(Player player, int diceValue) {
        Cell currentPosition = player.getPosition();
        System.out.println(GameController.INSTANCE.getGameBoardController().getGameBoard().getCell(0));
        int positionIndex = GameController.INSTANCE.getGameBoardController().getGameBoard().queryCellIndex(currentPosition.getName());
        int newIndex = (positionIndex + diceValue) % GameController.INSTANCE.getGameBoardController().getGameBoard().getCellNumber();
        GameController.INSTANCE.checkPlayerPassGoCell(newIndex, positionIndex, diceValue, player);
        player.setPosition(GameController.INSTANCE.getGameBoardController().getGameBoard().getCell(newIndex));
        GameController.INSTANCE.getGUIController().getGUI().movePlayer(getPlayerIndex(player), positionIndex, newIndex);
        GameController.INSTANCE.playerMoved(player);
        GameController.INSTANCE.getGUIController().updateGUI();
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
