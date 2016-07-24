/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tamarozzi.controller;

import br.com.tamarozzi.dao.LancamentoCategoriaDao;
import br.com.tamarozzi.dao.impl.LancamentoCategoriaDaoImpl;
import br.com.tamarozzi.model.LancamentoCategoria;
import java.util.List;
import javax.swing.JOptionPane;
import org.json.JSONObject;

/**
 *
 * @author Panda
 */
public class LancamentoCategoriaController {
    
    private final LancamentoCategoriaDao lancamentoCategoriaDao;

    public LancamentoCategoriaController() {
        this.lancamentoCategoriaDao = new LancamentoCategoriaDaoImpl();
    }

    public List<LancamentoCategoria> getAllLancamentoCategoria(String campo, String valor) {
        return this.lancamentoCategoriaDao.getAllLancamentoCategoria(campo, valor);
    }
    
    public LancamentoCategoria getLancamentoCategoria(LancamentoCategoria p) {
        return this.lancamentoCategoriaDao.getLancamentoCategoria(p);
    }

    public JSONObject editLancamentoCategoria(LancamentoCategoria p) {
        if (p.getUUID() != null) {
            return this.lancamentoCategoriaDao.edit(p);
        } else {
            return this.lancamentoCategoriaDao.add(p);
        }
    }

    public void deleteLancamentoCategoria(List<LancamentoCategoria> lancamentoCategorias) {

        lancamentoCategorias.stream().forEach((p) -> {
            this.lancamentoCategoriaDao.delete(p.getUUID());
        });
    }
}
