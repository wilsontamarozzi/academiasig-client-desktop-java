package br.com.tamarozzi.dao;

import java.util.List;

import br.com.tamarozzi.model.Cidade;

public interface CidadeDao {

    public void add(Cidade cidade);

    public void edit(Cidade cidade);

    public void delete(int cidadeId);
    
    public Cidade getCidade(int cidadeId);

    public List<Cidade> getAllCidade(String campo, String valor);
}
