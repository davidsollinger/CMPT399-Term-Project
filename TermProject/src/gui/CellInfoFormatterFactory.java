package gui;

public class CellInfoFormatterFactory {
    
    public CellInfoFormatter getCellInfoFormatter (String cellInfoFormatterType) {
        switch (cellInfoFormatterType) {
            case "Property":
                return new PropertyCellInfoFormatter();
            case "Rail Road":
                return new RRCellInfoFormatter("lime");
            case "Utility":
                return new UtilCellInfoFormatter("olive");
            case "Chance":
                return new CellInfoFormatter("teal");
            case "Community Chest":
                return new CellInfoFormatter("teal");
            case "Jail":
                return new CellInfoFormatter();
            case "Go To Jail":
                return new CellInfoFormatter();
            case "Go":
                return new CellInfoFormatter();
            case "Free Parking":
                return new CellInfoFormatter();
            default:
                return new NullInfoFormatter();
        }
    }
    
}
