/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tamarozzi.model;

import java.util.Date;

/**
 *
 * @author Wilson
 */
public class TarefaComentario {
    
    private String UUID;
    private String tarefaUUID;
    private String comentario;
    private Date dataCadastro;
    
    public TarefaComentario() {
        
    }

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public String getTarefaUUID() {
        return tarefaUUID;
    }

    public void setTarefaUUID(String tarefaUUID) {
        this.tarefaUUID = tarefaUUID;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
}