/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tamarozzi.controller;

import br.com.tamarozzi.dao.CidadeDao;
import br.com.tamarozzi.dao.impl.CidadeDaoImpl;
import br.com.tamarozzi.model.Cidade;
import java.util.List;

/**
 *
 * @author Panda
 */
public class CidadeController {
    
    private final CidadeDao cidadeDao;
    
    public CidadeController() {
        this.cidadeDao = new CidadeDaoImpl();
    }
    
    public List<Cidade> getAllCidades(String campo, String valor) {
        return this.cidadeDao.getAllCidade(campo, valor);
    }
}
