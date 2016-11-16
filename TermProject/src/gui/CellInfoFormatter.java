package gui;

import termproject.Cell;
import termproject.Nullable;

public class CellInfoFormatter implements Nullable {

    private String color;

    public CellInfoFormatter() {
        this("black");
    }

    public CellInfoFormatter(String color) {
        this.color = color.toLowerCase();
    }

    public String getColor() {
        return color;
    }

    public String format(Cell cell) {
        return "<html><font color='" + color + "'><b>" + cell.getName() + "</b></font></html>";
    }

    @Override
    public boolean isNull() {
        return false;
    }
}
