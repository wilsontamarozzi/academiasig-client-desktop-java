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
public class Estado {
    
    private int ufCodigo;
    private String ufSigla;
    private String ufDescricao;

    public Estado() {
        
    }

    public int getUfCodigo() {
        return ufCodigo;
    }

    public void setUfCodigo(int ufCodigo) {
        this.ufCodigo = ufCodigo;
    }

    public String getUfSigla() {
        return ufSigla;
    }

    public void setUfSigla(String ufSigla) {
        this.ufSigla = ufSigla;
    }

    public String getUfDescricao() {
        return ufDescricao;
    }

    public void setUfDescricao(String ufDescricao) {
        this.ufDescricao = ufDescricao;
    }
}