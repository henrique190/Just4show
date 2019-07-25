package TwoCaptchaApi;

import java.io.IOException;



	public class TwoCaptchaService {
		
		private String apiKey;
		private String googleKey;
		private String pageUrl;
		
		private String proxyIp;
		private String proxyPort;
		private String proxyUser;
		private String proxyPw;
		private ProxyType proxyType;
		
		private HttpWrapper hw;
		
		
		public TwoCaptchaService(String apiKey, String googleKey, String pageUrl) {
			this.apiKey = apiKey;
			this.googleKey = googleKey;
			this.pageUrl = pageUrl;
			hw = new HttpWrapper();
		}
		
		public TwoCaptchaService(String apiKey, String googleKey, String pageUrl, String proxyIp, String proxyPort, ProxyType proxyType) {
			this(apiKey, googleKey, pageUrl);
			this.proxyIp = proxyIp;
			this.proxyPort = proxyPort;
			this.proxyType = proxyType;
		}
		
		public TwoCaptchaService(String apiKey, String googleKey, String pageUrl, String proxyIp, String proxyPort,
				String proxyUser, String proxyPw, ProxyType proxyType) {
			this(apiKey,googleKey,pageUrl);
			this.proxyIp = proxyIp;
			this.proxyPort = proxyPort;
			this.proxyUser = proxyUser;
			this.proxyPw = proxyPw;
			this.proxyType = proxyType;
		}
		
		public String solveCaptcha() throws InterruptedException, IOException {
			System.out.println("Sending recaptcha challenge to 2captcha.com");
			
			String parameters = "key=" + apiKey
					+ "&method=userrecaptcha"
					+ "&googlekey=" + googleKey
					+ "&pageurl=" + pageUrl;
					
			if (proxyIp != null) {
				if (proxyUser != null) {
					parameters += "&proxy=" 
							+ proxyUser + ":" + proxyPw 
							+ "@"
							+ proxyIp + ":" + proxyPort;
				} else {
					parameters += "&proxy=" 
							+ proxyIp + ":" + proxyPort;
				}
				
				parameters += "&proxytype=" + proxyType;
			}
			hw.get("http://2captcha.com/in.php?" + parameters);
			
			String captchaId = hw.getHtml().replaceAll("\\D", "");
			int timeCounter = 0;
			
			do {
				hw.get("http://2captcha.com/res.php?key=" + apiKey 
						+ "&action=get"
						+ "&id=" + captchaId);
				
				Thread.sleep(1000);
				
				timeCounter++;
				System.out.println("Waiting for captcha to be solved... elapsed " + timeCounter);
			} while(hw.getHtml().contains("NOT_READY"));
			System.out.println("It took "  + timeCounter + " seconds to solve the captcha");
			String gRecaptchaResponse = hw.getHtml().replaceAll("OK\\|", "").replaceAll("\\n", "");
			return gRecaptchaResponse;
		}

		public String getApiKey() {
			return apiKey;
		}
		
		public void setApiKey(String apiKey) {
			this.apiKey = apiKey;
		}
		
		public String getGoogleKey() {
			return googleKey;
		}
		
		public void setGoogleKey(String googleKey) {
			this.googleKey = googleKey;
		}
		
		public String getPageUrl() {
			return pageUrl;
		}
		
		public void setPageUrl(String pageUrl) {
			this.pageUrl = pageUrl;
		}
		
		public String getProxyIp() {
			return proxyIp;
		}
		
		public void setProxyIp(String proxyIp) {
			this.proxyIp = proxyIp;
		}
		
		public String getProxyPort() {
			return proxyPort;
		}
		
		public void setProxyPort(String proxyPort) {
			this.proxyPort = proxyPort;
		}
		
		public String getProxyUser() {
			return proxyUser;
		}
		
		public void setProxyUser(String proxyUser) {
			this.proxyUser = proxyUser;
		}
		
		public String getProxyPw() {
			return proxyPw;
		}
		
		public void setProxyPw(String proxyPw) {
			this.proxyPw = proxyPw;
		}	
		
		public ProxyType getProxyType() {
			return proxyType;
		}
		
		public void setProxyType(ProxyType proxyType) {
			this.proxyType = proxyType;
		}
}