/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tamarozzi.controller;

import br.com.tamarozzi.dao.impl.FinanceiroCategoriaGrupoDaoImpl;
import br.com.tamarozzi.model.FinanceiroCategoriaGrupo;
import java.util.List;
import javax.swing.JOptionPane;
import org.json.JSONObject;
import br.com.tamarozzi.dao.impl.FinanceiroCategoriaDaoImpl;
import br.com.tamarozzi.model.FinanceiroCategoria;
import br.com.tamarozzi.dao.FinanceiroCategoriaDao;
import br.com.tamarozzi.dao.FinanceiroCategoriaGrupoDao;

/**
 *
 * @author Panda
 */
public class FinanceiroCategoriaController {
    
    private final FinanceiroCategoriaGrupoDao lancamentoCategoriaGrupoDao;
    private final FinanceiroCategoriaDao lancamentoCategoriaDao;

    public FinanceiroCategoriaController() {
        this.lancamentoCategoriaGrupoDao = new FinanceiroCategoriaGrupoDaoImpl();
        this.lancamentoCategoriaDao = new FinanceiroCategoriaDaoImpl();
    }

    /* PARTE DOS GRUPOS DE CATEGORIA*/
    public List<FinanceiroCategoriaGrupo> getAllGrupoCategoria(String campo, String valor) {
        return this.lancamentoCategoriaGrupoDao.getAllGrupoCategoria(campo, valor);
    }
    
    public FinanceiroCategoriaGrupo getGrupoCategoria(FinanceiroCategoriaGrupo g) {
        return this.lancamentoCategoriaGrupoDao.getGrupoCategoria(g);
    }

    public JSONObject editGrupoCategoria(FinanceiroCategoriaGrupo g) {
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
                if(c instanceof FinanceiroCategoriaGrupo) {
                    this.lancamentoCategoriaGrupoDao.delete(((FinanceiroCategoriaGrupo) c).getUUID());
                }

                if(c instanceof FinanceiroCategoria) {
                    this.lancamentoCategoriaDao.delete(((FinanceiroCategoria) c).getUUID());
                }
            });
            
            return true;
        }
        
        return false;
    }
    
    /* PARTE DAS CATEGORIAS */
    public List<FinanceiroCategoria> getAllCategoria(String campo, String valor) {
        return this.lancamentoCategoriaDao.getAll(campo, valor);
    }
    
    public FinanceiroCategoria getCategoria(FinanceiroCategoria c) {
        return this.lancamentoCategoriaDao.get(c);
    }

    public JSONObject editCategoria(FinanceiroCategoria c) {
        if (c.getUUID() != null) {
            return this.lancamentoCategoriaDao.edit(c);
        } else {
            return this.lancamentoCategoriaDao.add(c);
        }
    }
}
