package gui;

import termproject.Cell;

public class CellInfoFormatter {

    public String format(Cell cell) {
        return "<html><b>" + cell.getName() + "</b></html>";
    }
}
