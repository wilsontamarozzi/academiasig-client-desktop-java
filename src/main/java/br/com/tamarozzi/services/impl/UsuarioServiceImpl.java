/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tamarozzi.services.impl;

import br.com.tamarozzi.dao.UsuarioDao;
import br.com.tamarozzi.dao.impl.UsuarioDaoImpl;
import br.com.tamarozzi.model.Usuario;
import br.com.tamarozzi.services.UsuarioService;
import java.util.List;

/**
 *
 * @author Monde
 */
public class UsuarioServiceImpl implements UsuarioService{

    private final UsuarioDao usuarioDao;

    public UsuarioServiceImpl() {
        this.usuarioDao = new UsuarioDaoImpl();
    }
    
    @Override
    public boolean autenticacao(Usuario usuario) {
        return usuarioDao.autenticacao(usuario);
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
