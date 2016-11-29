package gui;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import logic.cell.Cell;
import logic.gameBoard.GameBoard;

public class GameBoardUtil {

    public static Dimension calculateDimension(int cellNumber) {
        int aCellNumber = cellNumber;
        aCellNumber -= 4;
        int shortSide = aCellNumber / 4;
        int longSide = (aCellNumber - (shortSide * 2)) / 2;
        return new Dimension(longSide, shortSide);
    }

    public static List<Cell> getEastCells(GameBoard board) {
        Dimension d = calculateDimension(board.getCellNumber());
        int shortSide = d.height;
        List<Cell> cells = new ArrayList<>();
        for (int i = board.getCellNumber() - shortSide; i <= board.getCellNumber() - 1; i++) {
            cells.add(board.getCell(i));
        }
        return cells;
    }

    public static List<Cell> getNorthCells(GameBoard board) {
        Dimension d = calculateDimension(board.getCellNumber());
        int longSide = d.width;
        int shortSide = d.height;
        List<Cell> cells = new ArrayList<>();
        for (int i = longSide + 2 + shortSide; i <= longSide + 2 + shortSide + longSide + 1; i++) {
            cells.add(board.getCell(i));
        }
        return cells;
    }

    public static List<Cell> getSouthCells(GameBoard board) {
        Dimension d = calculateDimension(board.getCellNumber());
        int longSide = d.width;
        List<Cell> cells = new ArrayList<>();
        for (int i = longSide + 1; i >= 0; i--) {
            cells.add(board.getCell(i));
        }
        return cells;
    }

    public static List<Cell> getWestCells(GameBoard board) {
        Dimension d = calculateDimension(board.getCellNumber());
        int longSide = d.width;
        int shortSide = d.height;
        List<Cell> cells = new ArrayList<>();
        for (int i = longSide + 1 + shortSide; i > longSide + 1; i--) {
            cells.add(board.getCell(i));
        }
        return cells;
    }
}
