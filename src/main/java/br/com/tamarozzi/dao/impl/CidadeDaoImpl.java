package br.com.tamarozzi.dao.impl;

import br.com.tamarozzi.dao.CidadeDao;
import br.com.tamarozzi.http.HttpClientAPI;
import br.com.tamarozzi.model.Cidade;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.net.URISyntaxException;
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
public class CidadeDaoImpl implements CidadeDao {

    private final Gson gson;
    
    public CidadeDaoImpl() {
        this.gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).setPrettyPrinting().create();
    }
    
    @Override
    public void add(Cidade cidade) {
        String cidadeJson = gson.toJson(cidade);
        String response = HttpClientAPI.sendPost("api/v1/cidades", cidadeJson);
        
        if (response == null) {
            JOptionPane.showMessageDialog(null, "Houve um erro ao cadastrar a cidade");
        }
    }

    @Override
    public void edit(Cidade cidade) {
       
        String cidadeJson = gson.toJson(cidade);
        String response = HttpClientAPI.sendPut("api/v1/cidades/" + cidade.getCidadeCep(), cidadeJson);
        
        if (response == null) {
            JOptionPane.showMessageDialog(null, "Houve um erro ao editar a cidade");
        }
    }

    @Override
    public void delete(int cidadeId) {
        
        String response = HttpClientAPI.sendDelete("api/v1/cidades/" + cidadeId);
        
        if(response != null) {
            JOptionPane.showMessageDialog(null, "Houve um erro ao excluir o(s) registro(s)");
        }
    }

    @Override
    public Cidade getCidade(int cidadeId) {
        
        String response = HttpClientAPI.sendGet("api/v1/cidades/" + cidadeId);
        
        Cidade cidade = this.gson.fromJson(response, Cidade.class);
        
        return cidade;
    }

    @Override
    public List<Cidade> getAllCidade(String campo, String valor) {

        List<Cidade> cidades = null;
        
        try {
            URIBuilder builder = new URIBuilder("api/v1/cidades");
            
            if(!campo.equalsIgnoreCase("todos"))        { builder.addParameter(campo, valor);               }
                        
            String response = HttpClientAPI.sendGet(builder.toString());
            
            cidades = Arrays.asList(this.gson.fromJson(response, Cidade[].class));
        } catch (URISyntaxException ex) {
            Logger.getLogger(CidadeDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return cidades;
    }
}