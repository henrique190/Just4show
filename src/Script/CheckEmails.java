package Script;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;

public class CheckEmails {
	String urlCatch;
	FindUrls findUrls = new FindUrls();
	String usermail;
	String password;
	
	public CheckEmails (String usermail,String password) {
		this.usermail = usermail;
		this.password = password;
	}
	
	public String getUrlCatch() {
		return urlCatch;
		
	}

	public void setUrlCatch(String urlCatch) {
		this.urlCatch = urlCatch;
	}

	public void checkAction() throws FileNotFoundException, IOException, MessagingException {
		// TODO Auto-generated method stub

		Properties props = new Properties();
		props.load(new FileInputStream(new File("C:\\smtp.properties")));
		Session session = Session.getDefaultInstance(props, null);

		Store store = session.getStore("imaps");
		store.connect("smtp.gmail.com", usermail, password);

		Folder inbox = store.getFolder("Inbox");
		inbox.open(Folder.READ_ONLY);

		Message lastEmail = inbox.getMessage(inbox.getMessageCount());
		System.out.println("Mail Subject:- " + lastEmail.getSubject());

		String url = getMessageContent(lastEmail);

		System.out.println("Reading eamil to get link");

		List<String> extractedUrls = findUrls.extractUrls(url);

		boolean findUrl = false;
	
		for (String x : extractedUrls) {
			if (findUrl == false) {
				if (x.contains("secure.runescape.com")) {
					
					this.urlCatch = x;
					findUrl = true;
				}
			}

		}
		 
		if(url.contains("utm")) {
			int dois = urlCatch.indexOf("?utm");
			int um = 0;
			urlCatch = urlCatch.substring(um,dois);
		}
		

		System.out.println(urlCatch);

	}

	private String getMessageContent(Message message) throws MessagingException {
		try {
			Object content = message.getContent();
			if (content instanceof Multipart) {
				StringBuffer messageContent = new StringBuffer();
				Multipart multipart = (Multipart) content;
				for (int i = 0; i < multipart.getCount(); i++) {
					Part part = multipart.getBodyPart(i);
					// if (part.isMimeType("text/plain")) {
					messageContent.append(part.getContent().toString());
					// }
				}
				return messageContent.toString();
			}
			return content.toString();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}


}
