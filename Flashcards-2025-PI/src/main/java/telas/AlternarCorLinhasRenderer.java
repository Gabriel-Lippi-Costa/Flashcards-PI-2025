package telas;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class AlternarCorLinhasRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                   boolean hasFocus, int row, int column) {
        Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        // Alternância de cores
        if (!isSelected) {
            if (row % 2 == 0) {
                comp.setBackground(new Color(250, 240, 220));
                comp.setForeground(Color.BLACK);
            } else {
                comp.setBackground(new Color(226, 211, 191));
                comp.setForeground(Color.BLACK);
            }
        } else {
            comp.setBackground(table.getSelectionBackground());
            comp.setForeground(table.getSelectionForeground());
        }

        // Remover borda do componente
        ((JComponent) comp).setBorder(null);

        // Centralizar texto
        if (comp instanceof JLabel) {
            ((JLabel) comp).setHorizontalAlignment(SwingConstants.CENTER);
        }

        return comp;
    }
}