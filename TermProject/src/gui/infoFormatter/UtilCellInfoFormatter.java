package gui.infoFormatter;

import logic.cell.Cell;

public class UtilCellInfoFormatter extends CellInfoFormatter {

    public UtilCellInfoFormatter(String color) {
        super(color);
    }

    @Override
    public String format(Cell cell) {
        StringBuilder buf = new StringBuilder();
        buf.append("<html><b><font color='")
                .append(super.getColor())
                .append("'>")
                .append(cell.getName())
                .append("</font></b><br>")
                .append("$")
                .append(cell.getPrice())
                .append("<br>Owner: ")
                .append(cell.getPlayer().getName())
                .append("</html>");
        return buf.toString();
    }
}
