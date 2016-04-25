/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tamarozzi.dao.impl;

import br.com.tamarozzi.http.HttpClientAPI;
import br.com.tamarozzi.model.Pessoa;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Panda
 */
public class PessoaDaoImplTest {
    
    public PessoaDaoImplTest() {
        HttpClientAPI.autenticacao("wilson", "123");
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of add method, of class PessoaDaoImpl.
     */
    //@Test
    public void testAdd() {
        System.out.println("add");
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("");
        pessoa.setEmail("teste@teste.com.br");
        PessoaDaoImpl instance = new PessoaDaoImpl();
        instance.add(pessoa);
    }

    /**
     * Test of edit method, of class PessoaDaoImpl.
     */
    //@Test
    public void testEdit() {
        System.out.println("edit");
        Pessoa pessoa = new Pessoa();
        pessoa.setId(34);
        pessoa.setNome("Wilson Java lalalal");
        pessoa.setAtivo(true);
        pessoa.setTipoPessoa("F");
        PessoaDaoImpl instance = new PessoaDaoImpl();
        instance.edit(pessoa);
    }

    /**
     * Test of delete method, of class PessoaDaoImpl.
     */
    //@Test
    public void testDelete() {
        System.out.println("delete");
        int pessoaId = 0;
        PessoaDaoImpl instance = new PessoaDaoImpl();
        instance.delete(pessoaId);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPessoa method, of class PessoaDaoImpl.
     */
    //@Test
    public void testGetPessoa() {
        System.out.println("getPessoa");
        Pessoa pessoa = new Pessoa();
        pessoa.setId(1);
        PessoaDaoImpl instance = new PessoaDaoImpl();
        Pessoa result = instance.getPessoa(pessoa);
        assertNotNull(result);
    }

    /**
     * Test of getAllPessoa method, of class PessoaDaoImpl.
     */
    //@Test
    public void testGetAllPessoa() {
        System.out.println("getAllPessoa");
        String campo = "todos";
        String valor = "";
        String tipoPessoa = "todos";
        String situacao = "todos";
        PessoaDaoImpl instance = new PessoaDaoImpl();
        List<Pessoa> result = instance.getAllPessoa(campo, valor, tipoPessoa, situacao);
        assertNotNull(result);
    }
    
}
