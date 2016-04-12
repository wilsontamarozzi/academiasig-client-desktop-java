package br.com.tamarozzi.dao;

import java.util.List;

import br.com.tamarozzi.model.Pessoa;

public interface PessoaDao {

    /**
     *
     * @param pessoa
     */
    public void add(Pessoa pessoa);

    /**
     *
     * @param pessoa
     */
    public void edit(Pessoa pessoa);

    /**
     *
     * @param pessoaId
     */
    public void delete(int pessoaId);

    /**
     *
     * @param pessoaId
     * @return
     */
    public Pessoa getPessoa(int pessoaId);

    /**
     *
     * @param campo
     * @param valor
     * @param tipoPessoa
     * @param situacao
     * @return
     */
    public List<Pessoa> getAllPessoa(String campo, String valor, String tipoPessoa, String situacao);
}
