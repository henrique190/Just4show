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

	public ChangePassword(String externalUrl) {
		super();
		this.externalUrl = externalUrl;
	}
	
	
	public void changelAction() throws IOException {
		
		URL url;
	    Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1",8888));
	    url = new URL(externalUrl);
	    HttpsURLConnection con = (HttpsURLConnection)url.openConnection(proxy);

	    String USER_AGENT = RandomUserAgent.getRandomUserAgent();
	    con.setRequestMethod("POST");
	    con.setRequestProperty("Host", "secure.runescape.com");
	    con.setRequestProperty("User-Agent",USER_AGENT);
	    con.setRequestProperty("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
	    con.setRequestProperty("Accept-Language", "en-US,en);q=0.5");
	    con.setRequestProperty("Accept-Encoding", "gzip, deflate, br");
	    con.setRequestProperty("Referer","http://oldschool.runescape.com/");
	    con.setDoOutput(true);
	    con.setDoInput(true);
	    
	    int um = externalUrl.indexOf("y=");
        int dois = externalUrl.lastIndexOf("");
        String session = externalUrl.substring(um + 2, dois);
        
        System.out.println(session);
	    String params = "password1=henrique190&password2=henrique190&changekey="+session+"&submitpasswords=Change+Password";
	    
	    DataOutputStream wr = new DataOutputStream(con.getOutputStream());
	    System.out.println(params);
        wr.writeBytes(params);
        wr.flush();
        wr.close();
	    System.out.println("\n"+con.getResponseCode()+"\n");    
	    System.out.println("\n"+con.getURL().toString()+"\n");
		   
	}
}
