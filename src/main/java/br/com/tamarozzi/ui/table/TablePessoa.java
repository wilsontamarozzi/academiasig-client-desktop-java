/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tamarozzi.ui.table;

import br.com.tamarozzi.model.Pessoa;
import br.com.tamarozzi.ui.table.model.PessoaTableModel;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;

/**
 *
 * @author Wilson
 */
public class TablePessoa extends JTable {
    	
    private final PessoaTableModel modelo;

    public TablePessoa() {
        this.modelo = new PessoaTableModel();
        
        initComponents();
    }
    
    private void initComponents() {
        this.setModel(this.modelo);
        this.setSizeTable();
    }

    private void setSizeTable() {
        this.getColumnModel().getColumn(0).setMaxWidth(20);
        this.getColumnModel().getColumn(6).setMaxWidth(80);
    }

    public Pessoa getPessoaSelected() {
        int i = this.getSelectedRow();

        if(i < 0) {
            return null;
        }

        return (Pessoa) this.modelo.getItemAt(i);
    }

    public List<Pessoa> getPessoasSelected() {
        int[] index = this.getSelectedRows();

        if(index.length <= 0) {
            return null;
        }

        List<Pessoa> pessoas = new ArrayList<>(0);

        for(int i : index) {
            pessoas.add((Pessoa) this.modelo.getItemAt(i));
        }

        return pessoas;
    }

    public void reload(List<Pessoa> pessoas) {
        this.modelo.reload((List<Object>) (Object) pessoas);
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