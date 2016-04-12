package br.com.tamarozzi.services;

import java.util.List;

import br.com.tamarozzi.model.Pessoa;

public interface PessoaService {

    public void add(Pessoa pessoa);

    public void edit(Pessoa pessoa);

    public void delete(int pessoaId);

    public Pessoa getPessoa(int pessoaId);

    public List<Pessoa> getAllPessoa(String campo, String valor, String tipoPessoa, String situacao);
}
