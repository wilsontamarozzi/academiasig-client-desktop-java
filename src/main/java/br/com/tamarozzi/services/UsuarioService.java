/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tamarozzi.services;

import br.com.tamarozzi.model.Usuario;
import java.util.List;

/**
 *
 * @author Monde
 */
public interface UsuarioService {

    public boolean autenticacao(Usuario usuario);

    public Usuario getUsuario(int id);

    public List<Usuario> getAllUsuario();
}