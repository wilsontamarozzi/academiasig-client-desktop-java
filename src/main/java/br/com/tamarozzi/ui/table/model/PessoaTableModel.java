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

import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.com.tamarozzi.model.Pessoa;
import java.util.ArrayList;

public class PessoaTableModel extends AbstractTableModel {

    private static final long serialVersionUID = 1L;

    private List<Pessoa> pessoas;

    private final String[] colNomes = {"#", "Nome", "E-mail", "Fone Comercial", "Fone Residencial", "Fone Celular", "Inativo"};

    private final Class<?>[] colTipo = {String.class, String.class, String.class, String.class, String.class, String.class, Boolean.class};

    public PessoaTableModel() {
    }

    public void reload(List<Pessoa> pessoas) {
        this.pessoas = pessoas;
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
        if (this.pessoas == null) {
            return 0;
        }

        return this.pessoas.size();
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        Pessoa p = this.pessoas.get(linha);

        String tipo = p.getTipoPessoa();

        switch (coluna) {
            case 0:
                return "*";
            case 1:
                return p.getNome();
            case 2:
                return p.getEmail();
            case 3:
                return "";
            case 4:
                return tipo.equalsIgnoreCase("F") ? p.getTelefoneResidencial() : "";
            case 5:
                return tipo.equalsIgnoreCase("F") ? p.getTelefoneCelular() : "";
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

    private Pessoa getPessoaAt(int index) {
        return this.pessoas.get(index);
    }

    public Pessoa getPessoaSelected(int getSelectedRow) {
        if (getSelectedRow < 0) {
            return null;
        }

        return this.getPessoaAt(getSelectedRow);
    }

    public List<Pessoa> getPessoasSelected(int[] getSelectedRows) {
        if (getSelectedRows.length <= 0) {
            return null;
        }

        List<Pessoa> selectedPessoas = new ArrayList<>(0);

        for (int i : getSelectedRows) {
            selectedPessoas.add(this.getPessoaAt(i));
        }

        return selectedPessoas;
    }
}
