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

import TwoCaptchaApi.ProxyType;
import TwoCaptchaApi.TwoCaptchaService;

import javax.net.ssl.HttpsURLConnection;

public class Login {
	Configs vars = new Configs();
	String urlRs = "https://secure.runescape.com/m=weblogin/l=3/login.ws";
	String osrsEmail = "";
	String osrsSenha = "";
	String apiKey = "";
	String session = "";
	String location = "";
	String responseToken = "";

	public String getSession() {
		return session;
	}

	public void setSession(String session) {
		this.session = session;
	}

	public Login(String osrsEmail, String osrsSenha, String apiKey) {
		this.osrsEmail = osrsEmail;
		this.osrsSenha = osrsSenha;
		this.apiKey = apiKey;
	}

	private String getRecaptcha(String apiKey) {
		String googleKey = "6Lcsv3oUAAAAAGFhlKrkRb029OHio098bbeyi_Hv"; ////
		String pageUrl = "https://secure.runescape.com/m=weblogin/l=3/login.ws";
		TwoCaptchaService service = new TwoCaptchaService(apiKey, googleKey, pageUrl, "127.0.0.1", "8888",
				ProxyType.HTTP);

		try {
			responseToken = service.solveCaptcha();
			System.out.println("The response token is: " + responseToken);
			 Configs.status =("The response token is: " + responseToken);
			if (!responseToken.contains("ERROR")) {
				return responseToken;
			}
		} catch (InterruptedException e) {
			System.out.println("Error grabbing key:");
			Configs.status = ("Error grabbing key:");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error grabbing key:");
			e.printStackTrace();
		}
		return null;
	}

	public void loginRs() throws IOException, InterruptedException {

		String recaptchaResponse = getRecaptcha(apiKey);

		String params = "username="+osrsEmail+"&password="+osrsSenha+"&g-recaptcha-response="+recaptchaResponse+"&theme=dual&mod=www&ssl=1&dest=account_settings";

		URL url;
		Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 8888));
		url = new URL("https://secure.runescape.com/m=weblogin/l=3/login.ws");
		HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
		HttpsURLConnection.setFollowRedirects(true);
		String USER_AGENT = RandomUserAgent.getRandomUserAgent();
		con.setConnectTimeout(20000);
		con.setReadTimeout(20000);
		con.setInstanceFollowRedirects(true);
		con.setRequestMethod("POST");
		con.setRequestProperty("Host", "secure.runescape.com");
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		con.setRequestProperty("Accept-Language", "en-US,en);q=0.5");
		con.setRequestProperty("Accept-Encoding", "gzip, deflate, br");
		con.setRequestProperty("Referer", "http://oldschool.runescape.com/");
		con.setDoOutput(true);
		con.setDoInput(true);

		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		System.out.println(params);
		Configs.status =(params);

		wr.writeBytes(params);
		wr.flush();
		wr.close();

		System.out.println(con.getResponseCode());
		Configs.status = ""+(con.getResponseCode());
		String location = "" + con.getURL().toString();

		if (location.contains("account_settings") || location.contains("set_address")) {
			System.out.println("User: " + osrsEmail + " Logged in");
			Configs.status = ("User: " + osrsEmail + " Logged in");
			String cookie = location;
			int um = cookie.indexOf("s=");
			int dois = cookie.lastIndexOf("/");
			session = cookie.substring(um, dois);
			vars.loggedin = true;
		} else {
			System.out.println("User or password invalid");
			Configs.status =("User or password invalid");
			vars.loggedin = false;
		}

		con.disconnect();

	}

}
