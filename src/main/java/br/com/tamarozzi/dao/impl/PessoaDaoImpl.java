package br.com.tamarozzi.dao.impl;

import br.com.tamarozzi.dao.PessoaDao;
import br.com.tamarozzi.http.HttpClientAPI;
import br.com.tamarozzi.model.Pessoa;
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
public class PessoaDaoImpl implements PessoaDao {

    private final Gson gson;
    
    public PessoaDaoImpl() {
        this.gson = new GsonBuilder().
            //setDateFormat(DateFormat.FULL, DateFormat.FULL).
            setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").
            setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).
            setPrettyPrinting().
            create();
    }
    
    @Override
    public JSONObject add(Pessoa pessoa) {
        String pessoaJson = gson.toJson(pessoa);
        String response = HttpClientAPI.sendPost("api/v1/pessoas", pessoaJson);
        
        Pessoa pessoaNew = this.gson.fromJson(response, Pessoa.class);
        pessoa.setUUID(pessoaNew.getUUID());
        
        JSONObject errors = new JSONObject(response);
        
        return !errors.isNull("errors") ? errors.getJSONObject("errors") : null;
    }

    @Override
    public JSONObject edit(Pessoa pessoa) {
       
        String pessoaJson = gson.toJson(pessoa);
        String response = HttpClientAPI.sendPut("api/v1/pessoas/" + pessoa.getUUID(), pessoaJson);
        
        JSONObject errors = new JSONObject(response);
        
        return !errors.isNull("errors") ? errors.getJSONObject("errors") : null;
    }

    @Override
    public void delete(String pessoaId) {
        
        String response = HttpClientAPI.sendDelete("api/v1/pessoas/" + pessoaId);
        
        if(response != null) {
            JOptionPane.showMessageDialog(null, "Houve um erro ao excluir o(s) registro(s)");
        }
    }

    @Override
    public Pessoa getPessoa(Pessoa pessoa) {
        
        String response = HttpClientAPI.sendGet("api/v1/pessoas/" + pessoa.getUUID());
        
        Pessoa pessoaNew = this.gson.fromJson(response, Pessoa.class);
        
        if(pessoaNew != null) {
            ClassMergeUtil.merge(pessoa, pessoaNew);
        }
        
        return pessoa;
    }

    @Override
    public List<Pessoa> getAllPessoa(String campo, String valor, String tipoPessoa, String situacao) {

        List<Pessoa> pessoas = new ArrayList<>(0);
        
        try {
            URIBuilder builder = new URIBuilder("api/v1/pessoas");

            builder.addParameter(campo, valor);
            
            if(tipoPessoa != null) { builder.addParameter("tipo_pessoa", tipoPessoa); }
            if(situacao != null) { builder.addParameter("ativo", situacao); }
            
            String response = HttpClientAPI.sendGet(builder.toString());
            
            if(response != null)            
                pessoas = Arrays.asList(this.gson.fromJson(response, Pessoa[].class));
        } catch (URISyntaxException ex) {
            Logger.getLogger(PessoaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return pessoas;
    }
}
