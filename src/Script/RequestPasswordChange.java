package Script;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class RequestPasswordChange {
	String session;

	public RequestPasswordChange(String session) {
		super();
		this.session = session;
	}
	
	public void requestAction() throws IOException {
		String cancelEmailSession = "https://secure.runescape.com/m=password_history/l=3/a=869/"+session+"/password-start-result";
		
		URL url;
	    Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1",8888));
	    url = new URL(cancelEmailSession);
	    HttpsURLConnection con = (HttpsURLConnection)url.openConnection(proxy);

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
	}
}
