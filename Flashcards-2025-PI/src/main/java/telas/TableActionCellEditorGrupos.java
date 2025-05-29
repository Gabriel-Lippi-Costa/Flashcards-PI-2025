/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package telas;

import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;

/**
 *
 * @author zion
 */
public class TableActionCellEditorGrupos extends DefaultCellEditor{
private TableActionEvent event;
    public TableActionCellEditorGrupos(TableActionEvent event){
        super(new JCheckBox());
        this.event = event;
    }
    
    @Override
    public Component getTableCellEditorComponent(JTable jtable, Object o, boolean bln1, int row, int i1){
        PainelTabelaGrupos painel = new PainelTabelaGrupos();
        painel.initEvent(event, row);
        painel.setBackground(jtable.getSelectionBackground());
        return painel;
    }
}