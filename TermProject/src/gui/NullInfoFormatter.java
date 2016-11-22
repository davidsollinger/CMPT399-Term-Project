package gui;

import termproject.Cell;

public class NullInfoFormatter extends CellInfoFormatter {
    
    @Override
    public String format(Cell cell) {
        return "";
    }
    
}
