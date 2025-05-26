package telas; 
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class HeaderRenderer extends DefaultTableCellRenderer {
    public HeaderRenderer() {
        setHorizontalAlignment(CENTER);
        setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
        boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        setBackground(new Color(206,191,171)); 
        setForeground(new Color(30, 45, 60));
        setFont(new Font("Comic Sans MS", Font.BOLD, 12));

        return this;
    }
}