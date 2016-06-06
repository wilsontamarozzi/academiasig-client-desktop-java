package br.com.tamarozzi.dao;

import java.util.List;

import br.com.tamarozzi.model.TarefaCategoria;
import org.json.JSONObject;

public interface TarefaCategoriaDao {

    public JSONObject add(TarefaCategoria tarefaCategoria);

    public JSONObject edit(TarefaCategoria tarefaCategoria);

    public void delete(String tarefaUUID);
    
    public TarefaCategoria getCategoria(TarefaCategoria tarefaCategoria);

    public List<TarefaCategoria> getAllCategoria(String campo, String valor);
}
