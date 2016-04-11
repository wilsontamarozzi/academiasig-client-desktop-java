package br.com.tamarozzi.http;

import br.com.tamarozzi.model.Pessoa;
import br.com.tamarozzi.model.Usuario;
import br.com.tamarozzi.model.UsuarioLogado;
import br.com.tamarozzi.util.MD5EncodeUtil;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

public final class HttpClientAPI {

    private static final String host = "http://academiasig-api.herokuapp.com/";

    private static CredentialsProvider credentialsProvider;

    private static HttpClient instance;

    public String teste;

    public static boolean autenticacao(String user, String password) {

        credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY,
                new UsernamePasswordCredentials(user, MD5EncodeUtil.encode(password)));

        List<NameValuePair> nvps = new ArrayList<>();
        nvps.add(new BasicNameValuePair("login", user));
        nvps.add(new BasicNameValuePair("senha", MD5EncodeUtil.encode(password)));

        instance = HttpClientBuilder.create().setDefaultCredentialsProvider(credentialsProvider).build();

        String response = HttpClientAPI.sendPost("v1/usuarios/auth", nvps);

        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).setPrettyPrinting().create();
        /*Seta usuário que se logou */
        UsuarioLogado.setInstance(gson.fromJson(response, Pessoa.class));

        return response != null;
    }

    private static synchronized HttpClient getInstance() {
        if (instance == null) {
            instance = HttpClientBuilder.create().setDefaultCredentialsProvider(credentialsProvider).build();
        }

        return instance;
    }

    public static String sendGet(String urlString) {
        
        System.out.println(host + urlString);
        
        String data = null;

        try {
            HttpGet getRequest = new HttpGet(host + urlString);
            getRequest.addHeader("accept", "application/json");

            HttpResponse response = HttpClientAPI.getInstance().execute(getRequest);

            int statusCode = response.getStatusLine().getStatusCode();

            if (statusCode != 200) {
                parsingStatusCode(statusCode);
                return null;
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent()), "UTF-8"));

            String output;
            StringBuilder responseBuffer = new StringBuilder();
            while ((output = br.readLine()) != null) {
                responseBuffer.append(output);
            }

            data = responseBuffer.toString();
        } catch (ClientProtocolException ex) {
            Logger.getLogger(HttpClientAPI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(HttpClientAPI.class.getName()).log(Level.SEVERE, null, ex);
        }

        return data;
    }

    public static String sendPost(String urlString, List<NameValuePair> nvps) {
        
        String data = null;

        try {
            HttpPost postRequest = new HttpPost(host + urlString);
            postRequest.setEntity(new UrlEncodedFormEntity(nvps));

            HttpResponse response = HttpClientAPI.getInstance().execute(postRequest);
            
            int statusCode = response.getStatusLine().getStatusCode();

            if (statusCode != 200) {
                parsingStatusCode(statusCode);
                return null;
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));

            String output;
            StringBuilder responseBuffer = new StringBuilder();
            while ((output = br.readLine()) != null) {
                responseBuffer.append(output);
            }

            data = responseBuffer.toString();
        } catch (MalformedURLException ex) {
            Logger.getLogger(HttpClientAPI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (HttpHostConnectException ex) {
            Logger.getLogger(HttpClientAPI.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Connection time out: connect.");
        } catch (IOException ex) {
            Logger.getLogger(HttpClientAPI.class.getName()).log(Level.SEVERE, null, ex);
        }

        return data;
    }
    
    private static void parsingStatusCode(int statusCode) {
        switch(statusCode) {
            case 503:
                generateLogStatusCode(statusCode);
                JOptionPane.showMessageDialog(null, "Erro ao se conectar ao servidor.");
                break;
            case 404:
                JOptionPane.showMessageDialog(null, "Página não encontrada.");
                generateLogStatusCode(statusCode);
                break;
            case 401:
                generateLogStatusCode(statusCode);
                JOptionPane.showMessageDialog(null, "Usuário ou Senha Inválidos.");
                break;
        }        
    }
    
    private static void generateLogStatusCode(int statusCode) {
        Logger.getLogger(HttpClientAPI.class.getName()).log(Level.SEVERE, "Failed : HTTP error code : {0}", statusCode);
    }
}
