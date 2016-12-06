package gui;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

class CellRenderer extends JLabel implements ListCellRenderer {

    private static final long serialVersionUID = 1L;

    private boolean backgroundColor;

    public CellRenderer() {
        super.setOpaque(true);
        backgroundColor = false;
    }

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        PlayerColor color = (PlayerColor) value;
        backgroundColor = true;
        setText(color.toString());
        setBackground(color.getColor());
        backgroundColor = false;
        return this;
    }

    @Override
    public void setBackground(Color color) {
        if (!backgroundColor) {
            return;
        }
        super.setBackground(color);
    }
}
