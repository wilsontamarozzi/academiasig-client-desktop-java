/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tamarozzi.controller;

import br.com.tamarozzi.dao.BancoDao;
import br.com.tamarozzi.dao.impl.BancoDaoImpl;
import br.com.tamarozzi.model.Banco;
import java.util.List;
import javax.swing.JOptionPane;
import org.json.JSONObject;

/**
 *
 * @author Panda
 */
public class BancoController {
    
    private final BancoDao bancoDao;

    public BancoController() {
        this.bancoDao = new BancoDaoImpl();
    }

    public List<Banco> getAllBanco(String campo, String valor) {
        return this.bancoDao.getAllBanco(campo, valor);
    }
    
    public Banco getBanco(Banco p) {
        return this.bancoDao.getBanco(p);
    }

    public JSONObject editBanco(Banco p) {
        if (p.getUUID() != null) {
            return this.bancoDao.edit(p);
        } else {
            return this.bancoDao.add(p);
        }
    }

    public boolean deleteBanco(List<Banco> bancos) {

        int opcao = JOptionPane.showConfirmDialog(null,
                "Confirma a exclusão deste(s) registro(s)?",
                "Atenção",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE);

        if (opcao == 0) {
            bancos.stream().forEach((p) -> {
                this.bancoDao.delete(p.getUUID());
            });
            
            return true;
        }
        
        return false;
    }
}
