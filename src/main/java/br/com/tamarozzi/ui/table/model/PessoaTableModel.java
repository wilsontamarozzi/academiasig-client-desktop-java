package br.com.tamarozzi.ui.table.model;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.com.tamarozzi.model.Pessoa;

public class PessoaTableModel extends AbstractTableModel {

    private static final long serialVersionUID = 1L;

    private List<Pessoa> pessoas;

    private final String[] colNomes = {"#", "Nome", "E-mail", "Fone Residencial",};

    private final Class<?>[] colTipo = {String.class, String.class, String.class, String.class};

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
                return tipo.equalsIgnoreCase("F") ? p.getPessoaFisica().getTelefoneResidencial() : "";
            default:
                return null;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public Pessoa getPessoaAt(int index) {
        return this.pessoas.get(index);
    }
}
