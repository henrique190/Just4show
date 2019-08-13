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

public class ChangeEmail {
	
	String session;
	String gmail;
	Configs vars = new Configs();

	public ChangeEmail(String session,String gmail) {
		super();
		this.session = session;
		this.gmail = gmail;
	}
	
	public void action() throws IOException {
		String EmailSession = "https://secure.runescape.com/m=email-register/l=3/a=869/"+session+"/submit_address";
		
		URL url;
	    Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1",8888));
	    url = new URL(EmailSession);
	    HttpsURLConnection con = (HttpsURLConnection)url.openConnection();

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
	    DataOutputStream wr = new DataOutputStream(con.getOutputStream());
	    
	    String params = "na="+gmail+"&na2="+gmail+"&agree_pp_and_tac=on&reg=1&action=-1&submit=Submit";
	    
        System.out.println(params);
        Configs.status = (params);
        wr.writeBytes(params);
        wr.flush();
        wr.close();
        
        
        
	    System.out.println("\n"+con.getResponseCode()+"\n");    
	    Configs.status = ("\n"+con.getResponseCode()+"\n");   
        String location = "" + con.getURL().toString();
        
        if (location.contains("submit_address") || location.contains("set_address")) {
			System.out.println("Sent email adress");
			Configs.status =("Sent email adress");
			
		} else {
			System.out.println("Invalid email request change");
			Configs.status = ("Invalid email request change");
		}

		con.disconnect();
        
        
	}
}
