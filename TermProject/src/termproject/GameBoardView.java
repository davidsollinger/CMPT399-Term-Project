package termproject;

public class GameBoardView extends GameBoard {

    private final Cell[] cellArray = new Cell[39];

    private JailCell jailCell;
    private FreeParkingCell freeParkingCell;
    private GoToJailCell goToJailCell;

    public GameBoardView() {
        super();
        createPropertyCells();
        createRRCells();
        createUtilityCells();
        createJailCell();
        createFreeParkingCell();
        createGoToJailCell();
        createCardCells();

        addJailCell();
        addFreeParkingCell();
        addGoToJailCell();
        addCards();
        addCells();
    }

    private void createPropertyCells() {
        PropertyCell dp1 = new PropertyCell("Mediterranean Avenue", 50, "purple", 2, 60);
        PropertyCell dp2 = new PropertyCell("Baltic Avenue", 50, "purple", 4, 60);
        PropertyCell dp3 = new PropertyCell("Sarah Avenue", 50, "purple", 4, 60);

        cellArray[0] = dp1;
        cellArray[2] = dp2;
        cellArray[3] = dp3;

        PropertyCell lb1 = new PropertyCell("Oriental Avenue", 50, "aqua", 6, 100);
        PropertyCell lb2 = new PropertyCell("Vermont Avenue", 50, "aqua", 6, 100);
        PropertyCell lb3 = new PropertyCell("Connecticut Avenue", 50, "aqua", 8, 120);

        cellArray[5] = lb1;
        cellArray[7] = lb2;
        cellArray[8] = lb3;

        PropertyCell p1 = new PropertyCell("St. Charles Place", 100, "fuchsia", 10, 140);
        PropertyCell p2 = new PropertyCell("States Avenue", 100, "fuchsia", 10, 140);
        PropertyCell p3 = new PropertyCell("Virginia Avenue", 100, "fuchsia", 12, 160);

        cellArray[10] = p1;
        cellArray[12] = p2;
        cellArray[13] = p3;

        PropertyCell o1 = new PropertyCell("St. James Avenue", 100, "maroon", 14, 180);
        PropertyCell o2 = new PropertyCell("Tennessee Avenue", 100, "maroon", 14, 180);
        PropertyCell o3 = new PropertyCell("New York Avenue", 100, "maroon", 16, 200);

        cellArray[15] = o1;
        cellArray[17] = o2;
        cellArray[18] = o3;

        PropertyCell r1 = new PropertyCell("Kentucky Avenue", 150, "red", 18, 220);
        PropertyCell r2 = new PropertyCell("Indiana Avenue", 150, "red", 18, 220);
        PropertyCell r3 = new PropertyCell("Illinois Avenue", 150, "red", 20, 240);

        cellArray[20] = r1;
        cellArray[22] = r2;
        cellArray[23] = r3;

        PropertyCell y1 = new PropertyCell("Atlantic Avenue", 150, "yellow", 22, 260);
        PropertyCell y2 = new PropertyCell("Ventnor Avenue", 150, "yellow", 22, 260);
        PropertyCell y3 = new PropertyCell("Marvin Gardens", 150, "yellow", 24, 280);

        cellArray[25] = y1;
        cellArray[26] = y2;
        cellArray[28] = y3;

        PropertyCell g1 = new PropertyCell("Pacific Avenue", 200, "green", 26, 300);
        PropertyCell g2 = new PropertyCell("North Carolina Avenue", 200, "green", 26, 300);
        PropertyCell g3 = new PropertyCell("Pennsylvania Avenue", 200, "green", 28, 320);

        cellArray[30] = g1;
        cellArray[31] = g2;
        cellArray[33] = g3;

        PropertyCell db1 = new PropertyCell("Park Place", 200, "blue", 35, 350);
        PropertyCell db2 = new PropertyCell("Dright Place", 200, "blue", 35, 350);
        PropertyCell db3 = new PropertyCell("Boardwalk", 200, "blue", 50, 400);

        cellArray[36] = db1;
        cellArray[37] = db2;
        cellArray[38] = db3;
    }

    private void createRRCells() {
        RailRoadCell rr1 = new RailRoadCell("Reading Railroad");
        RailRoadCell rr2 = new RailRoadCell("Pennsylvania Railroad");
        RailRoadCell rr3 = new RailRoadCell("B. & O. RailRoad");
        RailRoadCell rr4 = new RailRoadCell("Short Line");

        setRRCells();

        cellArray[4] = rr1;
        cellArray[14] = rr2;
        cellArray[24] = rr3;
        cellArray[34] = rr4;
    }

    private void createUtilityCells() {
        UtilityCell u1 = new UtilityCell("Electric Company");
        UtilityCell u2 = new UtilityCell("Water Works");

        setUtilityCells();

        cellArray[11] = u1;
        cellArray[27] = u2;
    }

    private void createJailCell() {
        jailCell = new JailCell();
    }

    private void createFreeParkingCell() {
        freeParkingCell = new FreeParkingCell();
    }

    private void createGoToJailCell() {
        goToJailCell = new GoToJailCell();
    }

    private void createCardCells() {
        CardCell cc1 = new CardCell(CardType.COMMUNITY, "Community Chest 1");
        CardCell c1 = new CardCell(CardType.CHANCE, "Chance 1");
        CardCell cc2 = new CardCell(CardType.COMMUNITY, "Community Chest 2");
        CardCell c2 = new CardCell(CardType.CHANCE, "Chance 2");
        CardCell cc3 = new CardCell(CardType.COMMUNITY, "Community Chest 3");
        CardCell c3 = new CardCell(CardType.CHANCE, "Chance 3");

        cellArray[1] = cc1;
        cellArray[16] = cc2;
        cellArray[32] = cc3;
        cellArray[6] = c1;
        cellArray[21] = c2;
        cellArray[35] = c3;
    }

    private void setRRCells() {
        RailRoadCell.setBaseRent(50);
        RailRoadCell.setPrice(200);
    }

    private void setUtilityCells() {
        UtilityCell.setPrice(150);
    }

    private void addJailCell() {
        cellArray[9] = jailCell;
    }

    private void addFreeParkingCell() {
        cellArray[19] = freeParkingCell;
    }

    private void addGoToJailCell() {
        cellArray[29] = goToJailCell;
    }

    private void addCells() {
        for (Cell cell : cellArray) {
            super.addCell(cell);
        }
    }

    private void addCards() {

        super.addCard(new MoneyCard("Win $50", 50, CardType.COMMUNITY));
        super.addCard(new MoneyCard("Win $20", 20, CardType.COMMUNITY));
        super.addCard(new MoneyCard("Win $10", 10, CardType.COMMUNITY));
        super.addCard(new MoneyCard("Lose $100", -100, CardType.COMMUNITY));
        super.addCard(new MoneyCard("Lose $50", -50, CardType.COMMUNITY));
        super.addCard(new JailCard(CardType.COMMUNITY));
        super.addCard(new MovePlayerCard("St. Charles Place", CardType.COMMUNITY));
        super.addCard(new MovePlayerCard("Boardwalk", CardType.COMMUNITY));

        super.addCard(new MoneyCard("Win $50", 50, CardType.CHANCE));
        super.addCard(new MoneyCard("Win $20", 20, CardType.CHANCE));
        super.addCard(new MoneyCard("Win $10", 10, CardType.CHANCE));
        super.addCard(new MoneyCard("Lose $100", -100, CardType.CHANCE));
        super.addCard(new MoneyCard("Lose $50", -50, CardType.CHANCE));
        super.addCard(new JailCard(CardType.CHANCE));
        super.addCard(new MovePlayerCard("Illinois Avenue", CardType.CHANCE));
        super.shuffleCardPiles();
    }
}
