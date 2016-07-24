/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tamarozzi.ui.table;

import br.com.tamarozzi.model.LancamentoCategoria;
import br.com.tamarozzi.ui.table.model.LancamentoCategoriaTableModel;
import br.com.tamarozzi.ui.table.renderer.LancamentoCategoriaTableCellRenderer;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;

/**
 *
 * @author Wilson
 */
public class TableLancamentoCategoria extends JTable {
    	
    private final LancamentoCategoriaTableModel modelo;
    private final LancamentoCategoriaTableCellRenderer renderer;

    public TableLancamentoCategoria() {
        this.modelo = new LancamentoCategoriaTableModel();
        this.renderer = new LancamentoCategoriaTableCellRenderer();
        
        initComponents();
    }
    
    private void initComponents() {
        this.setModel(this.modelo);
        this.setDefaultRenderer(String.class, renderer);
        this.setSizeTable();
    }
    
    private void setSizeTable() {
        this.getColumnModel().getColumn(0).setMaxWidth(20);
    }

    public LancamentoCategoria getLancamentoCategoriaSelected() {
        int i = this.getSelectedRow();

        if(i < 0) {
            return null;
        }

        return (LancamentoCategoria) this.modelo.getItemAt(i);
    }

    public List<LancamentoCategoria> getLancamentoCategoriasSelected() {
        int[] index = this.getSelectedRows();

        if(index.length <= 0) {
            return null;
        }

        List<LancamentoCategoria> lancamentoCategorias = new ArrayList<>(0);

        for(int i : index) {
            lancamentoCategorias.add((LancamentoCategoria) this.modelo.getItemAt(i));
        }

        return lancamentoCategorias;
    }

    public void reload(List<LancamentoCategoria> lancamentoCategorias) {
        this.modelo.reload((List<Object>) (Object) lancamentoCategorias);
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