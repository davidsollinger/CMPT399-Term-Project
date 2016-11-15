package gui;

import termproject.Cell;
import termproject.Player;
import termproject.UtilityCell;

public class UtilCellInfoFormatter extends CellInfoFormatter {

    public UtilCellInfoFormatter(String color) {
        super(color);
    }
    
    @Override
    public String format(Cell cell) {
        UtilityCell c = (UtilityCell) cell;
        StringBuilder buf = new StringBuilder();
        Player owner = cell.getPlayer();
        buf.append("<html><b><font color='")
                .append(super.getColor())
                .append("'>")
                .append(cell.getName())
                .append("</font></b><br>")
                .append("$").append(c.getPrice())
                .append("<br>Owner: ").append(owner.getName())
                .append("</html>");
        return buf.toString();
    }
}
