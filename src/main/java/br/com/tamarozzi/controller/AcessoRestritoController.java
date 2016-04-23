/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tamarozzi.controller;

import br.com.tamarozzi.dao.UsuarioDao;
import br.com.tamarozzi.dao.impl.UsuarioDaoImpl;
import br.com.tamarozzi.model.Usuario;

/**
 *
 * @author Monde
 */
public class AcessoRestritoController {

    private final UsuarioDao usuarioDao;

    public AcessoRestritoController() {
        this.usuarioDao = new UsuarioDaoImpl();
    }

    public boolean autenticacao(String login, String senha) {
        Usuario user = new Usuario();

        user.setLogin(login);
        user.setSenha(senha);

        return this.usuarioDao.autenticacao(user);
    }
}
