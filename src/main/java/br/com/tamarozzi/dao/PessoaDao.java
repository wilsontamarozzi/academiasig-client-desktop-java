package br.com.tamarozzi.dao;

import java.util.List;

import br.com.tamarozzi.model.Pessoa;
import org.json.JSONObject;

public interface PessoaDao {

    public JSONObject add(Pessoa pessoa);

    public JSONObject edit(Pessoa pessoa);

    public void delete(int pessoaId);
    
    public Pessoa getPessoa(Pessoa pessoa);

    public List<Pessoa> getAllPessoa(String campo, String valor, String tipoPessoa, String situacao);
}
