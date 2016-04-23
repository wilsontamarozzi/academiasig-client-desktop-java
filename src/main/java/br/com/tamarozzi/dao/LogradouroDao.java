package br.com.tamarozzi.dao;

import java.util.List;

import br.com.tamarozzi.model.Logradouro;

public interface LogradouroDao {

    public void add(Logradouro Logradouro);

    public void edit(Logradouro Logradouro);

    public void delete(int LogradouroId);
    
    public Logradouro getLogradouroByCEP(String logradouroCEP);

    public List<Logradouro> getAllLogradouro(String campo, String valor);
}
