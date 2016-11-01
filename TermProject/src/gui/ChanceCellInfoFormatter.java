package gui;

import termproject.Cell;

public class ChanceCellInfoFormatter extends CellInfoFormatter {

    @Override
    public String format(Cell cell) {
        return "<html><font color='teal'><b>" + cell.getName() + "</b></font></html>";
    }
}
