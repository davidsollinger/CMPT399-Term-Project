package termproject;

public class SimpleGameBoard extends GameBoard {

    public SimpleGameBoard() {
        super();
        PropertyCell blue1 = new PropertyCell("Blue 1", 50, "blue", 10, 100);
        PropertyCell blue2 = new PropertyCell("Blue 2", 50, "blue", 10, 100);
        PropertyCell blue3 = new PropertyCell("Blue 3", 50, "blue", 10, 120);
        PropertyCell green1 = new PropertyCell("Green 1", 70, "green", 1600, 200);
        PropertyCell green2 = new PropertyCell("Green 2", 70, "green", 20, 200);

        super.addPropertyCell(blue1);
        super.addPropertyCell(blue2);
        super.addPropertyCell(blue3);
        super.addPropertyCell(green1);
        super.addPropertyCell(green2);
    }
}
