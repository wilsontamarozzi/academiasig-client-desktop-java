/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tamarozzi.controller;

import br.com.tamarozzi.dao.LancamentoDao;
import br.com.tamarozzi.dao.impl.LancamentoDaoImpl;
import br.com.tamarozzi.model.Lancamento;
import java.util.List;
import javax.swing.JOptionPane;
import org.json.JSONObject;

/**
 *
 * @author Panda
 */
public class LancamentoController {
    
    private final LancamentoDao lancamentoDao;

    public LancamentoController() {
        this.lancamentoDao = new LancamentoDaoImpl();
    }

    public List<Lancamento> getAll(String campo, String valor) {
        return this.lancamentoDao.getAll(campo, valor);
    }
    
    public Lancamento get(Lancamento p) {
        return this.lancamentoDao.get(p);
    }

    public JSONObject edit(Lancamento p) {
        if (p.getUUID() != null) {
            return this.lancamentoDao.edit(p);
        } else {
            return this.lancamentoDao.add(p);
        }
    }

    public boolean delete(List<Lancamento> lancamentos) {

        int opcao = JOptionPane.showConfirmDialog(null,
                "Confirma a exclusão deste(s) registro(s)?",
                "Atenção",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE);

        if (opcao == 0) {
            lancamentos.stream().forEach((p) -> {
                this.lancamentoDao.delete(p.getUUID());
            });
            
            return true;
        }
        
        return false;
    }
}
