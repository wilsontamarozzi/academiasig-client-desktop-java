/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tamarozzi.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Wilson
 */
public class Lancamento {
    
    private String UUID;
    private String descricao;
    private String tipo;
    private BigDecimal valorFinal;
    private Date dataCadastro;
    private Date dataEmissao;
    private Date dataVencimento;
    private Date dataLiquidacao;
    private String observacao;
    private String pessoaUUID;
    private String cadastradoPorUUID;
    
    private Pessoa pessoa;
    private Pessoa cadastradoPor;
    
    private List<LancamentoCategoria> categorias;
    private List<LancamentoMovimentacao> movimentacoes;
    
    public Lancamento() {
        this.categorias = new ArrayList<>(0);
        this.movimentacoes = new ArrayList<>(0);
    }

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public BigDecimal getValorFinal() {
        return valorFinal;
    }

    public void setValorFinal(BigDecimal valorFinal) {
        this.valorFinal = valorFinal;
    }
    
    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Date getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(Date dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public Date getDataLiquidacao() {
        return dataLiquidacao;
    }

    public void setDataLiquidacao(Date dataLiquidacao) {
        this.dataLiquidacao = dataLiquidacao;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getPessoaUUID() {
        return pessoaUUID;
    }

    public void setPessoaUUID(String pessoaUUID) {
        this.pessoaUUID = pessoaUUID;
    }

    public String getCadastradoPorUUID() {
        return cadastradoPorUUID;
    }

    public void setCadastradoPorUUID(String cadastradoPorUUID) {
        this.cadastradoPorUUID = cadastradoPorUUID;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
        this.setPessoaUUID(pessoa.getUUID());
    }

    public Pessoa getCadastradoPor() {
        return cadastradoPor;
    }

    public void setCadastradoPor(Pessoa cadastradoPor) {
        this.cadastradoPor = cadastradoPor;
        this.setCadastradoPorUUID(cadastradoPor.getUUID());
    }

    public List<LancamentoCategoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<LancamentoCategoria> categorias) {
        this.categorias = categorias;
    }
    
    public void setCategorias(LancamentoCategoria categoria) {
        this.categorias.add(categoria);
    }
    
    public void removeCategorias(List<LancamentoCategoria> categorias) {
        this.categorias.removeAll(categorias);
    }

    public List<LancamentoMovimentacao> getMovimentacoes() {
        return movimentacoes;
    }

    public void setMovimentacoes(List<LancamentoMovimentacao> movimentacoes) {
        this.movimentacoes = movimentacoes;
    }
}
