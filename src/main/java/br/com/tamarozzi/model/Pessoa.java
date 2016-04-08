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
public class Pessoa {

    private int id;
    private String nome;
    private String email;
    private String tipoPessoa;
    private PessoaFisica pessoaFisica;

    public Pessoa() {
    }

    public Pessoa(int id, String nome, String email, String tipoPessoa, PessoaFisica pessoaFisica) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.tipoPessoa = tipoPessoa;
        this.pessoaFisica = pessoaFisica;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTipoPessoa() {
        return tipoPessoa;
    }

    public void setTipoPessoa(String tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }

    public PessoaFisica getPessoaFisica() {
        return pessoaFisica;
    }

    public void setPessoaFisica(PessoaFisica pessoaFisica) {
        this.pessoaFisica = pessoaFisica;
    }
}
