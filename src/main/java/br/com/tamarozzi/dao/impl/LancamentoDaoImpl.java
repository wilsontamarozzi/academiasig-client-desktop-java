package br.com.tamarozzi.dao.impl;

import br.com.tamarozzi.dao.LancamentoDao;
import br.com.tamarozzi.http.HttpClientAPI;
import br.com.tamarozzi.model.Lancamento;
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
public class LancamentoDaoImpl implements LancamentoDao {

    private final static String QUERY = "api/v1/lancamentos";
    
    private final Gson gson;
    
    public LancamentoDaoImpl() {
        this.gson = new GsonBuilder().
            setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").
            setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).
            setPrettyPrinting().
            create();
    }
    
    @Override
    public JSONObject add(Lancamento lancamento) {
        String lancamentoJson = gson.toJson(lancamento);
        String response = HttpClientAPI.sendPost(QUERY, lancamentoJson);
        
        Lancamento lancamentoNew = this.gson.fromJson(response, Lancamento.class);
        lancamento.setUUID(lancamentoNew.getUUID());
        
        JSONObject errors = new JSONObject(response);
        
        return !errors.isNull("errors") ? errors.getJSONObject("errors") : null;
    }

    @Override
    public JSONObject edit(Lancamento lancamento) {
       
        String lancamentoJson = gson.toJson(lancamento);
        String response = HttpClientAPI.sendPut(QUERY + "/" + lancamento.getUUID(), lancamentoJson);
        
        JSONObject errors = new JSONObject(response);
        
        return !errors.isNull("errors") ? errors.getJSONObject("errors") : null;
    }

    @Override
    public void delete(String lancamentoUUID) {
        
        String response = HttpClientAPI.sendDelete(QUERY + "/" + lancamentoUUID);
        
        if(response != null) {
            JOptionPane.showMessageDialog(null, "Houve um erro ao excluir o(s) registro(s)");
        }
    }

    @Override
    public Lancamento get(Lancamento lancamento) {
        
        String response = HttpClientAPI.sendGet(QUERY + "/" + lancamento.getUUID());
        
        Lancamento lancamentoNew = this.gson.fromJson(response, Lancamento.class);
        
        if(lancamentoNew != null) {
            ClassMergeUtil.merge(lancamento, lancamentoNew);
        }
        
        return lancamento;
    }

    @Override
    public List<Lancamento> getAll(String campo, String valor) {

        List<Lancamento> lancamentos = new ArrayList<>(0);
        
        try {
            URIBuilder builder = new URIBuilder(QUERY);
            
            builder.addParameter(campo, valor);
            
            String response = HttpClientAPI.sendGet(builder.toString());
            
            if(response != null)
                lancamentos = Arrays.asList(this.gson.fromJson(response, Lancamento[].class));
        } catch (URISyntaxException ex) {
            Logger.getLogger(LancamentoDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return lancamentos;
    }
}
