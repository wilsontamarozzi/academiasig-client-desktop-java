/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tamarozzi.controller;

import br.com.tamarozzi.dao.TarefaDao;
import br.com.tamarozzi.dao.impl.TarefaDaoImpl;
import br.com.tamarozzi.model.Tarefa;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import org.json.JSONObject;

/**
 *
 * @author Panda
 */
public class TarefaController {
    
    private final TarefaDao tarefaDao;

    public TarefaController() {
        this.tarefaDao = new TarefaDaoImpl();
    }

    public List<Tarefa> getAllTarefa(String campo, String valor, String situacao, String responsavelUUID, String categoriaUUID, String tipoData, Date dataInicio, Date dataFim) {
        return this.tarefaDao.getAllTarefa(campo, valor, situacao, responsavelUUID, categoriaUUID, tipoData, dataInicio, dataFim);
    }
    
    public Tarefa getTarefa(Tarefa p) {
        return this.tarefaDao.getTarefa(p);
    }

    public JSONObject editTarefa(Tarefa p) {
        if (p.getUUID() != null) {
            return this.tarefaDao.edit(p);
        } else {
            return this.tarefaDao.add(p);
        }
    }

    public boolean deleteTarefa(List<Tarefa> tarefas) {

        int opcao = JOptionPane.showConfirmDialog(null,
                "Confirma a exclusão deste(s) registro(s)?",
                "Atenção",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE);

        if (opcao == 0) {
            tarefas.stream().forEach((p) -> {
                this.tarefaDao.delete(p.getUUID());
            });
            
            return true;
        }
        
        return false;
    }
}
