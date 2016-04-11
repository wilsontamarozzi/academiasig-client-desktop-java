/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tamarozzi.controller;

import br.com.tamarozzi.model.Pessoa;
import br.com.tamarozzi.service.impl.PessoaServiceImpl;
import br.com.tamarozzi.services.PessoaService;
import java.util.List;

/**
 *
 * @author Panda
 */
public class PessoaController {

    private final PessoaService pessoaService;

    public PessoaController() {
        this.pessoaService = new PessoaServiceImpl();
    }

    public List<Pessoa> getAllPessoa(String valor, String campo, String tipoPessoa) {
        return this.pessoaService.getAllPessoa(valor, campo, tipoPessoa);
    }
}
