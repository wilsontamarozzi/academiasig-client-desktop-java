/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tamarozzi.controller;

import br.com.tamarozzi.dao.ContaDao;
import br.com.tamarozzi.dao.impl.ContaDaoImpl;
import br.com.tamarozzi.model.Conta;
import java.util.List;
import javax.swing.JOptionPane;
import org.json.JSONObject;

/**
 *
 * @author Panda
 */
public class ContaController {
    
    private final ContaDao contaDao;

    public ContaController() {
        this.contaDao = new ContaDaoImpl();
    }

    public List<Conta> getAllConta(String campo, String valor, String tipoConta, String situacao) {
        return this.contaDao.getAllConta(campo, valor, tipoConta, situacao);
    }
    
    public Conta getConta(Conta p) {
        return this.contaDao.getConta(p);
    }

    public JSONObject editConta(Conta c) {
        if (c.getId() > 0) {
            return this.contaDao.edit(c);
        } else {
            return this.contaDao.add(c);
        }
    }

    public boolean deleteConta(List<Conta> contas) {

        int opcao = JOptionPane.showConfirmDialog(null,
                "Confirma a exclusão deste(s) registro(s)?",
                "Atenção",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE);

        if (opcao == 0) {
            contas.stream().forEach((p) -> {
                this.contaDao.delete(p.getId());
            });
            
            return true;
        }
        
        return false;
    }
}
