package logic.gameBoard;

import logic.cell.Cell;
import logic.cell.NullCell;

public class NullGameBoard extends GameBoard {
    
    @Override
    public Cell queryCell(String cell) {
        return new NullCell();
    } 
    
    @Override
    public void removeCards() {
    }
    
}
