/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tamarozzi.controller;

import br.com.tamarozzi.dao.PessoaDao;
import br.com.tamarozzi.dao.impl.PessoaDaoImpl;
import br.com.tamarozzi.model.Pessoa;
import java.util.List;
import javax.swing.JOptionPane;
import org.json.JSONObject;

/**
 *
 * @author Panda
 */
public class PessoaController {

    private final PessoaDao pessoaDao;

    public PessoaController() {
        this.pessoaDao = new PessoaDaoImpl();
    }

    public List<Pessoa> getAllPessoa(String campo, String valor, String tipoPessoa, String situacao) {
        return this.pessoaDao.getAllPessoa(campo, valor, tipoPessoa, situacao);
    }
    
    public Pessoa getPessoa(Pessoa p) {
        return this.pessoaDao.getPessoa(p);
    }

    public JSONObject editPessoa(Pessoa p) {
        if (p.getId() > 0) {
            return this.pessoaDao.edit(p);
        } else {
            return this.pessoaDao.add(p);
        }
    }

    public void deletePessoa(List<Pessoa> pessoas) {

        int opcao = JOptionPane.showConfirmDialog(null,
                "Confirma a exclusão deste(s) registro(s)?",
                "Atenção",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE);

        if (opcao == 0) {
            pessoas.stream().forEach((p) -> {
                this.pessoaDao.delete(p.getId());
            });
        }
    }
}
