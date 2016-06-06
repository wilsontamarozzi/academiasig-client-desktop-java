package br.com.tamarozzi.dao;

import java.util.List;

import br.com.tamarozzi.model.Tarefa;
import java.util.Date;
import org.json.JSONObject;

public interface TarefaDao {

    public JSONObject add(Tarefa tarefa);

    public JSONObject edit(Tarefa tarefa);

    public void delete(String tarefaUUID);
    
    public Tarefa getTarefa(Tarefa tarefa);

    public List<Tarefa> getAllTarefa(String campo, String valor, String situacao, String responsavelUUID, String categoriaUUID, String tipoData, Date dataInicio, Date dataFim);
}
