package br.com.tamarozzi.service.impl;

import br.com.tamarozzi.dao.PessoaDao;
import br.com.tamarozzi.dao.impl.PessoaDaoImpl;
import br.com.tamarozzi.model.Pessoa;
import br.com.tamarozzi.services.PessoaService;
import java.util.List;

public class PessoaServiceImpl implements PessoaService {

    private PessoaDao pessoaDao;

    public PessoaServiceImpl() {
        this.pessoaDao = new PessoaDaoImpl();
    }

    @Override
    public void add(Pessoa pessoa) {
        this.pessoaDao.add(pessoa);
    }

    @Override
    public void edit(Pessoa pessoa) {
        this.pessoaDao.edit(pessoa);
    }

    @Override
    public void delete(int pessoaId) {
        this.pessoaDao.delete(pessoaId);
    }

    @Override
    public Pessoa getPessoa(int pessoaId) {
        return this.pessoaDao.getPessoa(pessoaId);
    }

    @Override
    public List<Pessoa> getAllPessoa(String campo, String valor, String tipoPessoa, String situacao) {
        return this.pessoaDao.getAllPessoa(campo, valor, tipoPessoa, situacao);
    }
}
