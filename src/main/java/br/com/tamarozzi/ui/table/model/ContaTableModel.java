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
import br.com.tamarozzi.model.Conta;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ContaTableModel extends AbstractTableModel implements MyAbstractTableModel {

    private static final long serialVersionUID = 1L;

    private List<Object> contas;

    private final String[] colNomes = {"#", "Descrição", "Tipo Conta", "Ativo"};

    private final Class<?>[] colTipo = {String.class, String.class, String.class, Boolean.class};

    public ContaTableModel() {
        this.contas = new ArrayList<>(0);
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
        if (this.contas == null) {
            return 0;
        }

        return this.contas.size();
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        Conta c = (Conta) this.contas.get(linha);
        
        switch (coluna) {
            case 0:
                return "*";
            case 1:
                return c.getDescricao();
            case 2:
                return c.getTipoConta();
            case 3:
                return c.isAtivo();
            default:
                return null;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public void reload(List<Object> itens) {
        this.contas = new ArrayList<>(0);
        this.contas.addAll(itens);
        
        fireTableDataChanged();
    }

    @Override
    public void addItem(Object item) {
        Collections.reverse(contas);
        this.contas.add(item);
        Collections.reverse(contas);
        
        fireTableDataChanged();
    }

    @Override
    public void addItens(List<Object> itens) {
        this.contas.addAll(itens);
        
        fireTableDataChanged();
    }

    @Override
    public void removeItem(Object item) {
        this.contas.remove(item);
        
        fireTableDataChanged();
    }

    @Override
    public void removeItens(List<Object> itens) {
        this.contas.removeAll(itens);
        
        fireTableDataChanged();
    }

    @Override
    public Object getItemAt(int index) {
        return this.contas.get(index);
    }
}
