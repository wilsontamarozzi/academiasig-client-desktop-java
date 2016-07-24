package br.com.tamarozzi.dao;

import java.util.List;

import br.com.tamarozzi.model.LancamentoCategoria;
import org.json.JSONObject;

public interface LancamentoCategoriaDao {

    public JSONObject add(LancamentoCategoria lancamentoCategoria);

    public JSONObject edit(LancamentoCategoria lancamentoCategoria);

    public void delete(String lancamentoCategoriaUUID);
    
    public LancamentoCategoria getLancamentoCategoria(LancamentoCategoria lancamentoCategoria);

    public List<LancamentoCategoria> getAllLancamentoCategoria(String campo, String valor);
}
