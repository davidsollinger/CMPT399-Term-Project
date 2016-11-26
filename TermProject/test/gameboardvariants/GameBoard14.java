package gameboardvariants;

import termproject.GameBoard;
import termproject.PropertyCell;

public class GameBoard14 extends GameBoard {

    public GameBoard14() {
        super();
        PropertyCell blue1 = new PropertyCell("Blue 1", 30, "blue", 10, 100);
        PropertyCell blue2 = new PropertyCell("Blue 2", 30, "blue", 10, 100);
        PropertyCell blue3 = new PropertyCell("Blue 3", 30, "blue", 12, 120);
        PropertyCell green1 = new PropertyCell("Green 1", 40, "green", 20, 200);
        PropertyCell green2 = new PropertyCell("Green 2", 40, "green", 24, 240);
        PropertyCell green3 = new PropertyCell("Green 3", 40, "green", 26, 260);
        PropertyCell red1 = new PropertyCell("Red 1", 50, "red", 30, 300);
        PropertyCell red2 = new PropertyCell("Red 2", 50, "red", 30, 300);
        PropertyCell red3 = new PropertyCell("Red 3", 50, "red", 32, 320);
        PropertyCell purple1 = new PropertyCell("Purple 1", 60, "purple", 34, 340);
        PropertyCell purple2 = new PropertyCell("Purple 2", 60, "purple", 36, 360);
        PropertyCell yellow1 = new PropertyCell("Yellow 1", 70, "yellow", 40, 400);
        PropertyCell yellow2 = new PropertyCell("Yellow 2", 70, "yellow", 42, 420);

        super.addCell(blue1);
        super.addCell(blue2);
        super.addCell(blue3);
        super.addCell(green1);
        super.addCell(green2);
        super.addCell(green3);
        super.addCell(red1);
        super.addCell(red2);
        super.addCell(red3);
        super.addCell(purple1);
        super.addCell(purple2);
        super.addCell(yellow1);
        super.addCell(yellow2);
    }
}
