package br.com.tamarozzi.http;

import br.com.tamarozzi.util.MD5EncodeUtil;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

public final class HttpClientAPI {

    private static final String host = "http://academiasig-api.herokuapp.com";

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

        String data = HttpClientAPI.sendPost("/v1/usuarios/auth", nvps);

        return data != null;
    }

    private static synchronized HttpClient getInstance() {
        if (instance == null) {
            instance = HttpClientBuilder.create().setDefaultCredentialsProvider(credentialsProvider).build();
        }

        return instance;
    }

    public static String sendGet(String urlString) {

        String data = null;

        try {
            HttpGet getRequest = new HttpGet(host + urlString);
            getRequest.addHeader("accept", "application/json");

            HttpResponse response = HttpClientAPI.getInstance().execute(getRequest);

            if (response.getStatusLine().getStatusCode() != 200) {
                Logger.getLogger(HttpClientAPI.class.getName()).log(Level.SEVERE, "Failed : HTTP error code : {0}", response.getStatusLine().getStatusCode());
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
            HttpPost postRequest = new HttpPost(host + "/v1/usuarios/auth");

            postRequest.setEntity(new UrlEncodedFormEntity(nvps));

            HttpResponse response = HttpClientAPI.getInstance().execute(postRequest);

            if (response.getStatusLine().getStatusCode() != 200) {
                Logger.getLogger(HttpClientAPI.class.getName()).log(Level.SEVERE, "Failed : HTTP error code : {0}", response.getStatusLine().getStatusCode());
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
        } catch (IOException ex) {
            Logger.getLogger(HttpClientAPI.class.getName()).log(Level.SEVERE, null, ex);
        }

        return data;
    }
}
