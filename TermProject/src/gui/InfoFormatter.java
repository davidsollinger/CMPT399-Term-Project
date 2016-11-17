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

    private static final Map<Class<?>, CellInfoFormatter> CELLINFOFORMATTERS = new HashMap<>();

    static {
        if (CELLINFOFORMATTERS.isEmpty()) {
            addFormatters();
        }
    }

    private static void addFormatters() {
        CellInfoFormatterFactory cellInfoFormatterFactory = new CellInfoFormatterFactory();

        CELLINFOFORMATTERS.put(
                PropertyCell.class, cellInfoFormatterFactory.getCellInfoFormatter("Property"));
        CELLINFOFORMATTERS.put(
                GoCell.class, cellInfoFormatterFactory.getCellInfoFormatter("Go"));
        CELLINFOFORMATTERS.put(
                JailCell.class, cellInfoFormatterFactory.getCellInfoFormatter("Jail"));
        CELLINFOFORMATTERS.put(
                GoToJailCell.class, cellInfoFormatterFactory.getCellInfoFormatter("Go To Jail"));
        CELLINFOFORMATTERS.put(
                FreeParkingCell.class, cellInfoFormatterFactory.getCellInfoFormatter("Free Parking"));
        CELLINFOFORMATTERS.put(
                RailRoadCell.class, cellInfoFormatterFactory.getCellInfoFormatter("Rail Road"));
        CELLINFOFORMATTERS.put(
                UtilityCell.class, cellInfoFormatterFactory.getCellInfoFormatter("Utility"));
        CELLINFOFORMATTERS.put(
                CardCell.class, cellInfoFormatterFactory.getCellInfoFormatter("Community Chest"));
    }

    public static String cellInfo(Cell cell) {
        CellInfoFormatter formatter = CELLINFOFORMATTERS.get(cell.getClass());
        return formatter.format(cell);
    }

}
