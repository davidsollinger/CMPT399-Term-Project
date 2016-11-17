package termproject;

public class JailCell extends Cell {

    private static final int BAIL = 50;

    public JailCell() {
        super.setName("Jail");
    }
    
    public static int getBail() {
        return BAIL;
    }

}
