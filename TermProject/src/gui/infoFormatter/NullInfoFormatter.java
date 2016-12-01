package gui.infoFormatter;

import logic.cell.Cell;

public class NullInfoFormatter extends CellInfoFormatter {
    
    @Override
    public String getColor() {
        return "";
    }
    
    @Override
    public String format(Cell cell) {
        return "<html><font color=''><b></b></font></html>";
    }
    
}
