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
import br.com.tamarozzi.model.LancamentoCategoria;
import br.com.tamarozzi.util.MoneyFormatUtil;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class LancamentoCategoriaTableModel extends AbstractTableModel implements MyAbstractTableModel {
    
    private List<Object> lancamentoCategorias;
    
    private List<Color> rowColours;

    private final String[] colNomes = {"#", "Grupo - Categoria", "Valor"};

    private final Class<?>[] colTipo = {String.class, String.class, String.class};

    public LancamentoCategoriaTableModel() {
        this.lancamentoCategorias = new ArrayList<>(0);
        this.rowColours = new ArrayList<>(0);
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
        if (this.lancamentoCategorias == null) {
            return 0;
        }

        return this.lancamentoCategorias.size();
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        LancamentoCategoria object = (LancamentoCategoria) this.lancamentoCategorias.get(linha);

        this.setColor(linha, object);
        
        switch (coluna) {
            case 0:
                return "*";
            case 1:
                return object.getCategoria().getNome();
            case 2:
                return MoneyFormatUtil.formatReal(object.getValor());
            default:
                return null;
        }
    }
    
    public void setColor(int row, LancamentoCategoria lancamentoCategoria) {
               
        if(lancamentoCategoria.getCategoria().getTipo().equalsIgnoreCase("Despesa")) {
            this.rowColours.add(row, Color.RED);
        } else {
            this.rowColours.add(row, Color.BLACK);      
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public void reload(List<Object> itens) {
        this.rowColours = new ArrayList<>(0);
        this.lancamentoCategorias = new ArrayList<>(0);
        this.lancamentoCategorias.addAll(itens);
        
        fireTableDataChanged();
    }

    @Override
    public void addItem(Object item) {
        Collections.reverse(lancamentoCategorias);
        this.lancamentoCategorias.add(item);
        Collections.reverse(lancamentoCategorias);
        
        fireTableDataChanged();
    }

    @Override
    public void addItens(List<Object> itens) {
        this.lancamentoCategorias.addAll(itens);
        
        fireTableDataChanged();
    }

    @Override
    public void removeItem(Object item) {
        this.lancamentoCategorias.remove(item);
        
        fireTableDataChanged();
    }

    @Override
    public void removeItens(List<Object> itens) {
        this.lancamentoCategorias.removeAll(itens);
        
        fireTableDataChanged();
    }

    @Override
    public Object getItemAt(int index) {
        return this.lancamentoCategorias.get(index);
    }    
}
