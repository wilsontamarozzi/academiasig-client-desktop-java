/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tamarozzi.controller;

import br.com.tamarozzi.http.HttpClientAPI;
import br.com.tamarozzi.model.Pessoa;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import static org.junit.Assert.*;

/**
 *
 * @author Monde
 */
public class PessoaControllerTest {

    public PessoaControllerTest() {
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
     * Test of getAllPessoa method, of class PessoaController.
     */
    @org.junit.Test
    public void testGetAllPessoa() {
        System.out.println("getAllPessoa");
        PessoaController instance = new PessoaController();
        List<Pessoa> result = instance.getAllPessoa("wilson", "nome", "F", "1");
        assertNotNull(this.getClass() + " - Lista de pessoas vázias", result);
    }

}
