package br.com.tamarozzi.dao;

import br.com.tamarozzi.model.FinanceiroCategoriaGrupo;
import java.util.List;

import org.json.JSONObject;

public interface FinanceiroCategoriaGrupoDao {

    public JSONObject add(FinanceiroCategoriaGrupo grupo);

    public JSONObject edit(FinanceiroCategoriaGrupo grupo);

    public void delete(String grupoUUID);
    
    public FinanceiroCategoriaGrupo getGrupoCategoria(FinanceiroCategoriaGrupo grupo);

    public List<FinanceiroCategoriaGrupo> getAllGrupoCategoria(String campo, String valor);
}
