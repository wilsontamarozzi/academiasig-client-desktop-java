package br.com.tamarozzi.dao.impl;

import br.com.tamarozzi.dao.LogradouroDao;
import br.com.tamarozzi.http.HttpClientAPI;
import br.com.tamarozzi.model.Logradouro;
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

/**
 *
 * @author Panda
 */
public class LogradouroDaoImpl implements LogradouroDao {

    private final Gson gson;
    
    public LogradouroDaoImpl() {
        this.gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).setPrettyPrinting().create();
    }
    
    @Override
    public void add(Logradouro logradouro) {
        String logradouroJson = gson.toJson(logradouro);
        String response = HttpClientAPI.sendPost("api/v1/logradouros", logradouroJson);
        
        if (response == null) {
            JOptionPane.showMessageDialog(null, "Houve um erro ao cadastrar a logradouro");
        }
    }

    @Override
    public void edit(Logradouro logradouro) {
       
        String logradouroJson = gson.toJson(logradouro);
        String response = HttpClientAPI.sendPut("api/v1/logradouros/" + logradouro.getEnderecoCep(), logradouroJson);
        
        if (response == null) {
            JOptionPane.showMessageDialog(null, "Houve um erro ao editar a logradouro");
        }
    }

    @Override
    public void delete(int logradouroId) {
        
        String response = HttpClientAPI.sendDelete("api/v1/logradouros/" + logradouroId);
        
        if(response != null) {
            JOptionPane.showMessageDialog(null, "Houve um erro ao excluir o(s) registro(s)");
        }
    }

    @Override
    public Logradouro getLogradouroByCEP(String logradouroCEP) {
        
        String response = HttpClientAPI.sendGet("api/v1/logradouros/" + logradouroCEP);
        
        Logradouro logradouro = this.gson.fromJson(response, Logradouro.class);
        
        return logradouro;
    }

    @Override
    public List<Logradouro> getAllLogradouro(String campo, String valor) {

        List<Logradouro> logradouros = new ArrayList<>(0);
        
        try {
            URIBuilder builder = new URIBuilder("api/v1/logradouros");
            
            if(!campo.equalsIgnoreCase("todos"))        { builder.addParameter(campo, valor);               }
                        
            String response = HttpClientAPI.sendGet(builder.toString());
            
            if(response != null)
                logradouros = Arrays.asList(this.gson.fromJson(response, Logradouro[].class));
        } catch (URISyntaxException ex) {
            Logger.getLogger(LogradouroDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return logradouros;
    }
}