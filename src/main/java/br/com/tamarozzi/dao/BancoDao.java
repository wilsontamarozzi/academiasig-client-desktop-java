package br.com.tamarozzi.dao;

import java.util.List;

import br.com.tamarozzi.model.Banco;
import org.json.JSONObject;

public interface BancoDao {

    public JSONObject add(Banco banco);

    public JSONObject edit(Banco banco);

    public void delete(int bancoId);
    
    public Banco getBanco(Banco banco);

    public List<Banco> getAllBanco(String campo, String valor);
}
