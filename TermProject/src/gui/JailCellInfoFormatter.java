package gui;

import termproject.Cell;

public class JailCellInfoFormatter implements CellInfoFormatter {

    public static final String JAIL_CELL_LABEL = "<html><b>Jail</b></html>";

    @Override
    public String format(Cell cell) {
        return JAIL_CELL_LABEL;
    }

}
