package gui;

import java.awt.Color;

public enum PlayerColor {
    GREEN(new Color(0, 128, 0, 75)),
    BLUE(new Color(0, 0, 255, 75)),
    YELLOW(new Color(255, 165, 0, 75)),
    RED(new Color(255, 0, 0, 75)),
    TAN(new Color(128, 128, 0, 75)),
    PEACH(new Color(250, 128, 114, 75)),
    TEAL(new Color(0, 128, 128, 75)),
    PINK(new Color(255, 0, 255, 75));
    
    private final Color color;
    
    PlayerColor(Color color) {
        this.color = color;
    }
    
    public Color getColor() {
        return new Color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
    }
}
