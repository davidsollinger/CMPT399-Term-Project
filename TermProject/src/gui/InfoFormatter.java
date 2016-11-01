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

    private static Map<Class, CellInfoFormatter> cellInfoFormatters = null;

    static {
        if (cellInfoFormatters == null) {
            cellInfoFormatters = new HashMap<>();
            addFormatters();
        }
    }

    private static void addFormatters() {
        CellInfoFormatterFactory cellInfoFormatterFactory = new CellInfoFormatterFactory();
        
        cellInfoFormatters.put(
                PropertyCell.class, cellInfoFormatterFactory.getCellInfoFormatter("Property"));
        cellInfoFormatters.put(
                GoCell.class, cellInfoFormatterFactory.getCellInfoFormatter("Go"));
        cellInfoFormatters.put(
                JailCell.class, cellInfoFormatterFactory.getCellInfoFormatter("Jail"));
        cellInfoFormatters.put(
                GoToJailCell.class, cellInfoFormatterFactory.getCellInfoFormatter("Go To Jail"));
        cellInfoFormatters.put(
                FreeParkingCell.class, cellInfoFormatterFactory.getCellInfoFormatter("Free Parking"));
        cellInfoFormatters.put(
                RailRoadCell.class, cellInfoFormatterFactory.getCellInfoFormatter("Rail Road"));
        cellInfoFormatters.put(
                UtilityCell.class, cellInfoFormatterFactory.getCellInfoFormatter("Utility"));
        cellInfoFormatters.put(
                CardCell.class, cellInfoFormatterFactory.getCellInfoFormatter("Community Chest"));
    }

    public static String cellInfo(Cell cell) {
        CellInfoFormatter formatter = cellInfoFormatters.get(cell.getClass());
        return formatter.format(cell);
    }

}
