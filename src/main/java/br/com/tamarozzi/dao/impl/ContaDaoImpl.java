package br.com.tamarozzi.dao.impl;

import br.com.tamarozzi.dao.ContaDao;
import br.com.tamarozzi.http.HttpClientAPI;
import br.com.tamarozzi.model.Conta;
import br.com.tamarozzi.model.ContaCorrente;
import static br.com.tamarozzi.typeEnum.EnumTipoConta.CONTA_CAIXA;
import static br.com.tamarozzi.typeEnum.EnumTipoConta.CONTA_CORRENTE;
import br.com.tamarozzi.util.ClassMergeUtil;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import java.lang.reflect.Type;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
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
public class ContaDaoImpl implements ContaDao {

    private final Gson gson;
    
    public ContaDaoImpl() {
        this.gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).setPrettyPrinting().create();
    }
    
    @Override
    public JSONObject add(Conta conta) {
        String contaJson = gson.toJson(conta);
        String response = HttpClientAPI.sendPost("api/v1/contas", contaJson);
        
        Conta contaNew = this.gson.fromJson(response, Conta.class);
        ClassMergeUtil.merge(conta, contaNew);
        
        JSONObject errors = new JSONObject(response);
        
        return !errors.isNull("errors") ? errors.getJSONObject("errors") : null;
    }

    @Override
    public JSONObject edit(Conta conta) {
       
        String contaJson = gson.toJson(conta);
        String response = HttpClientAPI.sendPut("api/v1/contas/" + conta.getId(), contaJson);
        
        JSONObject errors = new JSONObject(response);
        
        return !errors.isNull("errors") ? errors.getJSONObject("errors") : null;
    }

    @Override
    public void delete(int contaId) {
        
        String response = HttpClientAPI.sendDelete("api/v1/contas/" + contaId);
        
        if(response != null) {
            JOptionPane.showMessageDialog(null, "Houve um erro ao excluir o(s) registro(s)");
        }
    }

    @Override
    public Conta getConta(Conta conta) {
        
        String response = HttpClientAPI.sendGet("api/v1/contas/" + conta.getId());
        
        Conta contaNew;
        
        switch(conta.getTipoConta()) {
            case CONTA_CAIXA:
                contaNew = this.gson.fromJson(response, Conta.class);
                ClassMergeUtil.merge(conta, contaNew);
            break;
            case CONTA_CORRENTE:
                contaNew = this.gson.fromJson(response, ContaCorrente.class);
                ClassMergeUtil.merge(conta, contaNew);
            break;
        }
        
        return conta;
    }

    @Override
    public List<Conta> getAllConta(String campo, String valor, String tipoConta, String situacao) {

        List<Conta> contas = new ArrayList<>(0);
        
        if(tipoConta == null) {
            contas.addAll(this.getAllContas(campo, valor, CONTA_CAIXA, situacao, Conta[].class));
            contas.addAll(this.getAllContas(campo, valor, CONTA_CORRENTE, situacao, ContaCorrente[].class));
        } else {
            switch(tipoConta) {
                case CONTA_CAIXA:
                    contas.addAll(this.getAllContas(campo, valor, tipoConta, situacao, Conta[].class));
                break;
                case CONTA_CORRENTE :
                    contas.addAll(this.getAllContas(campo, valor, tipoConta, situacao, ContaCorrente[].class));
                break;
            }
        }
        
        contas.sort(Comparator.comparing(c -> c.getId()));
        
        return contas;
    }
    
    private List<Conta> getAllContas(String campo, String valor, String tipoConta, String situacao, Type tipoObject) {
        
        List<Conta> contas = new ArrayList<>(0);
        
        try {
            URIBuilder builder = new URIBuilder("api/v1/contas");

            if(!campo.equalsIgnoreCase("todos"))    { builder.addParameter(campo, valor); }
            if(!situacao.equalsIgnoreCase("todos")) { builder.addParameter("ativo", situacao); }

            builder.addParameter("tipo_conta", tipoConta);

            String response = HttpClientAPI.sendGet(builder.toString());

            contas.addAll(Arrays.asList(this.gson.fromJson(response, tipoObject)));
        } catch (JsonSyntaxException | URISyntaxException ex) {
            Logger.getLogger(ContaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return contas;
    }
}
