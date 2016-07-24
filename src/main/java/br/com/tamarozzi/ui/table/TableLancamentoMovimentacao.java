/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tamarozzi.ui.table;

import br.com.tamarozzi.model.Banco;
import br.com.tamarozzi.ui.table.model.BancoTableModel;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;

/**
 *
 * @author Wilson
 */
public class TableBanco extends JTable {
    	
    private final BancoTableModel modelo;

    public TableBanco() {
        this.modelo = new BancoTableModel();
        
        initComponents();
    }
    
    private void initComponents() {
        this.setModel(this.modelo);
        this.setSizeTable();
    }
    
    private void setSizeTable() {
        this.getColumnModel().getColumn(0).setMaxWidth(20);
    }

    public Banco getBancoSelected() {
        int i = this.getSelectedRow();

        if(i < 0) {
            return null;
        }

        return (Banco) this.modelo.getItemAt(i);
    }

    public List<Banco> getBancosSelected() {
        int[] index = this.getSelectedRows();

        if(index.length <= 0) {
            return null;
        }

        List<Banco> bancos = new ArrayList<>(0);

        for(int i : index) {
            bancos.add((Banco) this.modelo.getItemAt(i));
        }

        return bancos;
    }

    public void reload(List<Banco> bancos) {
        this.modelo.reload((List<Object>) (Object) bancos);
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