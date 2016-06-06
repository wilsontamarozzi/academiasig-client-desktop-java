/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tamarozzi.dao.impl;

import br.com.tamarozzi.dao.UsuarioDao;
import br.com.tamarozzi.http.HttpClientAPI;
import br.com.tamarozzi.model.Pessoa;
import br.com.tamarozzi.model.Usuario;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.client.utils.URIBuilder;

/**
 *
 * @author Monde
 */
public class UsuarioDaoImpl implements UsuarioDao {

    private final Gson gson;
    
    public UsuarioDaoImpl() {
        this.gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).setPrettyPrinting().create();
    }
    
    @Override
    public boolean autenticacao(Usuario usuario) {
        return HttpClientAPI.autenticacao(usuario.getLogin(), usuario.getSenha());
    }

    @Override
    public Usuario getUsuario(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Pessoa> getAllUsuario() {
        List<Pessoa> usuarios = new ArrayList<>(0);
        
        try {
            URIBuilder builder = new URIBuilder("api/v1/usuarios");
            
            String response = HttpClientAPI.sendGet(builder.toString());
            
            if(response != null)            
                usuarios = Arrays.asList(this.gson.fromJson(response, Pessoa[].class));
        } catch (URISyntaxException ex) {
            Logger.getLogger(PessoaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return usuarios;
    }
}