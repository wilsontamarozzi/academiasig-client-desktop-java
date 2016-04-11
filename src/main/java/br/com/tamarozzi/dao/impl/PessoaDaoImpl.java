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
import org.apache.http.client.utils.URIBuilder;

/**
 *
 * @author Panda
 */
public class PessoaDaoImpl implements PessoaDao {

    @Override
    public void add(Pessoa pessoa) {
    }

    @Override
    public void edit(Pessoa pessoa) {
    }

    @Override
    public void delete(int pessoaId) {
    }

    @Override
    public Pessoa getPessoa(int pessoaId) {
        return null;
    }

    @Override
    public List<Pessoa> getAllPessoa(String valor, String campo, String tipoPessoa) {

        List<Pessoa> pessoas = null;
        
        try {
            URIBuilder builder = new URIBuilder("v1/pessoas/lista.json");
            
            if(!campo.equalsIgnoreCase("todos"))        { builder.addParameter(campo, valor);               }
            if(!tipoPessoa.equalsIgnoreCase("todos"))   { builder.addParameter("tipo_pessoa", tipoPessoa);  }
            
            String response = HttpClientAPI.sendGet(builder.toString());
            
            Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).setPrettyPrinting().create();
            pessoas = Arrays.asList(gson.fromJson(response, Pessoa[].class));
        } catch (URISyntaxException ex) {
            Logger.getLogger(PessoaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return pessoas;
    }
}
