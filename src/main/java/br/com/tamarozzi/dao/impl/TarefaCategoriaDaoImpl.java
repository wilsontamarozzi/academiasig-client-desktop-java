package br.com.tamarozzi.dao.impl;

import br.com.tamarozzi.dao.TarefaCategoriaDao;
import br.com.tamarozzi.http.HttpClientAPI;
import br.com.tamarozzi.model.TarefaCategoria;
import br.com.tamarozzi.util.ClassMergeUtil;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
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
public class TarefaCategoriaDaoImpl implements TarefaCategoriaDao {

    private final Gson gson;
    
    public TarefaCategoriaDaoImpl() {
        this.gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).setPrettyPrinting().create();
    }
    
    @Override
    public JSONObject add(TarefaCategoria tarefaCategoria) {
        String tarefaCategoriaJson = gson.toJson(tarefaCategoria);
        String response = HttpClientAPI.sendPost("api/v1/tarefa-categorias", tarefaCategoriaJson);
        
        TarefaCategoria tarefaCategoriaNew = this.gson.fromJson(response, TarefaCategoria.class);
        tarefaCategoria.setUUID(tarefaCategoriaNew.getUUID());
        
        JSONObject errors = new JSONObject(response);
        
        return !errors.isNull("errors") ? errors.getJSONObject("errors") : null;
    }

    @Override
    public JSONObject edit(TarefaCategoria tarefaCategoria) {
       
        String tarefaCategoriaJson = gson.toJson(tarefaCategoria);
        String response = HttpClientAPI.sendPut("api/v1/tarefa-categorias/" + tarefaCategoria.getUUID(), tarefaCategoriaJson);
        
        JSONObject errors = new JSONObject(response);
        
        return !errors.isNull("errors") ? errors.getJSONObject("errors") : null;
    }

    @Override
    public void delete(String tarefaCategoriaUUID) {
        
        String response = HttpClientAPI.sendDelete("api/v1/tarefa-categorias/" + tarefaCategoriaUUID);
        
        if(response != null) {
            JOptionPane.showMessageDialog(null, "Houve um erro ao excluir o(s) registro(s)");
        }
    }

    @Override
    public TarefaCategoria getCategoria(TarefaCategoria tarefaCategoria) {
        
        String response = HttpClientAPI.sendGet("api/v1/tarefa-categorias/" + tarefaCategoria.getUUID());
        
        TarefaCategoria tarefaCategoriaNew = this.gson.fromJson(response, TarefaCategoria.class);
        
        if(tarefaCategoriaNew != null) {
            ClassMergeUtil.merge(tarefaCategoria, tarefaCategoriaNew);
        }
        
        return tarefaCategoria;
    }

    @Override
    public List<TarefaCategoria> getAllCategoria(String campo, String valor) {

        List<TarefaCategoria> tarefaCategorias = new ArrayList<>(0);
        
        try {
            URIBuilder builder = new URIBuilder("api/v1/tarefa-categorias");
            
            builder.addParameter(campo, valor);
            
            String response = HttpClientAPI.sendGet(builder.toString());
            
            if(response != null)
                tarefaCategorias = Arrays.asList(this.gson.fromJson(response, TarefaCategoria[].class));
        } catch (URISyntaxException ex) {
            Logger.getLogger(TarefaCategoriaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return tarefaCategorias;
    }
}
