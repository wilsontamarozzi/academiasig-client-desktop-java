/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tamarozzi.model;

import java.math.BigDecimal;

/**
 *
 * @author Wilson
 */
public class LancamentoCategoria {
    
    private String UUID;
    private String lancamentoUUID;
    private String financeiroCategoriaUUID;
    private BigDecimal valor;
    
    private FinanceiroCategoria categoria;
    
    public LancamentoCategoria() {
        
    }
    
    public LancamentoCategoria(FinanceiroCategoria categoria, BigDecimal valor) {
        this.setCategoria(categoria);
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

    public String getFinanceiroCategoriaUUID() {
        return financeiroCategoriaUUID;
    }

    public void setFinanceiroCategoriaUUID(String financeiroCategoriaUUID) {
        this.financeiroCategoriaUUID = financeiroCategoriaUUID;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public FinanceiroCategoria getCategoria() {
        return categoria;
    }

    public void setCategoria(FinanceiroCategoria categoria) {
        this.categoria = categoria;
        this.setFinanceiroCategoriaUUID(categoria.getUUID());
    }
}
