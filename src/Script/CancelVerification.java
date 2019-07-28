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

public class CancelVerification {
	
	String session;

	public CancelVerification(String session) {
		super();
		this.session = session;
	}
	
	public void cancelAction() throws IOException {
		String cancelEmailSession = "https://secure.runescape.com/m=email-register/l=3/a=869/"+session+"/cancel_action?type=address";
		
		URL url;
	    Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1",8888));
	    url = new URL(cancelEmailSession);
	    HttpsURLConnection con = (HttpsURLConnection)url.openConnection();

	    String USER_AGENT = RandomUserAgent.getRandomUserAgent();
	    con.setRequestMethod("GET");
	    con.setRequestProperty("Host", "secure.runescape.com");
	    con.setRequestProperty("User-Agent",USER_AGENT);
	    con.setRequestProperty("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
	    con.setRequestProperty("Accept-Language", "en-US,en);q=0.5");
	    con.setRequestProperty("Accept-Encoding", "gzip, deflate, br");
	    con.setRequestProperty("Referer","http://oldschool.runescape.com/");
	    con.setDoOutput(true);
	    con.setDoInput(true);
	    System.out.println("\n"+con.getResponseCode()+"\n");    
	    System.out.println("\n"+con.getURL().toString()+"\n"); 
	    Configs.status = ("\n"+con.getResponseCode()+"\n");    
		Configs.status =("\n"+con.getURL().toString()+"\n"); 
	}
}
