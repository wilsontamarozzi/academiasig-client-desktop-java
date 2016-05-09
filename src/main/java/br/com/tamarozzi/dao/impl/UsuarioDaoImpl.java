/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tamarozzi.dao.impl;

import br.com.tamarozzi.dao.UsuarioDao;
import br.com.tamarozzi.http.HttpClientAPI;
import br.com.tamarozzi.model.Usuario;
import java.util.List;

/**
 *
 * @author Monde
 */
public class UsuarioDaoImpl implements UsuarioDao {

    @Override
    public boolean autenticacao(Usuario usuario) {
        return HttpClientAPI.autenticacao(usuario.getLogin(), usuario.getSenha());
    }

    @Override
    public Usuario getUsuario(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Usuario> getAllUsuario() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}