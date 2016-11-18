package termproject;

public class NullGameBoard extends GameBoard {
    
    @Override
    public Cell queryCell(String cell) {
        return new NullCell();
    } 
    
    @Override
    public void removeCards() {
    }
    
}
