package gui;

import termproject.Cell;
import termproject.Player;
import termproject.RailRoadCell;

public class RRCellInfoFormatter extends CellInfoFormatter {

    @Override
    public String format(Cell cell) {
        RailRoadCell c = (RailRoadCell) cell;
        StringBuilder buf = new StringBuilder();
        Player owner = cell.getPlayer();
        String ownerName = "";
        if (owner != null) {
            ownerName = owner.getName();
        }
        buf.append("<html><b><font color='lime'>")
                .append(cell.getName())
                .append("</font></b><br>")
                .append("$").append(c.getPrice())
                .append("<br>Owner: ").append(ownerName)
                .append("</html>");
        return buf.toString();
    }
}
