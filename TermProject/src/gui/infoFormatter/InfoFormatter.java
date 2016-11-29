package gui.infoFormatter;

import java.util.HashMap;
import java.util.Map;
import logic.cell.CardCell;
import logic.cell.Cell;
import logic.cell.FreeParkingCell;
import logic.cell.GoCell;
import logic.cell.GoToJailCell;
import logic.cell.JailCell;
import logic.cell.PropertyCell;
import logic.cell.RailRoadCell;
import logic.cell.UtilityCell;

public class InfoFormatter {

    private static final Map<Class<?>, CellInfoFormatter> CELL_INFOFORMATTERS = new HashMap<>();

    static {
        if (CELL_INFOFORMATTERS.isEmpty()) {
            addFormatters();
        }
    }

    private static void addFormatters() {
        CellInfoFormatterFactory cellInfoFormatterFactory = new CellInfoFormatterFactory();

        CELL_INFOFORMATTERS.put(
                PropertyCell.class, cellInfoFormatterFactory.getCellInfoFormatter("Property"));
        CELL_INFOFORMATTERS.put(
                GoCell.class, cellInfoFormatterFactory.getCellInfoFormatter("Go"));
        CELL_INFOFORMATTERS.put(
                JailCell.class, cellInfoFormatterFactory.getCellInfoFormatter("Jail"));
        CELL_INFOFORMATTERS.put(
                GoToJailCell.class, cellInfoFormatterFactory.getCellInfoFormatter("Go To Jail"));
        CELL_INFOFORMATTERS.put(
                FreeParkingCell.class, cellInfoFormatterFactory.getCellInfoFormatter("Free Parking"));
        CELL_INFOFORMATTERS.put(
                RailRoadCell.class, cellInfoFormatterFactory.getCellInfoFormatter("Rail Road"));
        CELL_INFOFORMATTERS.put(
                UtilityCell.class, cellInfoFormatterFactory.getCellInfoFormatter("Utility"));
        CELL_INFOFORMATTERS.put(
                CardCell.class, cellInfoFormatterFactory.getCellInfoFormatter("Community Chest"));
    }

    public static String getCellInfo(Cell cell) {
        CellInfoFormatter formatter = CELL_INFOFORMATTERS.get(cell.getClass());
        return formatter.format(cell);
    }

}
