package Script;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;


import javax.net.ssl.HttpsURLConnection;

public class RecoverAccount {
	
	String externalUrl;
	String password;
	Configs vars = new Configs();
	String proxyIP;
	int port;
	String idAccount;
	
	
	public RecoverAccount(String externalUrl, String password, String proxyIP, int port) {
		// TODO Auto-generated constructor stub
		
		this.externalUrl = externalUrl;
		this.password = password;
		this.proxyIP = proxyIP;
		this.port = port;
	}


	public void changelAction() throws IOException, InterruptedException {
		Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyIP,port));
        URL obj = new URL(externalUrl);
        HttpsURLConnection con = (HttpsURLConnection) obj.openConnection(proxy);
        String USER_AGENT = RandomUserAgent.getRandomUserAgent();
        con.setInstanceFollowRedirects(false);
        con.setConnectTimeout(15000);
        con.setReadTimeout(15000);
        con.setRequestMethod("GET");

        if (200 == con.getResponseCode()) {
            System.out.println("200");

            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null)
                System.out.println(inputLine);
            in.close();

            System.out.println("Não funcionou 200");
        } else if(con.getResponseCode() == HttpsURLConnection.HTTP_MOVED_PERM || con.getResponseCode() == HttpsURLConnection.HTTP_MOVED_TEMP) {
            System.out.println("302");
            String location;
            location = con.getHeaderField("Location");
            System.out.println(location);

            con.disconnect();

            URL url = new URL(location);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection(proxy);
            conn.setConnectTimeout(15000);
            conn.setReadTimeout(15000);
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            int um = obj.toString().lastIndexOf("id=");
            int dois = obj.toString().lastIndexOf("");
            String idAccount = obj.toString().substring(um + 3, dois);

            String params = "password="+"Lasanha1"+"&confirm="+"Lasanha1"+"&submit=Change+Password"+"&account_id="+idAccount;

            DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
            System.out.println(params);
            wr.writeBytes(params);
            wr.flush();
            wr.close();

            System.out.println(conn.getResponseCode());

            BufferedReader br;
            if (200 <= conn.getResponseCode() && conn.getResponseCode() <= 299) {
                br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {
                br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }
            StringBuilder sb = new StringBuilder();
            String output;
            while ((output = br.readLine()) != null) {
                sb.append(output);
            }

           if(sb.toString().contains("Sua nova senha foi definida")){
               System.out.println("Deu certo");
           }


        }else{
            System.out.println("erro");
        }
	    
	    
		   
	}
}
