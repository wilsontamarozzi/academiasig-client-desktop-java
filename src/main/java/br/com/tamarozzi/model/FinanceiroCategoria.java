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
public class FinanceiroCategoria {

    private String UUID;
    
    private String nome;
    
    private String tipo;
    
    private String grupoCategoriaUUID;

    public FinanceiroCategoria() {
        
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

    public String getGrupoCategoriaUUID() {
        return grupoCategoriaUUID;
    }

    public void setGrupoCategoriaUUID(String grupoCategoriaUUID) {
        this.grupoCategoriaUUID = grupoCategoriaUUID;
    }
}
