/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tamarozzi.model;

import static br.com.tamarozzi.typeEnum.EnumTipoConta.CONTA_CORRENTE;
import java.math.BigDecimal;

/**
 *
 * @author Panda
 */
public class ContaCorrente extends Conta {
    
    private int agencia;
    private int agenciaDigito;
    private int conta;
    private int contaDigito;
    private String titularUUID;
    private String bancoUUID;
    private Pessoa titular;
    private Banco banco;

    public ContaCorrente() {
        setTipoConta(CONTA_CORRENTE);
        this.titular = new Pessoa();
        this.banco = new Banco();
    }

    public ContaCorrente(int agencia, int agenciaDigito, int conta, int contaDigito, String titularUUID, String bancoUUID, Pessoa titular, Banco banco, String UUID, String descricao, boolean ativo, String tipoConta, BigDecimal saldoInicial) {
        super(UUID, descricao, ativo, tipoConta, saldoInicial);
        this.agencia = agencia;
        this.agenciaDigito = agenciaDigito;
        this.conta = conta;
        this.contaDigito = contaDigito;
        this.titularUUID = titularUUID;
        this.bancoUUID = bancoUUID;
        this.titular = titular;
        this.banco = banco;
    }

    public int getAgencia() {
        return agencia;
    }

    public void setAgencia(int agencia) {
        this.agencia = agencia;
    }

    public int getAgenciaDigito() {
        return agenciaDigito;
    }

    public void setAgenciaDigito(int agenciaDigito) {
        this.agenciaDigito = agenciaDigito;
    }

    public int getConta() {
        return conta;
    }

    public void setConta(int conta) {
        this.conta = conta;
    }

    public int getContaDigito() {
        return contaDigito;
    }

    public void setContaDigito(int contaDigito) {
        this.contaDigito = contaDigito;
    }

    public String getTitularUUID() {
        return titularUUID;
    }

    public void setTitularUUID(String titularUUID) {
        this.titularUUID = titularUUID;
    }

    public String getBancoUUID() {
        return bancoUUID;
    }

    public void setBancoUUID(String bancoUUID) {
        this.bancoUUID = bancoUUID;
    }

    public Pessoa getTitular() {
        return titular;
    }

    public void setTitular(Pessoa titular) {
        this.titular = titular;
        this.setTitularUUID(titular.getUUID());
    }

    public Banco getBanco() {
        return banco;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
        this.setBancoUUID(banco.getUUID());
    }
}
