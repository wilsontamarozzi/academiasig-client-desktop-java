/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tamarozzi.model;

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

    public ContaCorrente() {
        
    }
    
    public ContaCorrente(int agencia, int agenciaDigito, int conta, int contaDigito, int id, String descricao, boolean ativo, String tipoConta, BigDecimal saldoInicial) {
        super(id, descricao, ativo, tipoConta, saldoInicial);
        this.agencia = agencia;
        this.agenciaDigito = agenciaDigito;
        this.conta = conta;
        this.contaDigito = contaDigito;
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
}
