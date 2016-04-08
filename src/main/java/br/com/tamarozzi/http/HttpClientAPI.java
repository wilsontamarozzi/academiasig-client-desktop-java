package br.com.tamarozzi.http;

import com.sun.net.httpserver.Headers;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;

public final class HttpClientAPI {

    private static final String host = "http://academiasig-api.herokuapp.com";

    private static CredentialsProvider credentialsProvider;

    private static HttpClient instance;

    public String teste;

    public static boolean autenticacao(String user, String password) {
        try {
            credentialsProvider = new BasicCredentialsProvider();
            credentialsProvider.setCredentials(AuthScope.ANY,
                    new UsernamePasswordCredentials(user, password));

            HttpGet getRequest = new HttpGet(host + "/v1/pessoas/1.json");

            HttpResponse response = HttpClientAPI.getInstance().execute(getRequest);

            for (Header h : response.getAllHeaders()) {
                System.out.println(h);
            }

            if (response.getStatusLine().getStatusCode() != 200) {
                Logger.getLogger(HttpClientAPI.class.getName()).log(Level.SEVERE, "Failed : HTTP error code : {0}", response.getStatusLine().getStatusCode());
                return false;
            }

        } catch (IOException ex) {
            Logger.getLogger(HttpClientAPI.class.getName()).log(Level.SEVERE, null, ex);
        }

        return true;
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
                //throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
                System.out.println("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
                return null;
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent()), "UTF-8"));

            String output;
            StringBuilder responseBuffer = new StringBuilder();
            System.out.println("Output from Server .... \n");
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

    public static String sendPost(String urlString, String login, String senha) {

        String data = null;

        try {
            HttpPost postRequest = new HttpPost(urlString);

            StringEntity input = new StringEntity("{\"login\":\"wilson\",\"senha\":\"123\"}");
            //input.setContentType("application/json");
            postRequest.setEntity(input);

            HttpResponse response = HttpClientAPI.getInstance().execute(postRequest);

            if (response.getStatusLine().getStatusCode() != 201) {
                // throw new RuntimeException("Failed : HTTP error code : " +
                // response.getStatusLine().getStatusCode());
                System.out.println("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
                return null;
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));

            String output;
            StringBuilder responseBuffer = new StringBuilder();
            System.out.println("Output from Server .... \n");
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

    /*
	public static void main(String args[]) {
		HttpClientAPI http = new HttpClientAPI("wilson", "12");
		
		System.out.println(http.sendGet("/v1/bancos/lista.json"));
		//System.out.println(http.sendGet("/v1/pessoas/lista.json"));
	}
     */
}
