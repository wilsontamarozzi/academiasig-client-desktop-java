package br.com.tamarozzi.dao;

import java.util.List;

import br.com.tamarozzi.model.Conta;
import org.json.JSONObject;

public interface ContaDao {

    public JSONObject add(Conta conta);

    public JSONObject edit(Conta conta);

    public void delete(int contaId);
    
    public Conta getConta(Conta conta);

    public List<Conta> getAllConta(String campo, String valor, String tipoConta, String situacao);
}
