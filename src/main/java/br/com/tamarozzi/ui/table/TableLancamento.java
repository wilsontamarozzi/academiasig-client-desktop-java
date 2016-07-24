/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tamarozzi.ui.table;

import br.com.tamarozzi.model.Tarefa;
import br.com.tamarozzi.ui.table.model.TarefaTableModel;
import br.com.tamarozzi.ui.table.renderer.TarefaTableCellRenderer;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;

/**
 *
 * @author Wilson
 */
public class TableTarefa extends JTable {
    	
    private final TarefaTableModel modelo;
    private final TarefaTableCellRenderer renderer;

    public TableTarefa() {
        this.modelo = new TarefaTableModel();
        this.renderer = new TarefaTableCellRenderer();
        
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

    public Tarefa getTarefaSelected() {
        int i = this.getSelectedRow();

        if(i < 0) {
            return null;
        }

        return (Tarefa) this.modelo.getItemAt(i);
    }

    public List<Tarefa> getTarefasSelected() {
        int[] index = this.getSelectedRows();

        if(index.length <= 0) {
            return null;
        }

        List<Tarefa> tarefas = new ArrayList<>(0);

        for(int i : index) {
            tarefas.add((Tarefa) this.modelo.getItemAt(i));
        }

        return tarefas;
    }

    public void reload(List<Tarefa> tarefas) {
        this.modelo.reload((List<Object>) (Object) tarefas);
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