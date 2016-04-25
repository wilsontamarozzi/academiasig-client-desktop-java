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

import br.com.tamarozzi.model.Banco;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class BancoTableModel extends AbstractTableModel {

    private static final long serialVersionUID = 1L;

    private List<Banco> bancos;

    private final String[] colNomes = {"#", "Nome", "Número"};

    private final Class<?>[] colTipo = {String.class, String.class, String.class};

    public BancoTableModel() {
    }

    public void reload(List<Banco> bancos) {
        this.bancos = new ArrayList<>(0);
        this.bancos.addAll(bancos);
        
        fireTableDataChanged();
    }
    
    public void addBanco(Banco banco) {
        Collections.reverse(bancos);
        this.bancos.add(banco);
        Collections.reverse(bancos);
        
        fireTableDataChanged();
    }
    
    public void removeBanco(Banco banco) {
        this.bancos.remove(banco);
        
        fireTableDataChanged();
    }
    
    public void removeBanco(List<Banco> bancos) {
        bancos.forEach(b -> {
            this.bancos.remove(b);
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
        if (this.bancos == null) {
            return 0;
        }

        return this.bancos.size();
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        Banco object = this.bancos.get(linha);

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

    private Banco getBancoAt(int index) {
        return this.bancos.get(index);
    }

    public Banco getBancoSelected(int getSelectedRow) {
        if (getSelectedRow < 0) {
            return null;
        }

        return this.getBancoAt(getSelectedRow);
    }

    public List<Banco> getBancosSelected(int[] getSelectedRows) {
        if (getSelectedRows.length <= 0) {
            return null;
        }

        List<Banco> selectedBancos = new ArrayList<>(0);

        for (int i : getSelectedRows) {
            selectedBancos.add(this.getBancoAt(i));
        }

        return selectedBancos;
    }
}
