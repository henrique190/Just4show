package Script;

import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.security.cert.Certificate;
import java.io.*;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;

public class HttpsClient{
	
   
	
   public void testIt(){

      String https_url = "https://runescape.com";
      URL url;
      try {
    	 Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1",8888));
	     url = new URL(https_url);
	     HttpsURLConnection con = (HttpsURLConnection)url.openConnection(proxy);
	     System.out.print(con.getResponseCode());
			
      } catch (MalformedURLException e) {
	     e.printStackTrace();
      } catch (IOException e) {
	     e.printStackTrace();
      }

   }
	
}
	
