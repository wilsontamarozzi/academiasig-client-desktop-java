/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tamarozzi.model;

import br.com.tamarozzi.util.MD5EncodeUtil;

/**
 *
 * @author Monde
 */
public class Usuario {

    private String UUID;
    private String pessoaUUID;
    private String nome;
    private String login;
    private String senha;

    public Usuario() {
    }

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public String getPessoaUUID() {
        return pessoaUUID;
    }

    public void setPessoaUUID(String pessoaUUID) {
        this.pessoaUUID = pessoaUUID;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    public boolean alterarSenha(String novaSenha, String senhaRepetida) {
        
        if(novaSenha.equals(senhaRepetida)) {
            this.senha = MD5EncodeUtil.encode(novaSenha);
            return true;
        }
        
        return false;
    }
}