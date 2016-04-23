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
public class Cidade {
    
    private int cidadeCodigo;
    private String cidadeDescricao;
    private String cidadeCep;
    private Estado estado;
    
    public Cidade() {
        
    }
    
    public int getCidadeCodigo() {
        return cidadeCodigo;
    }

    public void setCidadeCodigo(int cidadeCodigo) {
        this.cidadeCodigo = cidadeCodigo;
    }

    public String getCidadeDescricao() {
        return cidadeDescricao;
    }

    public void setCidadeDescricao(String cidadeDescricao) {
        this.cidadeDescricao = cidadeDescricao;
    }

    public String getCidadeCep() {
        return cidadeCep;
    }

    public void setCidadeCep(String cidadeCep) {
        this.cidadeCep = cidadeCep;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
}