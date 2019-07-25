package TwoCaptchaApi;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class HttpWrapper {
	
    private boolean printHeaders = false;
    private String html;
    private int responseCode = 0;
    
    public HttpWrapper() {
        html = "";
    }
    
    public void get(String url) {
        
    	try {
            URL url_ = new URL(url);
            HttpURLConnection conn;
                        
            conn = (HttpURLConnection) url_.openConnection();
            conn.setRequestMethod("GET");
            conn.setAllowUserInteraction(false);
            conn.setDoOutput(false);
            conn.setInstanceFollowRedirects(false);
            
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:47.0) Gecko/20100101 Firefox/47.0");
            conn.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
            conn.setRequestProperty("Accept-Language", "en-US,en;q=0.8");
            conn.setRequestProperty("Connection", "keep-alive");
             
            String headers = "";
            
            if (printHeaders) {
	            for(String key: conn.getHeaderFields().keySet())
	                headers += ((key != null)?key + ": ":"") + conn.getHeaderField(key) + "\n";
            }
            
            responseCode = conn.getResponseCode();
            
            BufferedReader d = new BufferedReader(new InputStreamReader(new DataInputStream(conn.getInputStream())));
            String result = "";
            String line = null;
            while ((line = d.readLine()) != null) {
            	line = new String(line.getBytes(),"UTF-8");
            	result += line + "\n";
            }

            d.close();
            
            if (printHeaders) {
                setHtml(headers + "\n" + result);
            } else {
                setHtml(result);
            }
        } catch (IOException e) {
            throw new IllegalStateException("An IOException occurred:" + "\n" + e.getMessage());
        }
    }
 
    public String getHtml() {
        return this.html;
    }
    
    private void setHtml(String html) {
        this.html = html;
    }
    
    public void setPrintHeaders(boolean trueOrFalse) {
    	printHeaders = trueOrFalse;
    }
    
    public int getResponseCode() {
    	return responseCode;
    }
}