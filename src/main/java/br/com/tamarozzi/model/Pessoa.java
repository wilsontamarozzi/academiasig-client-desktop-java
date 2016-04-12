/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tamarozzi.model;

import java.util.Date;

/**
 *
 * @author Panda
 */
public class Pessoa {

    private int id;
    private String nome;
    private String email;
    private String tipoPessoa;
    private String observacao;
    private String suporte;
    private boolean ativo;
    private PessoaFisica pessoaFisica;
    private PessoaJuridica pessoaJuridica;
    private Date dataCadastro;
    

    public Pessoa() {
    }

    public Pessoa(int id, String nome, String email, String tipoPessoa, String observacao, String suporte, boolean ativo, PessoaFisica pessoaFisica, PessoaJuridica pessoaJuridica, Date dataCadastro) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.tipoPessoa = tipoPessoa;
        this.observacao = observacao;
        this.suporte = suporte;
        this.ativo = ativo;
        this.pessoaFisica = pessoaFisica;
        this.pessoaJuridica = pessoaJuridica;
        this.dataCadastro = dataCadastro;
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

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getSuporte() {
        return suporte;
    }

    public void setSuporte(String suporte) {
        this.suporte = suporte;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public PessoaFisica getPessoaFisica() {
        return pessoaFisica;
    }

    public void setPessoaFisica(PessoaFisica pessoaFisica) {
        this.pessoaFisica = pessoaFisica;
    }

    public PessoaJuridica getPessoaJuridica() {
        return pessoaJuridica;
    }

    public void setPessoaJuridica(PessoaJuridica pessoaJuridica) {
        this.pessoaJuridica = pessoaJuridica;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
}
