package br.com.tamarozzi.dao.impl;

import br.com.tamarozzi.dao.LancamentoCategoriaDao;
import br.com.tamarozzi.http.HttpClientAPI;
import br.com.tamarozzi.model.LancamentoCategoria;
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
public class LancamentoCategoriaDaoImpl implements LancamentoCategoriaDao {

    private final static String QUERY = "api/v1/lancamento-categorias";
    
    private final Gson gson;
    
    public LancamentoCategoriaDaoImpl() {
        this.gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).setPrettyPrinting().create();
    }
    
    @Override
    public JSONObject add(LancamentoCategoria lancamentoCategoria) {
        String lancamentoCategoriaJson = gson.toJson(lancamentoCategoria);
        String response = HttpClientAPI.sendPost(QUERY, lancamentoCategoriaJson);
        
        LancamentoCategoria lancamentoCategoriaNew = this.gson.fromJson(response, LancamentoCategoria.class);
        lancamentoCategoria.setUUID(lancamentoCategoriaNew.getUUID());
        
        JSONObject errors = new JSONObject(response);
        
        return !errors.isNull("errors") ? errors.getJSONObject("errors") : null;
    }

    @Override
    public JSONObject edit(LancamentoCategoria lancamentoCategoria) {
       
        String lancamentoCategoriaJson = gson.toJson(lancamentoCategoria);
        String response = HttpClientAPI.sendPut(QUERY + "/" + lancamentoCategoria.getUUID(), lancamentoCategoriaJson);
        
        JSONObject errors = new JSONObject(response);
        
        return !errors.isNull("errors") ? errors.getJSONObject("errors") : null;
    }

    @Override
    public void delete(String lancamentoCategoriaUUID) {
        
        String response = HttpClientAPI.sendDelete(QUERY + "/" + lancamentoCategoriaUUID);
        
        if(response != null) {
            JOptionPane.showMessageDialog(null, "Houve um erro ao excluir o(s) registro(s)");
        }
    }

    @Override
    public LancamentoCategoria getLancamentoCategoria(LancamentoCategoria lancamentoCategoria) {
        
        String response = HttpClientAPI.sendGet(QUERY + "/" + lancamentoCategoria.getUUID());
        
        LancamentoCategoria lancamentoCategoriaNew = this.gson.fromJson(response, LancamentoCategoria.class);
        
        if(lancamentoCategoriaNew != null) {
            ClassMergeUtil.merge(lancamentoCategoria, lancamentoCategoriaNew);
        }
        
        return lancamentoCategoria;
    }

    @Override
    public List<LancamentoCategoria> getAllLancamentoCategoria(String campo, String valor) {

        List<LancamentoCategoria> lancamentoCategorias = new ArrayList<>(0);
        
        try {
            URIBuilder builder = new URIBuilder(QUERY);
            
            builder.addParameter(campo, valor);
            
            String response = HttpClientAPI.sendGet(builder.toString());
            
            if(response != null)
                lancamentoCategorias = Arrays.asList(this.gson.fromJson(response, LancamentoCategoria[].class));
        } catch (URISyntaxException ex) {
            Logger.getLogger(LancamentoCategoriaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return lancamentoCategorias;
    }
}
