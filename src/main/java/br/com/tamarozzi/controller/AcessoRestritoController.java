/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tamarozzi.controller;

import br.com.tamarozzi.model.Usuario;
import br.com.tamarozzi.services.UsuarioService;
import br.com.tamarozzi.services.impl.UsuarioServiceImpl;

/**
 *
 * @author Monde
 */
public class AcessoRestritoController {

    private final UsuarioService usuarioService;

    public AcessoRestritoController() {
        this.usuarioService = new UsuarioServiceImpl();
    }

    public boolean autenticacao(String login, String senha) {
        Usuario user = new Usuario();

        user.setLogin(login);
        user.setSenha(senha);

        return this.usuarioService.autenticacao(user);
    }
}
