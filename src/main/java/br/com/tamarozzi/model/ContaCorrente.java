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
    private int titularId;
    private int bancoId;
    private Pessoa titular;
    private Banco banco;

    public ContaCorrente() {
        setTipoConta(CONTA_CORRENTE);
        this.titular = new Pessoa();
        this.banco = new Banco();
    }

    public ContaCorrente(int agencia, int agenciaDigito, int conta, int contaDigito, int titularId, int bancoId, Pessoa titular, Banco banco, int id, String descricao, boolean ativo, String tipoConta, BigDecimal saldoInicial) {
        super(id, descricao, ativo, tipoConta, saldoInicial);
        this.agencia = agencia;
        this.agenciaDigito = agenciaDigito;
        this.conta = conta;
        this.contaDigito = contaDigito;
        this.titularId = titularId;
        this.bancoId = bancoId;
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

    public int getTitularId() {
        return titularId;
    }

    public void setTitularId(int titularId) {
        this.titularId = titularId;
    }

    public int getBancoId() {
        return bancoId;
    }

    public void setBancoId(int bancoId) {
        this.bancoId = bancoId;
    }

    public Pessoa getTitular() {
        return titular;
    }

    public void setTitular(Pessoa titular) {
        this.titular = titular;
        this.setTitularId(titular.getId());
    }

    public Banco getBanco() {
        return banco;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
        setBancoId(banco.getId());
    }
}
