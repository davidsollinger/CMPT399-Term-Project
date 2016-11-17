package gameboardvariants;

import termproject.Card;
import termproject.CardCell;
import termproject.GameBoard;
import termproject.JailCell;
import termproject.MoneyCard;
import termproject.PropertyCell;

public class GameBoardCCLoseMoney extends GameBoard {

    public GameBoardCCLoseMoney() {
        super();
        PropertyCell blue1 = new PropertyCell();
        PropertyCell blue2 = new PropertyCell();
        CardCell cc1 = new CardCell("Community Chest 1", "Community Chest");
        JailCell jail = new JailCell();
        CardCell chance1 = new CardCell("Chance 1", "Chance");

        Card ccCard1 = new MoneyCard("Pay $20", -20, "Community Chest");

        blue1.setName("Blue 1");
        blue2.setName("Blue 2");

        blue1.setColorGroup("blue");
        blue2.setColorGroup("blue");

        blue1.setPrice(100);
        blue2.setPrice(100);

        blue1.setRent(10);
        blue2.setRent(10);

        blue1.setHousePrice(50);
        blue2.setHousePrice(50);

        super.addCard(ccCard1);
        super.addCell(cc1);
        super.addCell(blue1);
        super.addCell(jail);
        super.addCell(blue2);
        super.addCell(chance1);

    }
}
