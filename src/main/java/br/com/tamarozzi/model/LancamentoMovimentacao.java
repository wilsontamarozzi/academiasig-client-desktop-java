/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tamarozzi.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Wilson
 */
public class LancamentoMovimentacao {
    
    private String UUID;
    private String lancamentoUUID;
    private String contaUUID;
    private BigDecimal valor;
    private Date dataMovimentacao;
    
    private Conta conta;

    public LancamentoMovimentacao() {
        this.dataMovimentacao = new Date();
    }
    
    public LancamentoMovimentacao(Conta conta, Date dataMovimentacao, BigDecimal valor) {
        this.setDataMovimentacao(dataMovimentacao);
        this.setConta(conta);
        this.setValor(valor);
    }
    
    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public String getLancamentoUUID() {
        return lancamentoUUID;
    }

    public void setLancamentoUUID(String lancamentoUUID) {
        this.lancamentoUUID = lancamentoUUID;
    }

    public String getContaUUID() {
        return contaUUID;
    }

    public void setContaUUID(String contaUUID) {
        this.contaUUID = contaUUID;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    public Date getDataMovimentacao() {
        return dataMovimentacao;
    }

    public void setDataMovimentacao(Date dataMovimentacao) {
        this.dataMovimentacao = dataMovimentacao;
    }
}
