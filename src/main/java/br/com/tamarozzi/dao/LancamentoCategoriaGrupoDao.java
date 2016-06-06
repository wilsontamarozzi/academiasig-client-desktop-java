package br.com.tamarozzi.dao;

import br.com.tamarozzi.model.LancamentoCategoriaGrupo;
import java.util.List;

import org.json.JSONObject;

public interface LancamentoCategoriaGrupoDao {

    public JSONObject add(LancamentoCategoriaGrupo grupo);

    public JSONObject edit(LancamentoCategoriaGrupo grupo);

    public void delete(String grupoUUID);
    
    public LancamentoCategoriaGrupo getGrupoCategoria(LancamentoCategoriaGrupo grupo);

    public List<LancamentoCategoriaGrupo> getAllGrupoCategoria(String campo, String valor);
}
