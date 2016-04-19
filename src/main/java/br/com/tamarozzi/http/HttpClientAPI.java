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
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.protocol.HTTP;

public final class HttpClientAPI {

    //http://academiasig-api.herokuapp.com/
    private static final String host = "http://localhost:8080/";

    private static CredentialsProvider credentialsProvider;

    private static HttpClient instance;

    public static boolean autenticacao(String login, String password) {

        credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY,
                new UsernamePasswordCredentials(login, MD5EncodeUtil.encode(password)));

        instance = HttpClientBuilder.create().setDefaultCredentialsProvider(credentialsProvider).build();
        
        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).setPrettyPrinting().create();
        
        Usuario usuario  = new Usuario();
        usuario.setLogin(login);
        usuario.setSenha(MD5EncodeUtil.encode(password));
        
        String response = HttpClientAPI.sendPost("api/v1/usuarios/auth", gson.toJson(usuario));

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

        HttpGet getRequest = new HttpGet(host + urlString);
        getRequest.addHeader(HTTP.CONTENT_TYPE, "application/json");

        return executeRequest(getRequest);
    }

    public static String sendPost(String urlString, List<NameValuePair> nvps) {

        HttpPost postRequest = new HttpPost(host + urlString);
        postRequest.addHeader(HTTP.CONTENT_TYPE, "application/json");
        postRequest.setEntity(new UrlEncodedFormEntity(nvps, StandardCharsets.UTF_8));
        
        return executeRequest(postRequest);
    }

    public static String sendPost(String urlString, String content) {

        HttpPost postRequest = new HttpPost(host + urlString);
        postRequest.addHeader(HTTP.CONTENT_TYPE, "application/json");
        postRequest.setEntity(new StringEntity(content, StandardCharsets.UTF_8));

        return executeRequest(postRequest);
    }

    public static String sendPut(String urlString, String content) {
        
        HttpPut putRequest = new HttpPut(host + urlString);
        putRequest.addHeader(HTTP.CONTENT_TYPE, "application/json");
        putRequest.setEntity(new StringEntity(content, StandardCharsets.UTF_8));

        return executeRequest(putRequest);
    }
 
    public static String sendDelete(String urlString) {
        return executeRequest(new HttpDelete(host + urlString));
    }   

    private static String executeRequest(HttpUriRequest request) {

        System.out.println(request.getURI().toString());

        String data = null;

        try {
            HttpResponse response = HttpClientAPI.getInstance().execute(request);

            int statusCode = response.getStatusLine().getStatusCode();

            if (statusCode != 200 && statusCode != 201) {
                parsingStatusCode(statusCode);
                return null;
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent()), StandardCharsets.UTF_8));

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
        switch (statusCode) {
            case 500:
                generateLogStatusCode(statusCode);
                JOptionPane.showMessageDialog(null, "Erro interno no servidor.");
                break;
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
