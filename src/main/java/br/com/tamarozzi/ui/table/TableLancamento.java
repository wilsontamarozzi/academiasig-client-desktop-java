/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tamarozzi.ui.table;

import br.com.tamarozzi.model.Lancamento;
import br.com.tamarozzi.ui.table.model.LancamentoTableModel;
import br.com.tamarozzi.ui.table.renderer.LancamentoTableCellRenderer;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;

/**
 *
 * @author Wilson
 */
public class TableLancamento extends JTable {
    	
    private final LancamentoTableModel modelo;
    private final LancamentoTableCellRenderer renderer;

    public TableLancamento() {
        this.modelo = new LancamentoTableModel();
        this.renderer = new LancamentoTableCellRenderer();
        
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

    public Lancamento getLancamentoSelected() {
        int i = this.getSelectedRow();

        if(i < 0) {
            return null;
        }

        return (Lancamento) this.modelo.getItemAt(i);
    }

    public List<Lancamento> getLancamentosSelected() {
        int[] index = this.getSelectedRows();

        if(index.length <= 0) {
            return null;
        }

        List<Lancamento> lancamentos = new ArrayList<>(0);

        for(int i : index) {
            lancamentos.add((Lancamento) this.modelo.getItemAt(i));
        }

        return lancamentos;
    }

    public void reload(List<Lancamento> lancamentos) {
        this.modelo.reload((List<Object>) (Object) lancamentos);
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