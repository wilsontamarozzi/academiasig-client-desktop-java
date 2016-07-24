/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tamarozzi.controller;

import br.com.tamarozzi.dao.LancamentoMovimentacaoDao;
import br.com.tamarozzi.dao.impl.LancamentoMovimentacaoDaoImpl;
import br.com.tamarozzi.model.LancamentoMovimentacao;
import java.util.List;
import org.json.JSONObject;

/**
 *
 * @author Panda
 */
public class LancamentoMovimentacaoController {
    
    private final LancamentoMovimentacaoDao lancamentoMovimentacaoDao;

    public LancamentoMovimentacaoController() {
        this.lancamentoMovimentacaoDao = new LancamentoMovimentacaoDaoImpl();
    }

    public List<LancamentoMovimentacao> getAllLancamentoMovimentacao(String campo, String valor) {
        return this.lancamentoMovimentacaoDao.getAll(campo, valor);
    }
    
    public LancamentoMovimentacao getLancamentoMovimentacao(LancamentoMovimentacao p) {
        return this.lancamentoMovimentacaoDao.get(p);
    }

    public JSONObject editLancamentoMovimentacao(LancamentoMovimentacao p) {
        if (p.getUUID() != null) {
            return this.lancamentoMovimentacaoDao.edit(p);
        } else {
            return this.lancamentoMovimentacaoDao.add(p);
        }
    }

    public void deleteLancamentoMovimentacao(List<LancamentoMovimentacao> lancamentoMovimentacaos) {

        lancamentoMovimentacaos.stream().forEach((p) -> {
            this.lancamentoMovimentacaoDao.delete(p.getUUID());
        });
    }
}
