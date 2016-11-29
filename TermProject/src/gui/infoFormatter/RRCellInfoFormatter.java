package gui.infoFormatter;

import logic.cell.Cell;
import logic.player.Player;
import logic.cell.RailRoadCell;

public class RRCellInfoFormatter extends CellInfoFormatter {

    public RRCellInfoFormatter(String color) {
        super(color);
    }

    @Override
    public String format(Cell cell) {
        RailRoadCell c = (RailRoadCell) cell;
        StringBuilder buf = new StringBuilder();
        Player owner = cell.getPlayer();
        buf.append("<html><b><font color='")
                .append(super.getColor())
                .append("'>")
                .append(cell.getName())
                .append("</font></b><br>")
                .append("Price: $").append(c.getPrice())
                .append("<br>Owner: ").append(owner.getName())
                .append("</html>");
        return buf.toString();
    }
}
