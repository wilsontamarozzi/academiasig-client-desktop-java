/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tamarozzi.ui.table;

import br.com.tamarozzi.model.LancamentoMovimentacao;
import br.com.tamarozzi.ui.table.model.LancamentoMovimentacaoTableModel;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;

/**
 *
 * @author Wilson
 */
public class TableLancamentoMovimentacao extends JTable {
    	
    private final LancamentoMovimentacaoTableModel modelo;

    public TableLancamentoMovimentacao() {
        this.modelo = new LancamentoMovimentacaoTableModel();
        
        initComponents();
    }
    
    private void initComponents() {
        this.setModel(this.modelo);
        this.setSizeTable();
    }
    
    private void setSizeTable() {
        this.getColumnModel().getColumn(0).setMaxWidth(20);
    }

    public LancamentoMovimentacao getLancamentoMovimentacaoSelected() {
        int i = this.getSelectedRow();

        if(i < 0) {
            return null;
        }

        return (LancamentoMovimentacao) this.modelo.getItemAt(i);
    }

    public List<LancamentoMovimentacao> getLancamentoMovimentacaosSelected() {
        int[] index = this.getSelectedRows();

        if(index.length <= 0) {
            return null;
        }

        List<LancamentoMovimentacao> lancamentoMovimentacaos = new ArrayList<>(0);

        for(int i : index) {
            lancamentoMovimentacaos.add((LancamentoMovimentacao) this.modelo.getItemAt(i));
        }

        return lancamentoMovimentacaos;
    }

    public void reload(List<LancamentoMovimentacao> lancamentoMovimentacaos) {
        this.modelo.reload((List<Object>) (Object) lancamentoMovimentacaos);
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