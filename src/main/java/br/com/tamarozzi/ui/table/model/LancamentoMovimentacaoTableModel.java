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
import br.com.tamarozzi.model.Banco;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class BancoTableModel extends AbstractTableModel implements MyAbstractTableModel {

    private List<Object> bancos;

    private final String[] colNomes = {"#", "Nome", "Número"};

    private final Class<?>[] colTipo = {String.class, String.class, String.class};

    public BancoTableModel() {
        this.bancos = new ArrayList<>(0);
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
        if (this.bancos == null) {
            return 0;
        }

        return this.bancos.size();
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        Banco object = (Banco) this.bancos.get(linha);

        switch (coluna) {
            case 0:
                return "*";
            case 1:
                return object.getNome();
            case 2:
                return object.getNumero();
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
        this.bancos = new ArrayList<>(0);
        this.bancos.addAll(itens);
        
        fireTableDataChanged();
    }

    @Override
    public void addItem(Object item) {
        Collections.reverse(bancos);
        this.bancos.add(item);
        Collections.reverse(bancos);
        
        fireTableDataChanged();
    }

    @Override
    public void addItens(List<Object> itens) {
        this.bancos.addAll(itens);
        
        fireTableDataChanged();
    }

    @Override
    public void removeItem(Object item) {
        this.bancos.remove(item);
        
        fireTableDataChanged();
    }

    @Override
    public void removeItens(List<Object> itens) {
        this.bancos.removeAll(itens);
        
        fireTableDataChanged();
    }

    @Override
    public Object getItemAt(int index) {
        return this.bancos.get(index);
    }    
}
