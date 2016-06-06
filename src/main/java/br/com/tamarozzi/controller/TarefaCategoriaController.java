/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tamarozzi.controller;

import br.com.tamarozzi.dao.TarefaCategoriaDao;
import br.com.tamarozzi.dao.impl.TarefaCategoriaDaoImpl;
import br.com.tamarozzi.model.TarefaCategoria;
import java.util.List;
import javax.swing.JOptionPane;
import org.json.JSONObject;

/**
 *
 * @author Panda
 */
public class TarefaCategoriaController {
    
    private final TarefaCategoriaDao tarefaCategoriaDao;

    public TarefaCategoriaController() {
        this.tarefaCategoriaDao = new TarefaCategoriaDaoImpl();
    }

    public List<TarefaCategoria> getAllTarefaCategoria(String campo, String valor) {
        return this.tarefaCategoriaDao.getAllCategoria(campo, valor);
    }
    
    public TarefaCategoria getTarefaCategoria(TarefaCategoria p) {
        return this.tarefaCategoriaDao.getCategoria(p);
    }

    public JSONObject editTarefaCategoria(TarefaCategoria p) {
        if (p.getUUID() != null) {
            return this.tarefaCategoriaDao.edit(p);
        } else {
            return this.tarefaCategoriaDao.add(p);
        }
    }

    public boolean deleteTarefaCategoria(List<TarefaCategoria> tarefaCategorias) {

        int opcao = JOptionPane.showConfirmDialog(null,
                "Confirma a exclusão deste(s) registro(s)?",
                "Atenção",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE);

        if (opcao == 0) {
            tarefaCategorias.stream().forEach((p) -> {
                this.tarefaCategoriaDao.delete(p.getUUID());
            });
            
            return true;
        }
        
        return false;
    }
}
