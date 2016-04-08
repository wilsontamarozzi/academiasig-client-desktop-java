package br.com.tamarozzi.dao.impl;

import br.com.tamarozzi.dao.PessoaDao;
import br.com.tamarozzi.http.HttpClientAPI;
import br.com.tamarozzi.model.Pessoa;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Panda
 */
public class PessoaDaoImpl implements PessoaDao {

    @Override
    public void add(Pessoa pessoa) {
    }

    @Override
    public void edit(Pessoa pessoa) {
    }

    @Override
    public void delete(int pessoaId) {
    }

    @Override
    public Pessoa getPessoa(int pessoaId) {
        return null;
    }

    @Override
    public List<Pessoa> getAllPessoa() {
        String response = HttpClientAPI.sendGet("/v1/pessoas/lista.json");

        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).setPrettyPrinting().create();
        List<Pessoa> pessoas = Arrays.asList(gson.fromJson(response, Pessoa[].class));

        return pessoas;
    }
}
