/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tamarozzi.http;

import br.com.tamarozzi.util.MD5EncodeUtil;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
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
public class HttpClientAPITest {
    
    private final String user = "wilson";
    private final String password = "123";
    
    public HttpClientAPITest() {
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
     * Test of autenticacao method, of class HttpClientAPI.
     */
    @Test
    public void testAutenticacao() {
        System.out.println("autenticacao");
        
        boolean expResult = true;
        boolean result = HttpClientAPI.autenticacao(user, password);
        assertEquals(expResult, result);
    }

    /**
     * Test of sendGet method, of class HttpClientAPI.
     */
    @Test
    public void testSendGet() {
        System.out.println("sendGet");
        String urlString = "v1/pessoas/1";
        
        String expResult = null;
        String result = HttpClientAPI.sendGet(urlString);
        assertNotEquals(expResult, result);
    }

    /**
     * Test of sendPost method, of class HttpClientAPI.
     */
    @Test
    public void testSendPost() {
        System.out.println("sendPost");
        String urlString = "v1/usuarios/auth";
        
        List<NameValuePair> nvps = new ArrayList<>();
        nvps.add(new BasicNameValuePair("login", user));
        nvps.add(new BasicNameValuePair("senha", MD5EncodeUtil.encode(password)));
        
        String expResult = null;
        String result = HttpClientAPI.sendPost(urlString, nvps);
        assertNotEquals(expResult, result);
    }    
}
