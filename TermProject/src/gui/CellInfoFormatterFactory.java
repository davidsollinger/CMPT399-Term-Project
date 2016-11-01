package gui;

public class CellInfoFormatterFactory {
    
    public CellInfoFormatter getCellInfoFormatter (String cellInfoFormatterType) {
        switch (cellInfoFormatterType) {
            case "Property":
                return new PropertyCellInfoFormatter();
            case "Rail Road":
                return new RRCellInfoFormatter();
            case "Utility":
                return new UtilCellInfoFormatter();
            case "Chance":
                return new ChanceCellInfoFormatter();
            case "Community Chest":
                return new CCCellInfoFormatter();
            case "Jail":
                return new CellInfoFormatter();
            case "Go To Jail":
                return new CellInfoFormatter();
            case "Go":
                return new CellInfoFormatter();
            case "Free Parking":
                return new CellInfoFormatter();
            default:
                break;
        }
        return null;
    }
    
}
