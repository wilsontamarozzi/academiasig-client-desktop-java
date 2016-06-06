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

    /* Atributos Pessoas */
    private String UUID;
    private int logradouroId;
    private int codigo;
    private String nome;
    private String tipoPessoa;
    private String observacao;
    private String suporte;
    private String email;
    private boolean ativo = true;
    private Date dataCadastro;
    private Pessoa CadastradoPor;
        
    /* Atributos de Endereço */
    private String numero;
    private String complemento;
    
    /* Atributos Pessoa Física */
    private String cpf;
    private String rg;
    private String telefoneResidencial;
    private String telefoneCelular;
    private String telefoneComercial;
    private boolean sexo;
    private boolean usuarioSistema = false;
    private Usuario usuario;
    private Date dataNascimento;
    
    /* Atributos Pessoa Jurídicas */
    private String telefoneEmpresa;
    private String razaoSocial;
    private String cnpj;
    private String fax;
    private String website;
    private String inscricaoMunicipal;
    private String inscricaoEstadual;
    private boolean empresaSistema;
    
    private Logradouro logradouro;
    
    public Pessoa() {
        
    }

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public int getLogradouroId() {
        return logradouroId;
    }

    public void setLogradouroId(int logradouroId) {
        this.logradouroId = logradouroId;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
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

    public Pessoa getCadastradoPor() {
        return CadastradoPor;
    }

    public void setCadastradoPor(Pessoa CadastradoPor) {
        this.CadastradoPor = CadastradoPor;
    }

    public String getCpf() {
        return cpf;
    }
    
    public String getCpfClean() {
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
        return telefoneResidencial;
    }
    
    public String getTelefoneResidencialClean() {
        return replace(telefoneResidencial);
    }

    public void setTelefoneResidencial(String telefoneResidencial) {
        this.telefoneResidencial = telefoneResidencial;
    }

    public String getTelefoneCelular() {
        return telefoneCelular;
    }
    
    public String getTelefoneCelularClean() {
        return replace(telefoneCelular);
    } 

    public void setTelefoneCelular(String telefoneCelular) {
        this.telefoneCelular = telefoneCelular;
    }

    public String getTelefoneComercial() {
        return telefoneComercial;
    }
    
    public String getTelefoneComercialClean() {
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

    public Logradouro getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(Logradouro logradouro) {
        this.logradouro = logradouro;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
    
    private String replace(String text) {
        if(text != null) {
            String caracter[] = {"(", ")", "-", ".", " "};

            for(String c : caracter) {
                text = text.replace(c, "");
            }
        }
        
        return text;
    }
}
