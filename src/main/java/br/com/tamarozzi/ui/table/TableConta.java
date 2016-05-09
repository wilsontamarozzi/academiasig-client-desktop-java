/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tamarozzi.ui.table;

import br.com.tamarozzi.model.Conta;
import br.com.tamarozzi.ui.table.model.ContaTableModel;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;

/**
 *
 * @author Wilson
 */
public class TableConta extends JTable {
    	
    private final ContaTableModel modelo;

    public TableConta() {
        this.modelo = new ContaTableModel();
        
        initComponents();
    }
    
    private void initComponents() {
        this.setModel(this.modelo);
        this.setSizeTable();
    }
    
    private void setSizeTable() {
        this.getColumnModel().getColumn(0).setMaxWidth(20);
    }

    public Conta getContaSelected() {
        int i = this.getSelectedRow();

        if(i < 0) {
            return null;
        }

        return (Conta) this.modelo.getItemAt(i);
    }

    public List<Conta> getContasSelected() {
        int[] index = this.getSelectedRows();

        if(index.length <= 0) {
            return null;
        }

        List<Conta> contas = new ArrayList<>(0);

        for(int i : index) {
            contas.add((Conta) this.modelo.getItemAt(i));
        }

        return contas;
    }

    public void reload(List<Conta> contas) {
        this.modelo.reload((List<Object>) (Object) contas);
    }
    
    public void addItem(Object obj) {
        this.modelo.addItem(obj);
    }
    
    public void addItens(List<Object> objs) {
        this.modelo.addItens(objs);
    }
    
    public void removeItem(Object obj) {
        this.modelo.removeItem(obj);
    }
    
    public void removeItens(List<Object> objs) {
        this.modelo.removeItens(objs);
    }
}