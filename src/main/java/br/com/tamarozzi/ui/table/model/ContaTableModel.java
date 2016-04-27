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

import br.com.tamarozzi.model.Conta;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ContaTableModel extends AbstractTableModel {

    private static final long serialVersionUID = 1L;

    private List<Conta> contas;

    private final String[] colNomes = {"#", "Descrição", "Tipo Conta", "Inativo"};

    private final Class<?>[] colTipo = {String.class, String.class, String.class, Boolean.class};

    public ContaTableModel() {
    }

    public void reload(List<Conta> contas) {
        this.contas = new ArrayList<>(0);
        this.contas.addAll(contas);
        
        fireTableDataChanged();
    }
    
    public void addConta(Conta conta) {
        Collections.reverse(contas);
        this.contas.add(conta);
        Collections.reverse(contas);
        
        fireTableDataChanged();
    }
    
    public void removeConta(Conta conta) {
        this.contas.remove(conta);
        
        fireTableDataChanged();
    }
    
    public void removeConta(List<Conta> contas) {
        contas.forEach(p -> {
            this.contas.remove(p);
        });
        
        fireTableDataChanged();
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

        Conta c = this.contas.get(linha);
        
        switch (coluna) {
            case 0:
                return "*";
            case 1:
                return c.getDescricao();
            case 2:
                return c.getTipoConta();
            case 3:
                return !c.isAtivo();
            default:
                return null;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    private Conta getContaAt(int index) {
        return this.contas.get(index);
    }

    public Conta getContaSelected(int getSelectedRow) {
        if (getSelectedRow < 0) {
            return null;
        }

        return this.getContaAt(getSelectedRow);
    }

    public List<Conta> getContasSelected(int[] getSelectedRows) {
        if (getSelectedRows.length <= 0) {
            return null;
        }

        List<Conta> selectedContas = new ArrayList<>(0);

        for (int i : getSelectedRows) {
            selectedContas.add(this.getContaAt(i));
        }

        return selectedContas;
    }
}
