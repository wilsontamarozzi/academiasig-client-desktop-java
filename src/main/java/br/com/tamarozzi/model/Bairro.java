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
public class Bairro {
    
    private int bairroCodigo;
    private String bairroDescricao;
    private Cidade cidade;
    
    public Bairro() {
        
    }
    
    public int getBairroCodigo() {
        return bairroCodigo;
    }

    public void setBairroCodigo(int bairroCodigo) {
        this.bairroCodigo = bairroCodigo;
    }

    public String getBairroDescricao() {
        return bairroDescricao;
    }

    public void setBairroDescricao(String bairroDescricao) {
        this.bairroDescricao = bairroDescricao;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }
}