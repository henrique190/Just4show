package Script;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;


import javax.net.ssl.HttpsURLConnection;

public class ChangePassword {
	
	String externalUrl;
	String password;
	Configs vars = new Configs();
	
	public ChangePassword(String externalUrl,String password) {
		super();
		this.externalUrl = externalUrl;
		this.password = password;
	}
	
	
	public void changelAction() throws IOException {
		
		URL url;
	    Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1",8888));
	    url = new URL(externalUrl);
	    HttpsURLConnection con = (HttpsURLConnection)url.openConnection();

	    String USER_AGENT = RandomUserAgent.getRandomUserAgent();
	    con.setRequestMethod("POST");
	    con.setRequestProperty("Host", "secure.runescape.com");
	    con.setRequestProperty("User-Agent",USER_AGENT);
	    con.setRequestProperty("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
	    con.setRequestProperty("Accept-Language", "en-US,en);q=0.5");
	    //con.setRequestProperty("Accept-Encoding", "gzip, deflate, br");
	    con.setRequestProperty("Referer","http://oldschool.runescape.com/");
	    con.setDoOutput(true);
	    con.setDoInput(true);
	    
	    int um = externalUrl.indexOf("y=");
        int dois = externalUrl.lastIndexOf("");
        String session = externalUrl.substring(um + 2, dois);
        
        System.out.println(session);
	    String params = "password1="+password+"&password2="+password+"&changekey="+session+"&submitpasswords=Change+Password";
	    
	    DataOutputStream wr = new DataOutputStream(con.getOutputStream());
	    System.out.println(params);
	    Configs.status = (params);
        wr.writeBytes(params);
        wr.flush();
        wr.close();
	    System.out.println("\n"+con.getResponseCode()+"\n"); 
	    Configs.status = ("\n"+con.getResponseCode()+"\n");
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

	    
	    if(sb.toString().contains("Mudança efetuada com sucesso")) {
	    	
	    	System.out.println("New password setted sucessful");
	    	Configs.status =("New password setted sucessful");
	    	vars.passwordChanged = true;
	    }else {
	    	Configs.status = ("Failed set new password");
	    	System.out.println("Failed set new password");
	    }
	    
	    
		   
	}
}
