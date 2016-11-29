package gui.infoFormatter;

import logic.cell.Cell;
import logic.cell.PropertyCell;

public class PropertyCellInfoFormatter extends CellInfoFormatter {

    @Override
    public String format(Cell cell) {
        PropertyCell propertyCell = (PropertyCell) cell;
        StringBuilder buf = new StringBuilder();
        buf.append("<html><b><font color='")
                .append(propertyCell.getColorGroup())
                .append("'>")
                .append(propertyCell.getName())
                .append("</font></b><br>")
                .append("Price: $")
                .append(propertyCell.getPrice())
                .append("<br>Owner: ")
                .append(propertyCell.getName())
                .append("<br>Houses: ")
                .append(propertyCell.getNumHouses())
                .append("</html>");
        return buf.toString();
    }
}
