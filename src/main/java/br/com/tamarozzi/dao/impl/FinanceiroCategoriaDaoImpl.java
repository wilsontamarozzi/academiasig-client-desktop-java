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
        this.gson = new GsonBuilder().
            setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").
            setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).
            setPrettyPrinting().
            create();
    }
    
    @Override
    public JSONObject add(LancamentoCategoria categoria) {
        
        String categoriaJson = gson.toJson(categoria);
        String response = HttpClientAPI.sendPost(QUERY, categoriaJson);
        
        LancamentoCategoria categoriaNew = this.gson.fromJson(response, LancamentoCategoria.class);
        categoria.setUUID(categoriaNew.getUUID());
        
        JSONObject errors = new JSONObject(response);
        
        return !errors.isNull("errors") ? errors.getJSONObject("errors") : null;
    }

    @Override
    public JSONObject edit(LancamentoCategoria categoria) {
       
        String categoriaJson = gson.toJson(categoria);
        String response = HttpClientAPI.sendPut(QUERY + "/" + categoria.getUUID(), categoriaJson);
        
        JSONObject errors = new JSONObject(response);
        
        return !errors.isNull("errors") ? errors.getJSONObject("errors") : null;
    }

    @Override
    public void delete(String categoriaUUID) {
        
        String response = HttpClientAPI.sendDelete(QUERY + "/" + categoriaUUID);
        
        if(response != null) {
            JOptionPane.showMessageDialog(null, "Houve um erro ao excluir o(s) registro(s)");
        }
    }

    @Override
    public LancamentoCategoria get(LancamentoCategoria categoria) {
        
        String response = HttpClientAPI.sendGet(QUERY + "/" + categoria.getUUID());
        
        LancamentoCategoria categoriaNew = this.gson.fromJson(response, LancamentoCategoria.class);
        
        if(categoriaNew != null) {
            ClassMergeUtil.merge(categoria, categoriaNew);
        }
        
        return categoria;
    }

    @Override
    public List<LancamentoCategoria> getAll(String campo, String valor) {

        List<LancamentoCategoria> categorias = new ArrayList<>(0);

        try {
            URIBuilder builder = new URIBuilder(QUERY);
            
            builder.addParameter(campo, valor);
            
            String response = HttpClientAPI.sendGet(builder.toString());
            
            if(response != null)
                categorias = Arrays.asList(this.gson.fromJson(response, LancamentoCategoria[].class));
        } catch (URISyntaxException ex) {
            Logger.getLogger(LancamentoCategoriaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return categorias;
    }
}
