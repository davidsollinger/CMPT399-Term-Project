package gui;

import termproject.Cell;

public class CellInfoFormatter {
    
    private String color;
    
    public CellInfoFormatter () {
        this("black");
    }
    
    public CellInfoFormatter (String color) {
        this.color = color.toLowerCase();
    }
    
    public String getColor() {
        return this.color;
    }

    public String format(Cell cell) {
        return "<html><font color='" + this.color + "'><b>" + cell.getName() + "</b></font></html>";
    }
}
