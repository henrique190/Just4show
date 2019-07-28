package Script;

import java.io.IOException;
import java.net.CookieManager;
import java.net.HttpCookie;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class GetCookies {
	String session;

	public GetCookies(String session) {
		super();
		this.session = session;
	}
	
	public void action() throws IOException {
		String loginSession = "https://secure.runescape.com/m=weblogin/l=3/a=869/"+session+"/redirect.ws?mod=www&ssl=1&dest=account_settings";
		
		URL url;
	    Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1",8888));
	    url = new URL(loginSession);
	    HttpsURLConnection con = (HttpsURLConnection)url.openConnection();
	    HttpsURLConnection.setFollowRedirects(true);
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
	    
	    String cookiesHeader = con.getHeaderField("Set-Cookie");
	   
	    
	    
	    
	    System.out.println("\n"+cookiesHeader+"\n");    
	    System.out.println("\n"+con.getURL().toString());
	    Configs.status = ("\n"+cookiesHeader+"\n");  
	    		Configs.status = ("\n"+con.getURL().toString());
	}
}
