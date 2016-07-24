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
import br.com.tamarozzi.model.LancamentoMovimentacao;
import br.com.tamarozzi.util.MoneyFormatUtil;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class LancamentoMovimentacaoTableModel extends AbstractTableModel implements MyAbstractTableModel {

    private final SimpleDateFormat dateFormat;
    
    private List<Object> lancamentoMovimentacaos;

    private final String[] colNomes = {"#", "Conta", "Data Movimentação", "Valor"};

    private final Class<?>[] colTipo = {String.class, String.class, String.class, String.class};

    public LancamentoMovimentacaoTableModel() {
        this.lancamentoMovimentacaos = new ArrayList<>(0);
        
        this.dateFormat = new SimpleDateFormat("dd/MM/yyyy");
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
        if (this.lancamentoMovimentacaos == null) {
            return 0;
        }

        return this.lancamentoMovimentacaos.size();
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        LancamentoMovimentacao object = (LancamentoMovimentacao) this.lancamentoMovimentacaos.get(linha);

        switch (coluna) {
            case 0:
                return "*";
            case 1:
                return object.getConta().getDescricao();
            case 2:
                return this.dateFormat.format(object.getDataMovimentacao());
            case 3:
                return MoneyFormatUtil.formatReal(object.getValor());
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
        this.lancamentoMovimentacaos = new ArrayList<>(0);
        this.lancamentoMovimentacaos.addAll(itens);
        
        fireTableDataChanged();
    }

    @Override
    public void addItem(Object item) {
        Collections.reverse(lancamentoMovimentacaos);
        this.lancamentoMovimentacaos.add(item);
        Collections.reverse(lancamentoMovimentacaos);
        
        fireTableDataChanged();
    }

    @Override
    public void addItens(List<Object> itens) {
        this.lancamentoMovimentacaos.addAll(itens);
        
        fireTableDataChanged();
    }

    @Override
    public void removeItem(Object item) {
        this.lancamentoMovimentacaos.remove(item);
        
        fireTableDataChanged();
    }

    @Override
    public void removeItens(List<Object> itens) {
        this.lancamentoMovimentacaos.removeAll(itens);
        
        fireTableDataChanged();
    }

    @Override
    public Object getItemAt(int index) {
        return this.lancamentoMovimentacaos.get(index);
    }    
}
