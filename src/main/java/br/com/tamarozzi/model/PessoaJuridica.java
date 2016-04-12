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
public class PessoaJuridica extends Pessoa {
    
    private boolean empresaSistema;
    private String razaoSocial;
    private String cnpj;
    private String telefoneComercial;
    private String fax;
    private String website;
    private String inscricaoMunicipal;
    private String inscricaoEstadual;
    
    public PessoaJuridica() {
        
    }

    public PessoaJuridica(boolean empresaSistema, String razaoSocial, String cnpj, String telefoneComercial, String fax, String website, String inscricaoMunicipal, String inscricaoEstadual) {
        this.empresaSistema = empresaSistema;
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.telefoneComercial = telefoneComercial;
        this.fax = fax;
        this.website = website;
        this.inscricaoMunicipal = inscricaoMunicipal;
        this.inscricaoEstadual = inscricaoEstadual;
    }

    public boolean isEmpresaSistema() {
        return empresaSistema;
    }

    public void setEmpresaSistema(boolean empresaSistema) {
        this.empresaSistema = empresaSistema;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getTelefoneComercial() {
        return telefoneComercial;
    }

    public void setTelefoneComercial(String telefoneComercial) {
        this.telefoneComercial = telefoneComercial;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getInscricaoMunicipal() {
        return inscricaoMunicipal;
    }

    public void setInscricaoMunicipal(String inscricaoMunicipal) {
        this.inscricaoMunicipal = inscricaoMunicipal;
    }

    public String getInscricaoEstadual() {
        return inscricaoEstadual;
    }

    public void setInscricaoEstadual(String inscricaoEstadual) {
        this.inscricaoEstadual = inscricaoEstadual;
    }
}
