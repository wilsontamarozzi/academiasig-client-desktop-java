package br.com.tamarozzi.dao;

import java.util.List;

import br.com.tamarozzi.model.LancamentoMovimentacao;
import org.json.JSONObject;

public interface LancamentoMovimentacaoDao {

    public JSONObject add(LancamentoMovimentacao lancamentoMovimentacao);

    public JSONObject edit(LancamentoMovimentacao lancamentoMovimentacao);

    public void delete(String lancamentoMovimentacaoUUID);
    
    public LancamentoMovimentacao get(LancamentoMovimentacao lancamentoMovimentacao);

    public List<LancamentoMovimentacao> getAll(String campo, String valor);
}
