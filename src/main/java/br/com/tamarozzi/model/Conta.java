/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tamarozzi.model;

import static br.com.tamarozzi.typeEnum.EnumTipoConta.CONTA_CAIXA;
import java.math.BigDecimal;

/**
 *
 * @author Panda
 */
public class Conta {
        
    private String UUID;
    private String descricao;
    private boolean ativo;
    private String tipoConta;
    private BigDecimal saldoInicial;

    public Conta() {
        this.tipoConta = CONTA_CAIXA;
    }
    
    public Conta(String UUID, String descricao, boolean ativo, String tipoConta, BigDecimal saldoInicial) {
        this.UUID = UUID;
        this.descricao = descricao;
        this.ativo = ativo;
        this.tipoConta = tipoConta;
        this.saldoInicial = saldoInicial;
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

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public String getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(String tipoConta) {
        this.tipoConta = tipoConta;
    }

    public BigDecimal getSaldoInicial() {
        return saldoInicial;
    }

    public void setSaldoInicial(BigDecimal saldoInicial) {
        this.saldoInicial = saldoInicial;
    }
}