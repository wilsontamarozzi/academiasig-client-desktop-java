package br.com.tamarozzi.dao.impl;

import br.com.tamarozzi.http.HttpClientAPI;
import br.com.tamarozzi.model.FinanceiroCategoriaGrupo;
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
import br.com.tamarozzi.dao.FinanceiroCategoriaGrupoDao;

/**
 *
 * @author Panda
 */
public class FinanceiroCategoriaGrupoDaoImpl implements FinanceiroCategoriaGrupoDao {

    private final static String QUERY = "api/v1/financeiro-categoria-grupos";
    
    private final Gson gson;
    
    public FinanceiroCategoriaGrupoDaoImpl() {
        this.gson = new GsonBuilder().
            setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").
            setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).
            setPrettyPrinting().
            create();
    }
    
    @Override
    public JSONObject add(FinanceiroCategoriaGrupo grupoCategoria) {
        
        String grupoCategoriaJson = gson.toJson(grupoCategoria);
        String response = HttpClientAPI.sendPost(QUERY, grupoCategoriaJson);
        
        FinanceiroCategoriaGrupo grupoCategoriaNew = this.gson.fromJson(response, FinanceiroCategoriaGrupo.class);
        grupoCategoria.setUUID(grupoCategoriaNew.getUUID());
        
        JSONObject errors = new JSONObject(response);
        
        return !errors.isNull("errors") ? errors.getJSONObject("errors") : null;
    }

    @Override
    public JSONObject edit(FinanceiroCategoriaGrupo grupoCategoria) {
       
        String grupoCategoriaJson = gson.toJson(grupoCategoria);
        String response = HttpClientAPI.sendPut(QUERY + "/" + grupoCategoria.getUUID(), grupoCategoriaJson);
        
        JSONObject errors = new JSONObject(response);
        
        return !errors.isNull("errors") ? errors.getJSONObject("errors") : null;
    }

    @Override
    public void delete(String grupoCategoriaUUID) {
        
        String response = HttpClientAPI.sendDelete(QUERY + "/" + grupoCategoriaUUID);
        
        if(response != null) {
            JOptionPane.showMessageDialog(null, "Houve um erro ao excluir o(s) registro(s)");
        }
    }

    @Override
    public FinanceiroCategoriaGrupo getGrupoCategoria(FinanceiroCategoriaGrupo grupoCategoria) {
        
        String response = HttpClientAPI.sendGet(QUERY + "/" + grupoCategoria.getUUID());
        
        FinanceiroCategoriaGrupo grupoCategoriaNew = this.gson.fromJson(response, FinanceiroCategoriaGrupo.class);
        
        if(grupoCategoriaNew != null) {
            ClassMergeUtil.merge(grupoCategoria, grupoCategoriaNew);
        }
        
        return grupoCategoria;
    }

    @Override
    public List<FinanceiroCategoriaGrupo> getAllGrupoCategoria(String campo, String valor) {

        List<FinanceiroCategoriaGrupo> gruposCategoria = new ArrayList<>(0);

        try {
            URIBuilder builder = new URIBuilder(QUERY);
            
            builder.addParameter(campo, valor);
            
            String response = HttpClientAPI.sendGet(builder.toString());
            
            if(response != null)
                gruposCategoria = Arrays.asList(this.gson.fromJson(response, FinanceiroCategoriaGrupo[].class));
        } catch (URISyntaxException ex) {
            Logger.getLogger(FinanceiroCategoriaGrupoDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return gruposCategoria;
    }
}
