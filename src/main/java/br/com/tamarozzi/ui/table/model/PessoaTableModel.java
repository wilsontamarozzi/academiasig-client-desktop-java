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
import br.com.tamarozzi.model.Pessoa;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class PessoaTableModel extends AbstractTableModel implements MyAbstractTableModel {

    private List<Object> pessoas;

    private final String[] colNomes = {"#", "Nome", "E-mail", "Fone Comercial", "Fone Residencial", "Fone Celular", "Inativo"};

    private final Class<?>[] colTipo = {String.class, String.class, String.class, String.class, String.class, String.class, Boolean.class};

    public PessoaTableModel() {
        this.pessoas = new ArrayList<>(0);
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
        if (this.pessoas == null) {
            return 0;
        }

        return this.pessoas.size();
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        Pessoa p = (Pessoa) this.pessoas.get(linha);

        String tipo = p.getTipoPessoa();

        switch (coluna) {
            case 0:
                return "*";
            case 1:
                return p.getNome();
            case 2:
                return p.getEmail();
            case 3:
                return tipo.equalsIgnoreCase("F") ? p.getTelefoneComercial() : p.getTelefoneEmpresa();
            case 4:
                return p.getTelefoneResidencial();
            case 5:
                return p.getTelefoneCelular();
            case 6:
                return !p.isAtivo();
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
        this.pessoas = new ArrayList<>(0);
        this.pessoas.addAll(itens);
        
        fireTableDataChanged();
    }

    @Override
    public void addItem(Object item) {
        Collections.reverse(pessoas);
        this.pessoas.add(item);
        Collections.reverse(pessoas);
        
        fireTableDataChanged();
    }

    @Override
    public void addItens(List<Object> itens) {
        this.pessoas.addAll(itens);
        
        fireTableDataChanged();
    }

    @Override
    public void removeItem(Object item) {
        this.pessoas.remove(item);
        
        fireTableDataChanged();
    }

    @Override
    public void removeItens(List<Object> itens) {
        this.pessoas.removeAll(itens);
        
        fireTableDataChanged();
    }

    @Override
    public Object getItemAt(int index) {
        return this.pessoas.get(index);
    }
}
