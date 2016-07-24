package br.com.tamarozzi.dao;

import java.util.List;

import br.com.tamarozzi.model.Lancamento;
import org.json.JSONObject;

public interface LancamentoDao {

    public JSONObject add(Lancamento lancamento);

    public JSONObject edit(Lancamento lancamento);

    public void delete(String lancamentoUUID);
    
    public Lancamento get(Lancamento lancamento);

    public List<Lancamento> getAll(String campo, String valor);
}
