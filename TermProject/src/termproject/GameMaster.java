package termproject;

import java.util.ArrayList;
import java.util.List;

public enum GameMaster {
    INSTANCE;

    public static final int MAX_PLAYER = 8;

    private final int INIT_AMOUNT_OF_MONEY = 1500;

    private final Die[] dice;
    private GameBoard gameBoard;
    private MonopolyGUI gui;
    private final List<Player> players = new ArrayList<>();
    private int turn = 0;
    private int utilDiceRoll;
    private boolean testMode;

    private GameMaster() {
        dice = new Die[]{new Die(), new Die()};
    }

    public void btnBuyHouseClicked() {
        gui.showBuyHouseDialog(getCurrentPlayer());
    }

    public Card btnDrawCardClicked() {
        gui.setDrawCardEnabled(false);
        CardCell cell = (CardCell) getCurrentPlayer().getPosition();
        Card card;
        if (cell.getType() == Card.TYPE_CC) {
            card = getGameBoard().drawCCCard();
            card.applyAction();
        } else {
            card = getGameBoard().drawChanceCard();
            card.applyAction();
        }
        gui.setEndTurnEnabled(true);
        return card;
    }

    public void btnEndTurnClicked() {
        setAllButtonEnabled(false);
        getCurrentPlayer().getPosition().playAction();
        if (getCurrentPlayer().isBankrupt()) {
            setButtonPropertiesFalse();
            updateGUI();
        } else {
            switchTurn();
            updateGUI();
        }
    }

    private void setButtonPropertiesFalse() {
        gui.setBuyHouseEnabled(false);
        gui.setDrawCardEnabled(false);
        gui.setEndTurnEnabled(false);
        gui.setGetOutOfJailEnabled(false);
        gui.setPurchasePropertyEnabled(false);
        gui.setRollDiceEnabled(false);
        gui.setTradeEnabled(getCurrentPlayerIndex(), false);
    }

    public void btnGetOutOfJailClicked() {
        getCurrentPlayer().getOutOfJail();
        if (getCurrentPlayer().isBankrupt()) {
            setButtonPropertiesFalse();
        } else {
            playerIsOutOfJail();
        }
    }

    private void playerIsOutOfJail() {
        gui.setRollDiceEnabled(true);
        gui.setBuyHouseEnabled(getCurrentPlayer().canBuyHouse());
        gui.setGetOutOfJailEnabled(getCurrentPlayer().isInJail());
    }

    public void btnPurchasePropertyClicked() {
        Player player = getCurrentPlayer();
        player.purchase();
        gui.setPurchasePropertyEnabled(false);
        updateGUI();
    }

    public void btnRollDiceClicked() {
        int[] rolls = rollDice();
        if ((rolls[0] + rolls[1]) > 0) {
            Player player = getCurrentPlayer();
            gui.setRollDiceEnabled(false);
            StringBuilder msg = new StringBuilder();
            msg.append(player.getName())
                    .append(", you rolled ")
                    .append(rolls[0])
                    .append(" and ")
                    .append(rolls[1]);
            gui.showMessage(msg.toString());
            movePlayer(player, rolls[0] + rolls[1]);
            gui.setBuyHouseEnabled(false);
        }
    }

    public void btnTradeClicked() {
        TradeDialog dialog = gui.openTradeDialog();
        TradeDeal deal = dialog.getTradeDeal();
        if (!deal.isNull()) {
            RespondDialog rDialog = gui.openRespondDialog(deal);
            if (rDialog.getResponse()) {
                completeTrade(deal);
                updateGUI();
            }
        }
    }

    public void completeTrade(TradeDeal deal) {
        Player seller = getPlayer(deal.getPlayerIndex());
        Cell property = gameBoard.queryCell(deal.getPropertyName());
        seller.sellProperty(property, deal.getAmount());
        getCurrentPlayer().buyProperty(property, deal.getAmount());
    }

    public Card drawCCCard() {
        return gameBoard.drawCCCard();
    }

    public Card drawChanceCard() {
        return gameBoard.drawChanceCard();
    }

    public Player getCurrentPlayer() {
        return getPlayer(turn);
    }

    public int getCurrentPlayerIndex() {
        return turn;
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public MonopolyGUI getGUI() {
        return gui;
    }

    public int getInitAmountOfMoney() {
        return INIT_AMOUNT_OF_MONEY;
    }

    public int getNumberOfPlayers() {
        return players.size();
    }

    public int getNumberOfSellers() {
        return players.size() - 1;
    }

    public Player getPlayer(int index) {
        return players.get(index);
    }

    public int getPlayerIndex(Player player) {
        return players.indexOf(player);
    }

    public List<Player> getSellerList() {
        List<Player> sellers = new ArrayList<>();
        players.stream().filter((player) -> (player != getCurrentPlayer())).forEach((player) -> {
            sellers.add(player);
        });
        return sellers;
    }

    public int getTurn() {
        return turn;
    }

    public int getUtilDiceRoll() {
        return utilDiceRoll;
    }

    public void movePlayer(int playerIndex, int diceValue) {
        Player player = players.get(playerIndex);
        movePlayer(player, diceValue);
    }

    public void movePlayer(Player player, int diceValue) {
        Cell currentPosition = player.getPosition();
        int positionIndex = gameBoard.queryCellIndex(currentPosition.getName());
        int newIndex = (positionIndex + diceValue) % gameBoard.getCellNumber();
        if (newIndex <= positionIndex || diceValue > gameBoard.getCellNumber()) {
            player.setMoney(player.getMoney() + 200);
        }
        player.setPosition(gameBoard.getCell(newIndex));
        gui.movePlayer(getPlayerIndex(player), positionIndex, newIndex);
        playerMoved(player);
        updateGUI();
    }

    public void playerMoved(Player player) {
        Cell cell = player.getPosition();
        int playerIndex = getPlayerIndex(player);
        if (cell instanceof CardCell) {
            gui.setDrawCardEnabled(true);
        } else {
            if (cell.isAvailable()) {
                int price = cell.getPrice();
                if (price <= player.getMoney() && price > 0) {
                    gui.enablePurchaseBtn(playerIndex);
                }
            }
            gui.enableEndTurnBtn(playerIndex);
        }
        gui.setTradeEnabled(turn, false);
    }

    public void reset() {
        for (int i = 0; i < getNumberOfPlayers(); i++) {
            Player player = players.get(i);
            player.setPosition(gameBoard.getCell(0));
        }
        if (!gameBoard.isNull()) {
            gameBoard.removeCards();
        }
        turn = 0;
    }

    public int[] rollDice() {
        if (testMode) {
            return gui.getDiceRoll();
        }
        return new int[]{dice[0].getRoll(), dice[1].getRoll()};
    }

    public void sendToJail(Player player) {
        int oldPosition = gameBoard.queryCellIndex(getCurrentPlayer().getPosition().getName());
        player.setPosition(gameBoard.queryCell("Jail"));
        player.setInJail(true);
        int jailIndex = gameBoard.queryCellIndex("Jail");
        gui.movePlayer(getPlayerIndex(player), oldPosition, jailIndex);
    }

    private void setAllButtonEnabled(boolean enabled) {
        gui.setRollDiceEnabled(enabled);
        gui.setPurchasePropertyEnabled(enabled);
        gui.setEndTurnEnabled(enabled);
        gui.setTradeEnabled(turn, enabled);
        gui.setBuyHouseEnabled(enabled);
        gui.setDrawCardEnabled(enabled);
        gui.setGetOutOfJailEnabled(enabled);
    }

    public void setGameBoard(GameBoard board) {
        gameBoard = board;
    }

    public void setGUI(MonopolyGUI gui) {
        this.gui = gui;
    }

    public void setNumberOfPlayers(int number) {
        players.clear();
        for (int i = 0; i < number; i++) {
            Player player = new Player();
            player.setMoney(INIT_AMOUNT_OF_MONEY);
            players.add(player);
        }
    }

    public void setUtilDiceRoll(int diceRoll) {
        utilDiceRoll = diceRoll;
    }

    public void startGame() {
        gui.startGame();
        gui.enablePlayerTurn(0);
        gui.setTradeEnabled(0, true);
    }

    public void switchTurn() {
        turn = (turn + 1) % getNumberOfPlayers();
        if (!getCurrentPlayer().isInJail()) {
            gui.enablePlayerTurn(turn);
            gui.setBuyHouseEnabled(getCurrentPlayer().canBuyHouse());
            gui.setTradeEnabled(turn, true);
        } else {
            gui.setGetOutOfJailEnabled(true);
        }
    }

    public void updateGUI() {
        gui.update();
    }

    public void utilRollDice() {
        utilDiceRoll = gui.showUtilDiceRoll();
    }

    public void setTestMode(boolean b) {
        testMode = b;
    }
}
