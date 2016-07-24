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
import br.com.tamarozzi.model.Lancamento;
import br.com.tamarozzi.util.MoneyFormatUtil;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class LancamentoTableModel extends AbstractTableModel implements MyAbstractTableModel {

    private final SimpleDateFormat dateFormat;
    
    private List<Object> lancamentos;
    
    private List<Color> rowColours;

    private final String[] colNomes = {"#", "Pessoa", "Descrição", "Valor Final", "Data Liquidação", "Data Vencimento"};

    private final Class<?>[] colTipo = {String.class, String.class, String.class, String.class, String.class, String.class};

    public LancamentoTableModel() {
        this.lancamentos = new ArrayList<>(0);
        this.rowColours = new ArrayList<>(0);
        
        this.dateFormat = new SimpleDateFormat("dd/MM/yyyy");
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
        if (this.lancamentos == null) {
            return 0;
        }

        return this.lancamentos.size();
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        Lancamento object = (Lancamento) this.lancamentos.get(linha);

        this.setColor(linha, object);
        
        switch (coluna) {
            case 0:
                return "*";
            case 1:
                return object.getPessoa().getNome();
            case 2:
                return object.getDescricao();
            case 3:
                return MoneyFormatUtil.formatReal(object.getValorFinal());
            case 4:
                if(object.getDataLiquidacao() != null) {
                    return this.dateFormat.format(object.getDataLiquidacao());
                }
                
                return "";
            case 5:
                return this.dateFormat.format(object.getDataVencimento());
            default:
                return null;
        }
    }
    
    public void setColor(int row, Lancamento lancamento) {
              
        if(lancamento.getTipo().equalsIgnoreCase("Conta Pagar")) {
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
        this.lancamentos = new ArrayList<>(0);
        this.lancamentos.addAll(itens);
        
        fireTableDataChanged();
    }

    @Override
    public void addItem(Object item) {
        Collections.reverse(lancamentos);
        this.lancamentos.add(item);
        Collections.reverse(lancamentos);
        
        fireTableDataChanged();
    }

    @Override
    public void addItens(List<Object> itens) {
        this.lancamentos.addAll(itens);
        
        fireTableDataChanged();
    }

    @Override
    public void removeItem(Object item) {
        this.lancamentos.remove(item);
        
        fireTableDataChanged();
    }

    @Override
    public void removeItens(List<Object> itens) {
        this.lancamentos.removeAll(itens);
        
        fireTableDataChanged();
    }

    @Override
    public Object getItemAt(int index) {
        return this.lancamentos.get(index);
    }    
}
