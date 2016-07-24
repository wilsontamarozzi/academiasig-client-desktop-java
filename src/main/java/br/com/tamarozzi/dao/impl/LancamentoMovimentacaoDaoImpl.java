package br.com.tamarozzi.dao.impl;

import br.com.tamarozzi.dao.LancamentoMovimentacaoDao;
import br.com.tamarozzi.http.HttpClientAPI;
import br.com.tamarozzi.model.LancamentoMovimentacao;
import br.com.tamarozzi.util.ClassMergeUtil;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.apache.http.client.utils.URIBuilder;
import org.json.JSONObject;

/**
 *
 * @author Panda
 */
public class LancamentoMovimentacaoDaoImpl implements LancamentoMovimentacaoDao {

    private final static String QUERY = "api/v1/lancamento-movimentacoes";
    
    private final Gson gson;
    
    public LancamentoMovimentacaoDaoImpl() {
        this.gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).setPrettyPrinting().create();
    }
    
    @Override
    public JSONObject add(LancamentoMovimentacao lancamentoMovimentacao) {
        String lancamentoMovimentacaoJson = gson.toJson(lancamentoMovimentacao);
        String response = HttpClientAPI.sendPost(QUERY, lancamentoMovimentacaoJson);
        
        LancamentoMovimentacao lancamentoMovimentacaoNew = this.gson.fromJson(response, LancamentoMovimentacao.class);
        lancamentoMovimentacao.setUUID(lancamentoMovimentacaoNew.getUUID());
        
        JSONObject errors = new JSONObject(response);
        
        return !errors.isNull("errors") ? errors.getJSONObject("errors") : null;
    }

    @Override
    public JSONObject edit(LancamentoMovimentacao lancamentoMovimentacao) {
       
        String lancamentoMovimentacaoJson = gson.toJson(lancamentoMovimentacao);
        String response = HttpClientAPI.sendPut(QUERY + "/" + lancamentoMovimentacao.getUUID(), lancamentoMovimentacaoJson);
        
        JSONObject errors = new JSONObject(response);
        
        return !errors.isNull("errors") ? errors.getJSONObject("errors") : null;
    }

    @Override
    public void delete(String lancamentoMovimentacaoUUID) {
        
        String response = HttpClientAPI.sendDelete(QUERY + "/" + lancamentoMovimentacaoUUID);
        
        if(response != null) {
            JOptionPane.showMessageDialog(null, "Houve um erro ao excluir o(s) registro(s)");
        }
    }

    @Override
    public LancamentoMovimentacao get(LancamentoMovimentacao lancamentoMovimentacao) {
        
        String response = HttpClientAPI.sendGet(QUERY + "/" + lancamentoMovimentacao.getUUID());
        
        LancamentoMovimentacao lancamentoMovimentacaoNew = this.gson.fromJson(response, LancamentoMovimentacao.class);
        
        if(lancamentoMovimentacaoNew != null) {
            ClassMergeUtil.merge(lancamentoMovimentacao, lancamentoMovimentacaoNew);
        }
        
        return lancamentoMovimentacao;
    }

    @Override
    public List<LancamentoMovimentacao> getAll(String campo, String valor) {

        List<LancamentoMovimentacao> lancamentoMovimentacaos = new ArrayList<>(0);
        
        try {
            URIBuilder builder = new URIBuilder(QUERY);
            
            builder.addParameter(campo, valor);
            
            String response = HttpClientAPI.sendGet(builder.toString());
            
            if(response != null)
                lancamentoMovimentacaos = Arrays.asList(this.gson.fromJson(response, LancamentoMovimentacao[].class));
        } catch (URISyntaxException ex) {
            Logger.getLogger(LancamentoMovimentacaoDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return lancamentoMovimentacaos;
    }
}
