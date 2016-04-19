package br.com.tamarozzi.dao.impl;

import br.com.tamarozzi.dao.PessoaDao;
import br.com.tamarozzi.http.HttpClientAPI;
import br.com.tamarozzi.model.Pessoa;
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
public class PessoaDaoImpl implements PessoaDao {

    private final Gson gson;
    
    public PessoaDaoImpl() {
        this.gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).setPrettyPrinting().create();
    }
    
    @Override
    public void add(Pessoa pessoa) {
        String pessoaJson = gson.toJson(pessoa);
        String response = HttpClientAPI.sendPost("api/v1/pessoas", pessoaJson);
        
        if (response == null) {
            JOptionPane.showMessageDialog(null, "Houve um erro ao cadastrar a pessoa");
        }
    }

    @Override
    public void edit(Pessoa pessoa) {
       
        String pessoaJson = gson.toJson(pessoa);
        String response = HttpClientAPI.sendPut("api/v1/pessoas/" + pessoa.getId(), pessoaJson);
        
        if (response == null) {
            JOptionPane.showMessageDialog(null, "Houve um erro ao editar a pessoa");
        }
    }

    @Override
    public void delete(int pessoaId) {
        
        String response = HttpClientAPI.sendDelete("api/v1/pessoas/" + pessoaId);
        
        if(response != null) {
            JOptionPane.showMessageDialog(null, "Houve um erro ao excluir o(s) registro(s)");
        }
    }

    @Override
    public Pessoa getPessoa(int pessoaId) {
        
        String response = HttpClientAPI.sendGet("api/v1/pessoas/" + pessoaId);
        
        Pessoa pessoa = this.gson.fromJson(response, Pessoa.class);
        
        return pessoa;
    }

    @Override
    public List<Pessoa> getAllPessoa(String campo, String valor, String tipoPessoa, String situacao) {

        List<Pessoa> pessoas = null;
        
        try {
            URIBuilder builder = new URIBuilder("api/v1/pessoas");
            
            if(!campo.equalsIgnoreCase("todos"))        { builder.addParameter(campo, valor);               }
            if(!tipoPessoa.equalsIgnoreCase("todos"))   { builder.addParameter("tipo_pessoa", tipoPessoa);  }
            if(!situacao.equalsIgnoreCase("todos"))     { builder.addParameter("ativo", situacao);          }
            
            String response = HttpClientAPI.sendGet(builder.toString());
            
            pessoas = Arrays.asList(this.gson.fromJson(response, Pessoa[].class));
        } catch (URISyntaxException ex) {
            Logger.getLogger(PessoaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return pessoas;
    }
}
