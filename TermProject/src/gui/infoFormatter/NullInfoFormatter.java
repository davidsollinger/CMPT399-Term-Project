package gui.infoFormatter;

import logic.cell.Cell;

public class NullInfoFormatter extends CellInfoFormatter {
    
    @Override
    public String format(Cell cell) {
        return "";
    }
    
}
