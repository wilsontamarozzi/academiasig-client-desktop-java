/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tamarozzi.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Wilson
 */
public class Tarefa {
    
    private String UUID;
    private String descricao;
    private Date dataCadastro;
    private Date dataVencimento;
    private Date dataConclusao;
    private boolean concluida;
    private String categoriaUUID;
    private String responsavelUUID;
    private TarefaCategoria categoria;
    private Pessoa responsavel;
    private List<TarefaComentario> comentarios;
    
    public Tarefa() {
        this.comentarios = new ArrayList<>(0);
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

    public boolean isConcluida() {
        return concluida;
    }

    public void setConcluida(boolean concluida) {
        this.concluida = concluida;
    }

    public String getCategoriaUUID() {
        return categoriaUUID;
    }

    public void setCategoriaUUID(String categoriaUUID) {
        this.categoriaUUID = categoriaUUID;
    }

    public String getResponsavelUUID() {
        return responsavelUUID;
    }

    public void setResponsavelUUID(String responsavelUUID) {
        this.responsavelUUID = responsavelUUID;
    }

    public TarefaCategoria getCategoria() {
        return categoria;
    }

    public void setCategoria(TarefaCategoria categoria) {
        this.categoria = categoria;
        this.categoriaUUID = categoria.getUUID();
    }

    public Pessoa getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Pessoa responsavel) {
        this.responsavel = responsavel;
        this.responsavelUUID = responsavel.getUUID();
    }

    public List<TarefaComentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<TarefaComentario> comentarios) {
        this.comentarios = comentarios;
    }
    
    public void addComentario(TarefaComentario comentario) {
        this.comentarios.add(comentario);
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public Date getDataConclusao() {
        return dataConclusao;
    }

    public void setDataConclusao(Date dataConclusao) {
        this.dataConclusao = dataConclusao;
    }
}