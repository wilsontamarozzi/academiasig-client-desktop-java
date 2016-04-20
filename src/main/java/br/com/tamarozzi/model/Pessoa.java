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

    /* Atributos Pessoas */
    private int id;
    private String nome;
    private String tipoPessoa;
    private String observacao;
    private String suporte;
    private String email;
    private boolean ativo = true;    
    
    /* Atributos de Endereço */
    private String bairro;
    private String logradouro;
    private String numero;
    private String complemento;
    private String cep;
    
    /* Atributos Pessoa Física */
    private String cpf;
    private String rg;
    private String telefoneResidencial;
    private String telefoneCelular;
    private String telefoneComercial;
    private boolean sexo;
    private boolean usuarioSistema = false;
    private Usuario usuario;
    
    /* Atributos Pessoa Jurídicas */
    private String telefoneEmpresa;
    private String razaoSocial;
    private String cnpj;
    private String fax;
    private String website;
    private String inscricaoMunicipal;
    private String inscricaoEstadual;
    private boolean empresaSistema;
    
    public Pessoa() {
        
    }

    public Pessoa(int id, String nome, String tipoPessoa, String observacao, String suporte, String email, String bairro, String logradouro, String numero, String complemento, String cep, String cpf, String rg, String telefoneResidencial, String telefoneCelular, String telefoneComercial, boolean sexo, Usuario usuario, String telefoneEmpresa, String razaoSocial, String cnpj, String fax, String website, String inscricaoMunicipal, String inscricaoEstadual, boolean empresaSistema) {
        this.id = id;
        this.nome = nome;
        this.tipoPessoa = tipoPessoa;
        this.observacao = observacao;
        this.suporte = suporte;
        this.email = email;
        this.bairro = bairro;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.cep = cep;
        this.cpf = cpf;
        this.rg = rg;
        this.telefoneResidencial = telefoneResidencial;
        this.telefoneCelular = telefoneCelular;
        this.telefoneComercial = telefoneComercial;
        this.sexo = sexo;
        this.usuario = usuario;
        this.telefoneEmpresa = telefoneEmpresa;
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.fax = fax;
        this.website = website;
        this.inscricaoMunicipal = inscricaoMunicipal;
        this.inscricaoEstadual = inscricaoEstadual;
        this.empresaSistema = empresaSistema;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public String getCpf() {
        return replace(cpf);
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getTelefoneResidencial() {
        return replace(telefoneResidencial);
    }

    public void setTelefoneResidencial(String telefoneResidencial) {
        this.telefoneResidencial = telefoneResidencial;
    }

    public String getTelefoneCelular() {
        return replace(telefoneCelular);
    }

    public void setTelefoneCelular(String telefoneCelular) {
        this.telefoneCelular = telefoneCelular;
    }

    public String getTelefoneComercial() {
        return replace(telefoneComercial);
    }

    public void setTelefoneComercial(String telefoneComercial) {
        this.telefoneComercial = telefoneComercial;
    }

    public boolean isSexo() {
        return sexo;
    }

    public void setSexo(boolean sexo) {
        this.sexo = sexo;
    }

    public boolean isUsuarioSistema() {
        return usuarioSistema;
    }

    public void setUsuarioSistema(boolean usuarioSistema) {
        this.usuarioSistema = usuarioSistema;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getTelefoneEmpresa() {
        return telefoneEmpresa;
    }

    public void setTelefoneEmpresa(String telefoneEmpresa) {
        this.telefoneEmpresa = telefoneEmpresa;
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

    public boolean isEmpresaSistema() {
        return empresaSistema;
    }

    public void setEmpresaSistema(boolean empresaSistema) {
        this.empresaSistema = empresaSistema;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCep() {
        return replace(cep);
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
    
    private String replace(String text) {
        String caracter[] = {"(", ")", "-", ".", " "};
        
        for(String c : caracter) {
            text = text.replace(c, "");
        }
        
        return text;
    }
}
