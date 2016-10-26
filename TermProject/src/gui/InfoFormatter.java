package gui;

import java.util.HashMap;
import java.util.Map;
import termproject.CardCell;
import termproject.Cell;
import termproject.FreeParkingCell;
import termproject.GoCell;
import termproject.GoToJailCell;
import termproject.JailCell;
import termproject.PropertyCell;
import termproject.RailRoadCell;
import termproject.UtilityCell;

public class InfoFormatter {

    static Map<Class, CellInfoFormatter> cellInfoFormatters = null;

    static {
        if (cellInfoFormatters == null) {
            cellInfoFormatters = new HashMap<>();
            addFormatters();
        }
    }

    private static void addFormatters() {
        cellInfoFormatters.put(
                PropertyCell.class, new PropertyCellInfoFormatter());
        cellInfoFormatters.put(
                GoCell.class, new GoCellInfoFormatter());
        cellInfoFormatters.put(
                JailCell.class, new JailCellInfoFormatter());
        cellInfoFormatters.put(
                GoToJailCell.class, new GotoJailCellInfoFormatter());
        cellInfoFormatters.put(
                FreeParkingCell.class, new FreeParkingCellInfoFormatter());
        cellInfoFormatters.put(
                RailRoadCell.class, new RRCellInfoFormatter());
        cellInfoFormatters.put(
                UtilityCell.class, new UtilCellInfoFormatter());
        cellInfoFormatters.put(
                CardCell.class, new CCCellInfoFormatter());
    }

    public static String cellInfo(Cell cell) {
        CellInfoFormatter formatter = cellInfoFormatters.get(cell.getClass());
        return formatter.format(cell);
    }

}
