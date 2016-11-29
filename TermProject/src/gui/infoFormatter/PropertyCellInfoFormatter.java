package gui.infoFormatter;

import logic.cell.Cell;
import logic.player.Player;
import logic.cell.PropertyCell;

public class PropertyCellInfoFormatter extends CellInfoFormatter {

    @Override
    public String format(Cell cell) {
        PropertyCell c = (PropertyCell) cell;
        StringBuilder buf = new StringBuilder();
        Player owner = cell.getPlayer();
        buf.append("<html><b><font color='")
                .append(c.getColorGroup())
                .append("'>")
                .append(cell.getName())
                .append("</font></b><br>")
                .append("Price: $").append(c.getPrice())
                .append("<br>Owner: ").append(owner.getName())
                .append("<br>Houses: ").append(c.getNumHouses())
                .append("</html>");
        return buf.toString();
    }
}
