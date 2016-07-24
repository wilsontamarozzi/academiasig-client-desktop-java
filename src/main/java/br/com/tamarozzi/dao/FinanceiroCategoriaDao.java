package br.com.tamarozzi.dao;

import br.com.tamarozzi.model.LancamentoCategoria;
import java.util.List;

import org.json.JSONObject;

public interface LancamentoCategoriaDao {

    public JSONObject add(LancamentoCategoria categoria);

    public JSONObject edit(LancamentoCategoria categoria);

    public void delete(String grupoUUID);
    
    public LancamentoCategoria get(LancamentoCategoria grupo);

    public List<LancamentoCategoria> getAll(String campo, String valor);
}
