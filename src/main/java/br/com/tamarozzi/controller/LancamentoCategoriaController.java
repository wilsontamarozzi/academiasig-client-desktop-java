/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tamarozzi.controller;

import br.com.tamarozzi.dao.LancamentoCategoriaDao;
import br.com.tamarozzi.dao.impl.LancamentoCategoriaGrupoDaoImpl;
import br.com.tamarozzi.model.LancamentoCategoriaGrupo;
import java.util.List;
import javax.swing.JOptionPane;
import org.json.JSONObject;
import br.com.tamarozzi.dao.LancamentoCategoriaGrupoDao;
import br.com.tamarozzi.dao.impl.LancamentoCategoriaDaoImpl;
import br.com.tamarozzi.model.LancamentoCategoria;

/**
 *
 * @author Panda
 */
public class LancamentoCategoriaController {
    
    private final LancamentoCategoriaGrupoDao lancamentoCategoriaGrupoDao;
    private final LancamentoCategoriaDao lancamentoCategoriaDao;

    public LancamentoCategoriaController() {
        this.lancamentoCategoriaGrupoDao = new LancamentoCategoriaGrupoDaoImpl();
        this.lancamentoCategoriaDao = new LancamentoCategoriaDaoImpl();
    }

    /* PARTE DOS GRUPOS DE CATEGORIA*/
    public List<LancamentoCategoriaGrupo> getAllGrupoCategoria(String campo, String valor) {
        return this.lancamentoCategoriaGrupoDao.getAllGrupoCategoria(campo, valor);
    }
    
    public LancamentoCategoriaGrupo getGrupoCategoria(LancamentoCategoriaGrupo g) {
        return this.lancamentoCategoriaGrupoDao.getGrupoCategoria(g);
    }

    public JSONObject editGrupoCategoria(LancamentoCategoriaGrupo g) {
        if (g.getUUID() != null) {
            return this.lancamentoCategoriaGrupoDao.edit(g);
        } else {
            return this.lancamentoCategoriaGrupoDao.add(g);
        }
    }

    public boolean deleteCategoria(List<Object> categorias) {

        int opcao = JOptionPane.showConfirmDialog(null,
                "Confirma a exclusão deste(s) registro(s)?",
                "Atenção",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE);

        if (opcao == 0) {
            categorias.stream().forEach((c) -> {
                if(c instanceof LancamentoCategoriaGrupo) {
                    this.lancamentoCategoriaGrupoDao.delete(((LancamentoCategoriaGrupo) c).getUUID());
                }

                if(c instanceof LancamentoCategoria) {
                    this.lancamentoCategoriaDao.delete(((LancamentoCategoria) c).getUUID());
                }
            });
            
            return true;
        }
        
        return false;
    }
    
    /* PARTE DAS CATEGORIAS */
    public List<LancamentoCategoria> getAllCategoria(String campo, String valor) {
        return this.lancamentoCategoriaDao.getAll(campo, valor);
    }
    
    public LancamentoCategoria getCategoria(LancamentoCategoria c) {
        return this.lancamentoCategoriaDao.get(c);
    }

    public JSONObject editCategoria(LancamentoCategoria c) {
        if (c.getUUID() != null) {
            return this.lancamentoCategoriaDao.edit(c);
        } else {
            return this.lancamentoCategoriaDao.add(c);
        }
    }
}
