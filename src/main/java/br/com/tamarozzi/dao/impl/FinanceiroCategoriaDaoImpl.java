package br.com.tamarozzi.dao.impl;

import br.com.tamarozzi.http.HttpClientAPI;
import br.com.tamarozzi.model.FinanceiroCategoria;
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
import br.com.tamarozzi.dao.FinanceiroCategoriaDao;

/**
 *
 * @author Panda
 */
public class FinanceiroCategoriaDaoImpl implements FinanceiroCategoriaDao {

    private final static String QUERY = "api/v1/financeiro-categorias";
    
    private final Gson gson;
    
    public FinanceiroCategoriaDaoImpl() {
        this.gson = new GsonBuilder().
            setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").
            setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).
            setPrettyPrinting().
            create();
    }
    
    @Override
    public JSONObject add(FinanceiroCategoria categoria) {
        
        String categoriaJson = gson.toJson(categoria);
        String response = HttpClientAPI.sendPost(QUERY, categoriaJson);
        
        FinanceiroCategoria categoriaNew = this.gson.fromJson(response, FinanceiroCategoria.class);
        categoria.setUUID(categoriaNew.getUUID());
        
        JSONObject errors = new JSONObject(response);
        
        return !errors.isNull("errors") ? errors.getJSONObject("errors") : null;
    }

    @Override
    public JSONObject edit(FinanceiroCategoria categoria) {
       
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
    public FinanceiroCategoria get(FinanceiroCategoria categoria) {
        
        String response = HttpClientAPI.sendGet(QUERY + "/" + categoria.getUUID());
        
        FinanceiroCategoria categoriaNew = this.gson.fromJson(response, FinanceiroCategoria.class);
        
        if(categoriaNew != null) {
            ClassMergeUtil.merge(categoria, categoriaNew);
        }
        
        return categoria;
    }

    @Override
    public List<FinanceiroCategoria> getAll(String campo, String valor) {

        List<FinanceiroCategoria> categorias = new ArrayList<>(0);

        try {
            URIBuilder builder = new URIBuilder(QUERY);
            
            builder.addParameter(campo, valor);
            
            String response = HttpClientAPI.sendGet(builder.toString());
            
            if(response != null)
                categorias = Arrays.asList(this.gson.fromJson(response, FinanceiroCategoria[].class));
        } catch (URISyntaxException ex) {
            Logger.getLogger(FinanceiroCategoriaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return categorias;
    }
}
