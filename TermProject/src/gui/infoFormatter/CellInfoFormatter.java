package gui.infoFormatter;

import logic.cell.CardCell;
import logic.cell.Cell;

public class CellInfoFormatter {

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
        return "<html><font color='" + color + "'><b>" + getCellName(cell) + "</b></font></html>";
    }

    private String getCellName(Cell cell) {
        if (cell instanceof CardCell) {
            return cell.getName().substring(0, cell.getName().length() - 2);
        }
        return cell.getName();
    }

}
