package Script;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import TwoCaptchaApi.ProxyType;
import TwoCaptchaApi.TwoCaptchaService;

import javax.net.ssl.HttpsURLConnection;

public class AppealAccount {

	Configs vars = new Configs();
	String urlRs = "https://secure.runescape.com/m=accountappeal/l=3/a=869/passwordrecovery";
	String osrsEmail = "";
	String apiKey = "379ead4ad08eec6c7985ee62e62b56bf";
	String session = "";
	String location = "";
	String responseToken = "";
	String proxyIP;
	int port;
	int loginStatus;



	public int getLoginStatus() {
		return loginStatus;
	}

	public void setLoginStatus(int loginStatus) {
		this.loginStatus = loginStatus;
	}

	public String getSession() {
		return session;
	}

	public void setSession(String session) {
		this.session = session;
	}

	public AppealAccount(String proxyIP,int port,String osrsEmail) {
		this.proxyIP = proxyIP;
		this.port = port;
		this.osrsEmail = osrsEmail;
	}

	private String getRecaptcha(String apiKey) {
		String googleKey = "6Lcsv3oUAAAAAGFhlKrkRb029OHio098bbeyi_Hv"; ////
		//String pageUrl = "https://secure.runescape.com/m=weblogin/l=3/login.ws";
		TwoCaptchaService service = new TwoCaptchaService(apiKey, googleKey, urlRs);

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

	public void action() throws IOException, InterruptedException {
		CookieManager cookieManager = new CookieManager();
		CookieHandler.setDefault(cookieManager);


		String recaptchaResponse = getRecaptcha(apiKey);
		String params = "email=" + osrsEmail + "&password-recovery-submit=" + "password-recovery" + "&g-recaptcha-response=" + recaptchaResponse;
		URL url = new URL("https://secure.runescape.com/m=accountappeal/l=3/a=869/passwordrecovery");
		Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyIP,port));

		HttpURLConnection con = (HttpURLConnection) url.openConnection(proxy);
		HttpURLConnection.setFollowRedirects(true);
		String USER_AGENT = RandomUserAgent.getRandomUserAgent();
		con.setInstanceFollowRedirects(true);
		con.setRequestMethod("GET");
		con.setRequestProperty("Host", "secure.runescape.com");
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		con.setRequestProperty("Accept-Language", "en-US,en);q=0.5");
		con.setRequestProperty("Referer", "http://oldschool.runescape.com/");
		con.setDoOutput(true);
		con.setDoInput(true);

		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(params);
		wr.flush();
		wr.close();


		BufferedReader br;
		if (200 <= con.getResponseCode() && con.getResponseCode() <= 299) {
			br = new BufferedReader(new InputStreamReader(con.getInputStream()));
		} else {
			br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
		}
		StringBuilder sb;
		sb = new StringBuilder();
		String output;
		while ((output = br.readLine()) != null) {
			sb.append(output);
		}


		System.out.println(sb.toString());
		System.out.println(con.getURL());


		String location = "" + con.getURL().toString();

		if(location.contains("account-identified")) {

			int um = location.indexOf("");
			int dois = location.lastIndexOf("/");
			location = location.substring(um, dois);
			location = location+"/email-confirmation";
			System.out.println(location);

			url = new URL(location);
			con = (HttpURLConnection) url.openConnection(proxy);
			HttpURLConnection.setFollowRedirects(true);
			USER_AGENT = RandomUserAgent.getRandomUserAgent();
			con.setRequestMethod("GET");
			con.setRequestProperty("Host", "secure.runescape.com");
			con.setRequestProperty("User-Agent",USER_AGENT);
			con.setRequestProperty("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
			con.setRequestProperty("Accept-Language", "en-US,en);q=0.5");
			//  con.setRequestProperty("Accept-Encoding", "gzip, deflate, br");
			con.setRequestProperty("Referer","http://oldschool.runescape.com/");
			con.setDoOutput(true);
			con.setDoInput(true);


			if (200 <= con.getResponseCode() && con.getResponseCode() <= 299) {
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else {
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			sb = new StringBuilder();

			while ((output = br.readLine()) != null) {
				sb.append(output);
			}


			System.out.println(sb.toString());

			if(sb.toString().contains("Um link de redefinição de senha foi enviado")) {
				System.out.println("send email OK");

			}






		}else if(location.contains("email-confirmation")) {

		}else if(location.contains("message.ws")) {
			System.out.println("Limit reach, try agin after later");

		}





		con.disconnect();

	}


}
