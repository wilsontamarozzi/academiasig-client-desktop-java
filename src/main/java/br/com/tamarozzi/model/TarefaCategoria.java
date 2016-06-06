/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tamarozzi.model;

/**
 *
 * @author Wilson
 */
public class TarefaCategoria {
    
    private String UUID;
    private String descricao;
    
    public TarefaCategoria() {
        
    }

    public TarefaCategoria(String descricao) {
        this.descricao = descricao;
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
}