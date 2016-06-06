package br.com.tamarozzi.dao.impl;

import br.com.tamarozzi.dao.TarefaDao;
import br.com.tamarozzi.http.HttpClientAPI;
import br.com.tamarozzi.model.Tarefa;
import br.com.tamarozzi.util.ClassMergeUtil;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.apache.http.client.utils.URIBuilder;
import org.json.JSONObject;

/**
 *
 * @author Panda
 */
public class TarefaDaoImpl implements TarefaDao {

    private final Gson gson;
    
    public TarefaDaoImpl() {
        this.gson = new GsonBuilder().
            //setDateFormat("yyyy-MM-dd HH:mm:ss").
            setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").
            setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).
            setPrettyPrinting().
            create();
    }
    
    @Override
    public JSONObject add(Tarefa tarefa) {
        String tarefaJson = gson.toJson(tarefa);
        String response = HttpClientAPI.sendPost("api/v1/tarefas", tarefaJson);
        
        Tarefa tarefaNew = this.gson.fromJson(response, Tarefa.class);
        tarefa.setUUID(tarefaNew.getUUID());
        
        tarefa.setDataCadastro(new Date());
        
        JSONObject errors = new JSONObject(response);
        
        return !errors.isNull("errors") ? errors.getJSONObject("errors") : null;
    }

    @Override
    public JSONObject edit(Tarefa tarefa) {
       
        String tarefaJson = gson.toJson(tarefa);
        String response = HttpClientAPI.sendPut("api/v1/tarefas/" + tarefa.getUUID(), tarefaJson);
        
        JSONObject errors = new JSONObject(response);
        
        return !errors.isNull("errors") ? errors.getJSONObject("errors") : null;
    }

    @Override
    public void delete(String tarefaUUID) {
        
        String response = HttpClientAPI.sendDelete("api/v1/tarefas/" + tarefaUUID);
        
        if(response != null) {
            JOptionPane.showMessageDialog(null, "Houve um erro ao excluir o(s) registro(s)");
        }
    }

    @Override
    public Tarefa getTarefa(Tarefa tarefa) {
        
        String response = HttpClientAPI.sendGet("api/v1/tarefas/" + tarefa.getUUID());
        
        Tarefa tarefaNew = this.gson.fromJson(response, Tarefa.class);
        
        if(tarefaNew != null) {
            ClassMergeUtil.merge(tarefa, tarefaNew);
        }
        
        return tarefa;
    }

    @Override
    public List<Tarefa> getAllTarefa(String campo, String valor, String situacao, String responsavelUUID, String categoriaUUID, String tipoData, Date dataInicio, Date dataFim) {

        List<Tarefa> tarefas = new ArrayList<>(0);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        
        /*
        System.out.println(
                "Responsavel: " + responsavelUUID + "\n" +
                "Categoria: " + categoriaUUID + "\n" + 
                "Data Inicio: " + dataInicio + "\n" + 
                "Data Fim: " + dataFim + "\n"
        );
        */        
        
        try {
            URIBuilder builder = new URIBuilder("api/v1/tarefas");
            
            builder.addParameter(campo, valor);
            
            if(situacao != null) { 
                builder.addParameter("concluida", situacao); 
            }
            
            if(responsavelUUID != null) {
                builder.addParameter("responsavel", responsavelUUID);
            }
            
            if(categoriaUUID != null) {
                builder.addParameter("categoria", categoriaUUID);
            }
            
            if(tipoData != null) {
                builder.addParameter("tipoData", tipoData);
            }
            
            if(dataInicio != null) {
                builder.addParameter("dataInicio", dateFormat.format(dataInicio));
            }
            
            if(dataFim != null) {
                builder.addParameter("dataFim", dateFormat.format(dataFim));
            }
            
            String response = HttpClientAPI.sendGet(builder.toString());
            
            if(response != null)
                tarefas = Arrays.asList(this.gson.fromJson(response, Tarefa[].class));
        } catch (URISyntaxException ex) {
            Logger.getLogger(TarefaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return tarefas;
    }
}
