package br.com.tamarozzi.dao.impl;

import br.com.tamarozzi.dao.BancoDao;
import br.com.tamarozzi.http.HttpClientAPI;
import br.com.tamarozzi.model.Banco;
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
public class BancoDaoImpl implements BancoDao {

    private final Gson gson;
    
    public BancoDaoImpl() {
        this.gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).setPrettyPrinting().create();
    }
    
    @Override
    public JSONObject add(Banco banco) {
        String bancoJson = gson.toJson(banco);
        String response = HttpClientAPI.sendPost("api/v1/bancos", bancoJson);
        
        Banco bancoNew = this.gson.fromJson(response, Banco.class);
        banco.setUUID(bancoNew.getUUID());
        
        JSONObject errors = new JSONObject(response);
        
        return !errors.isNull("errors") ? errors.getJSONObject("errors") : null;
    }

    @Override
    public JSONObject edit(Banco banco) {
       
        String bancoJson = gson.toJson(banco);
        String response = HttpClientAPI.sendPut("api/v1/bancos/" + banco.getUUID(), bancoJson);
        
        JSONObject errors = new JSONObject(response);
        
        return !errors.isNull("errors") ? errors.getJSONObject("errors") : null;
    }

    @Override
    public void delete(String bancoUUID) {
        
        String response = HttpClientAPI.sendDelete("api/v1/bancos/" + bancoUUID);
        
        if(response != null) {
            JOptionPane.showMessageDialog(null, "Houve um erro ao excluir o(s) registro(s)");
        }
    }

    @Override
    public Banco getBanco(Banco banco) {
        
        String response = HttpClientAPI.sendGet("api/v1/bancos/" + banco.getUUID());
        
        Banco bancoNew = this.gson.fromJson(response, Banco.class);
        
        if(bancoNew != null) {
            ClassMergeUtil.merge(banco, bancoNew);
        }
        
        return banco;
    }

    @Override
    public List<Banco> getAllBanco(String campo, String valor) {

        List<Banco> bancos = new ArrayList<>(0);
        
        try {
            URIBuilder builder = new URIBuilder("api/v1/bancos");
            
            builder.addParameter(campo, valor);
            
            String response = HttpClientAPI.sendGet(builder.toString());
            
            if(response != null)
                bancos = Arrays.asList(this.gson.fromJson(response, Banco[].class));
        } catch (URISyntaxException ex) {
            Logger.getLogger(BancoDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return bancos;
    }
}
