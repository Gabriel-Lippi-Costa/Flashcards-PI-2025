/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package telas;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author zion
 */
public class TableActionCellRendererBaralhosDoGrupo extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable jtable, Object o, boolean isSelected, boolean bln1, int row, int i1) {
        Component com = super.getTableCellRendererComponent(jtable, o, isSelected, bln1, row, i1);
        PainelTabelaBaralhosDoGrupo painel = new PainelTabelaBaralhosDoGrupo();
        if (!isSelected) {
            if (row % 2 == 0) {
                painel.setBackground(new Color(250, 240, 220));
            } else {
                painel.setBackground(new Color(226, 211, 191));
            }
        } else {
            painel.setBackground(com.getBackground());
        }
        return painel;
    }

}
