package Script;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;


import javax.net.ssl.HttpsURLConnection;

import org.apache.commons.io.IOUtils;

public class ValidadeNewEmail {
	Configs vars = new Configs();
	
	String session;
	String newUrl;
	
	public ValidadeNewEmail(String newUrl) {
		super();
		this.newUrl = newUrl;
	}
	
	public void validadelAction() throws IOException {		
		
		
		
		URL url;
	    Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1",8888));
	    url = new URL(newUrl);
	    HttpsURLConnection con = (HttpsURLConnection)url.openConnection(proxy);

	    String USER_AGENT = RandomUserAgent.getRandomUserAgent();
	    con.setRequestMethod("GET");
	    con.setRequestProperty("Host", "secure.runescape.com");
	    con.setRequestProperty("User-Agent",USER_AGENT);
	    con.setRequestProperty("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
	    con.setRequestProperty("Accept-Language", "en-US,en);q=0.5");
	    //  con.setRequestProperty("Accept-Encoding", "gzip, deflate, br");
	    con.setRequestProperty("Referer","http://oldschool.runescape.com/");
	    con.setDoOutput(true);
	    con.setDoInput(true);
	    
	    BufferedReader br;
		if (200 <= con.getResponseCode() && con.getResponseCode() <= 299) {
	        br = new BufferedReader(new InputStreamReader(con.getInputStream()));
	    } else {
	        br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
	    }
	    StringBuilder sb = new StringBuilder();
	    String output;
	    while ((output = br.readLine()) != null) {
	      sb.append(output);
	    }

	    
	    if(sb.toString().contains("<h1>Cadastro efetuado</h1>")) {
	    	vars.validadeEmail = true;
	    	System.out.println("New email sucessul setted");
	    }
	    
	   
	    
	    
	}
	
	
	
}
