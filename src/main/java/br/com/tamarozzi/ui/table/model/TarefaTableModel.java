/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Panda
 */
package br.com.tamarozzi.ui.table.model;

import br.com.tamarozzi.interfaces.MyAbstractTableModel;
import br.com.tamarozzi.model.Tarefa;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class TarefaTableModel extends AbstractTableModel implements MyAbstractTableModel {

    private SimpleDateFormat dateFormat;
    
    private List<Object> tarefas;
    
    private List<Color> rowColours;

    private final String[] colNomes = {"#", "Categoria", "Descrição", "Responsável", "Data Cadastro", "Data Vencimento", "Data Conclusão", "Concluída"};

    private final Class<?>[] colTipo = {String.class, String.class, String.class, String.class, String.class, String.class, String.class, Boolean.class};

    public TarefaTableModel() {
        this.tarefas = new ArrayList<>(0);
        this.rowColours = new ArrayList<>(0);
        
        this.dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    }
    
    public Color getRowColour(int row) {
        return rowColours.get(row);
    }
    
    @Override
    public Class<?> getColumnClass(int coluna) {
        return this.colTipo[coluna];
    }

    @Override
    public String getColumnName(int coluna) {
        return this.colNomes[coluna];
    }

    @Override
    public int getColumnCount() {
        return this.colNomes.length;
    }

    @Override
    public int getRowCount() {
        if (this.tarefas == null) {
            return 0;
        }

        return this.tarefas.size();
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        Tarefa object = (Tarefa) this.tarefas.get(linha);

        this.checkTask(linha, object);
        
        switch (coluna) {
            case 0:
                return "*";
            case 1:
                return object.getCategoria().getDescricao();
            case 2:
                return object.getDescricao();
            case 3:
                return object.getResponsavel().getNome();
            case 4:
                return this.dateFormat.format(object.getDataCadastro());
            case 5:
                return this.dateFormat.format(object.getDataVencimento());
            case 6:
                if(object.getDataConclusao() == null) {
                    return "";
                }
                
                return this.dateFormat.format(object.getDataConclusao());
            case 7:
                return object.isConcluida();
            default:
                return null;
        }
    }
    
    public void checkTask(int row, Tarefa tarefa) {
        
        if(tarefa.isConcluida()) {
            this.rowColours.add(row, Color.GRAY);
        } else {        
            if(tarefa.getDataVencimento().before(new Date())) {
                this.rowColours.add(row, Color.RED);
            }
            
            if(tarefa.getDataVencimento().after(new Date())) {
                this.rowColours.add(row, Color.BLACK);      
            }
            
            if(tarefa.getDataVencimento().equals(new Date())) {
                this.rowColours.add(row, Color.BLUE);
            }
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public void reload(List<Object> itens) {
        this.rowColours = new ArrayList<>(0);
        this.tarefas = new ArrayList<>(0);
        this.tarefas.addAll(itens);
        
        fireTableDataChanged();
    }

    @Override
    public void addItem(Object item) {
        Collections.reverse(tarefas);
        this.tarefas.add(item);
        Collections.reverse(tarefas);
        
        fireTableDataChanged();
    }

    @Override
    public void addItens(List<Object> itens) {
        this.tarefas.addAll(itens);
        
        fireTableDataChanged();
    }

    @Override
    public void removeItem(Object item) {
        this.tarefas.remove(item);
        
        fireTableDataChanged();
    }

    @Override
    public void removeItens(List<Object> itens) {
        this.tarefas.removeAll(itens);
        
        fireTableDataChanged();
    }

    @Override
    public Object getItemAt(int index) {
        return this.tarefas.get(index);
    }    
}
