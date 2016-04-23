/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tamarozzi.controller;

import br.com.tamarozzi.dao.LogradouroDao;
import br.com.tamarozzi.dao.impl.LogradouroDaoImpl;
import br.com.tamarozzi.model.Logradouro;

/**
 *
 * @author Panda
 */
public class LogradouroController {
    
    private final LogradouroDao logradouroDao;
    
    public LogradouroController() {
        this.logradouroDao = new LogradouroDaoImpl();
    }
    
    public Logradouro getLogradouroByCEP(String cep) {
        return this.logradouroDao.getLogradouroByCEP(cep);
    }
}
