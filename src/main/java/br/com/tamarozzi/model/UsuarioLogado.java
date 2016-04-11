/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tamarozzi.model;

/**
 *
 * @author Panda
 */
public final class UsuarioLogado {
    
    private static Pessoa INSTANCE = new Pessoa();
    
    public static Pessoa getInstance() {
        return INSTANCE;
    }
    
    public static void setInstance(Pessoa p) {
        INSTANCE = p;
    }
}
