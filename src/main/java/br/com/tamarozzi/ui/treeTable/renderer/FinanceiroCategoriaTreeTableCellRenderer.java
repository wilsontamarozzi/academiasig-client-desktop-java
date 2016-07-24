/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tamarozzi.ui.treeTable.renderer;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import org.jdesktop.swingx.renderer.DefaultTableRenderer;

/**
 *
 * @author Wilson
 */
public class FinanceiroCategoriaTreeTableCellRenderer extends DefaultTableRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        
        TableModel model = table.getModel();
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        
        String tipo = model.getValueAt(row, column).toString();
        
        if(tipo.equalsIgnoreCase("despesa")) {
            c.setForeground(Color.red);
        }
        
        if(tipo.equalsIgnoreCase("receita")) {
            c.setForeground(Color.black);
        }
               
        return c;
    }
}
