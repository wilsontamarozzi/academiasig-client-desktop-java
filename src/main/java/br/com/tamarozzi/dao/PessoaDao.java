package br.com.tamarozzi.dao;

import java.util.List;

import br.com.tamarozzi.model.Pessoa;

public interface PessoaDao {

    public void add(Pessoa pessoa);

    public void edit(Pessoa pessoa);

    public void delete(int pessoaId);

    public Pessoa getPessoa(int pessoaId);

    public List<Pessoa> getAllPessoa();
}
