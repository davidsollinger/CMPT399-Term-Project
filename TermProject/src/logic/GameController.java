package logic;

import logic.card.Card;
import logic.card.CardType;
import logic.cell.CardCell;
import logic.cell.Cell;
import logic.player.Player;
import logic.trade.TradeDeal;

public enum GameController {
    INSTANCE;

    public static final int MAX_PLAYERS = 8;

    private int turn = 0;
    private int utilDiceRoll;

    private boolean testMode;

    private final int GO_CELL_AMOUNT = 200;
    private final int INIT_AMOUNT_OF_MONEY = 1500;

    private final PlayerController playerController;
    private final GUIController guiController;
    private final GameBoardController gameBoardController;

    private GameController() {
        playerController = new PlayerController();
        guiController = new GUIController();
        gameBoardController = new GameBoardController();
    }

    public PlayerController getPlayerController() {
        return playerController;
    }

    public GUIController getGUIController() {
        return guiController;
    }

    public GameBoardController getGameBoardController() {
        return gameBoardController;
    }

    public Player getCurrentPlayer() {
        return playerController.getPlayer(turn);
    }

    public int getInitAmountOfMoney() {
        return INIT_AMOUNT_OF_MONEY;
    }

    public int getNumberOfPlayers() {
        return playerController.getPlayerList().size();
    }

    public int getUtilDiceRoll() {
        return utilDiceRoll;
    }

    public Card getCardAction(CardCell cell) {
        Card card;
        if (isCellTypeCommunity(cell)) {
            card = gameBoardController.getGameBoard().drawCommunityChestCard();
        } else {
            card = gameBoardController.getGameBoard().drawChanceCard();
        }
        card.applyAction();
        return card;
    }

    private static boolean isCellTypeCommunity(CardCell cell) {
        return cell.getType().equals(CardType.COMMUNITY);
    }

    public int getTurn() {
        return turn;
    }

    public void setNumberOfPlayers(int number) {
        playerController.clearPlayers();
        for (int i = 0; i < number; i++) {
            Player player = new Player();
            player.setMoney(INIT_AMOUNT_OF_MONEY);
            playerController.addPlayer(player);
        }
    }

    public void setUtilDiceRoll(int diceRoll) {
        utilDiceRoll = diceRoll;
    }

    public void setTestMode(boolean enabled) {
        testMode = enabled;
    }

    public boolean isTestModeEnabled() {
        return testMode;
    }

    public void completeTrade(TradeDeal deal) {
        Player seller = playerController.getPlayer(deal.getPlayerIndex());
        Cell property = gameBoardController.getGameBoard().queryCell(deal.getPropertyName());
        seller.getActions().sellProperty(property, deal.getAmount());
        getCurrentPlayer().getActions().buyProperty(property, deal.getAmount());
    }

    public Card drawCCCard() {
        return gameBoardController.getGameBoard().drawCommunityChestCard();
    }

    public Card drawChanceCard() {
        return gameBoardController.getGameBoard().drawChanceCard();
    }

    public void playerMoved(Player player) {
        Cell cell = player.getPosition();
        int playerIndex = playerController.getPlayerIndex(player);
        if (cell instanceof CardCell) {
            guiController.getGUI().setDrawCardEnabled(true);
        } else {
            checkCellIsAvailible(cell, player, playerIndex);
            guiController.getGUI().enableEndTurnBtn(playerIndex);
        }
        guiController.getGUI().setTradeEnabled(turn, false);
    }

    private void checkCellIsAvailible(Cell cell, Player player, int playerIndex) {
        if (cell.isAvailable()) {
            int price = cell.getPrice();
            checkAvailibleForPurchase(price, player, playerIndex);
        }
    }

    private void checkAvailibleForPurchase(int price, Player player, int playerIndex) {
        if (hasEnoughMoney(price, player.getMoney()) && price > 0) {
            guiController.getGUI().enablePurchaseBtn(playerIndex);
        }
    }

    public void checkPlayerPassGoCell(int newIndex, int positionIndex, int diceValue, Player player) {
        if (hasEnoughMoney(newIndex, positionIndex) || diceValue > gameBoardController.getGameBoard().getCellNumber()) {
            player.addMoney(GO_CELL_AMOUNT);
        }
    }

    private boolean hasEnoughMoney(int newIndex, int positionIndex) {
        return newIndex <= positionIndex;
    }

    public void reset() {
        for (int i = 0; i < getNumberOfPlayers(); i++) {
            Player player = playerController.getPlayerList().get(i);
            player.setPosition(gameBoardController.getGameBoard().getCell(0));
        }
        gameBoardController.getGameBoard().removeCards();
        turn = 0;
    }

    public void startGame() {
        guiController.getGUI().startGame();
        guiController.getGUI().enablePlayerTurn(0);
    }

    public void switchTurn() {
        turn = (turn + 1) % getNumberOfPlayers();
        if (!getCurrentPlayer().isInJail()) {
            guiController.getGUI().enablePlayerTurn(turn);
            guiController.getGUI().setBuyHouseEnabled(getCurrentPlayer().canBuyHouse());
            guiController.getGUI().setTradeEnabled(turn, true);
        } else {
            guiController.getGUI().setGetOutOfJailEnabled(true);
        }
    }

    public void utilRollDice() {
        utilDiceRoll = guiController.getGUI().showUtilDiceRoll();
    }
}
