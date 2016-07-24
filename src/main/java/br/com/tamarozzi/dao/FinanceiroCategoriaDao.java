package br.com.tamarozzi.dao;

import br.com.tamarozzi.model.FinanceiroCategoria;
import java.util.List;

import org.json.JSONObject;

public interface FinanceiroCategoriaDao {

    public JSONObject add(FinanceiroCategoria categoria);

    public JSONObject edit(FinanceiroCategoria categoria);

    public void delete(String grupoUUID);
    
    public FinanceiroCategoria get(FinanceiroCategoria grupo);

    public List<FinanceiroCategoria> getAll(String campo, String valor);
}
