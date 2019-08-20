package Proxy;



import Script.RandomUserAgent;

import javax.net.ssl.HttpsURLConnection;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;

public class VerificaProxy {

	private String status;
	private String proxyip;
	private String port;
	private String state;
	Proxy proxy = null;





	public String executarProxy(Proxy proxy) throws MalformedURLException {
		URL obj = new URL("https://runescape.com/");

		try {
			HttpURLConnection con = (HttpURLConnection) obj.openConnection(proxy);
			String USER_AGENT = RandomUserAgent.getRandomUserAgent();
			con.setConnectTimeout(8000);
			con.setReadTimeout(8000);
			con.setRequestMethod("GET");
			con.setRequestProperty("Host", "secure.runescape.com");
			con.setRequestProperty("User-Agent", USER_AGENT);
			con.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
			con.setRequestProperty("Accept-Language", "en-US,en);q=0.5");
			con.setRequestProperty("Accept-Encoding", "gzip, deflate, br");
			con.setRequestProperty("Referer", "http://oldschool.runescape.com/");
			if (200 == con.getResponseCode()) {
				status = "ok";
			} else {
				status = "erro";
			}
			con.disconnect();
			return status;



		} catch (Exception e) {
			status = "erro";

		}
		return status;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
