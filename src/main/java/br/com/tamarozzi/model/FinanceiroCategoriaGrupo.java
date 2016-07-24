/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tamarozzi.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Wilson
 */
public class FinanceiroCategoriaGrupo {
    
    private String UUID;
    
    private String nome;
    
    private String tipo;
    
    private List<FinanceiroCategoria> categorias = new ArrayList<>(0);
    
    public FinanceiroCategoriaGrupo() {
        this.categorias = new ArrayList<>(0);
    }

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public List<FinanceiroCategoria> getCategorias() {
        if(this.categorias == null) {
            this.categorias = new ArrayList<>(0);
        }
        
        return categorias;
    }

    public void setCategorias(List<FinanceiroCategoria> categorias) {
        if(this.categorias == null) {
            this.categorias = new ArrayList<>(0);
        }
        
        this.categorias = categorias;
    }
}
