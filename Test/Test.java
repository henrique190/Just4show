import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.swing.JOptionPane;

import Script.Configs;
import Script.FindUrls;

public class Test extends Thread{
	
	int messageCount;
	Session session;
	Properties props;
	private  Store store;
	private  Folder inbox;
	FindUrls findUrls = new FindUrls();
	String urlCatch;
	private  Message lastEmail;

	
	

	public Test() {
		super();
		start();
	}

	public void run(){
		
		try {
			while (true) {
				checkEmail();
				sleep(200);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public void checkEmail() {
		try {
			props = new Properties();	
			props.put("mail.store.protocol","imaps");
			session = Session.getDefaultInstance(props, null);
			store = session.getStore("imaps");
			store.connect("imap.gmail.com", "henriquereissp@gmail.com", "henrique190");
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro de conexão: " +e);
		}

		try {
			inbox = store.getFolder("Inbox");
			inbox.open(Folder.READ_ONLY);
			messageCount = inbox.getUnreadMessageCount();
			inbox.close();
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ler pasta: " + e);
		}


		try {
			while (inbox.getUnreadMessageCount() == messageCount) {
				inbox = store.getFolder("Inbox");
				inbox.open(Folder.READ_ONLY);
				inbox.close();
			}
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao receber novo email: "+e);
		}


		try {
			if (inbox.getUnreadMessageCount() > messageCount) {
				System.out.println("New mail has arrived");
				inbox = store.getFolder("Inbox");
				inbox.open(Folder.READ_ONLY);

				lastEmail = inbox.getMessage(inbox.getMessageCount());
				System.out.println("Mail Subject:- " + lastEmail.getSubject());
				Configs.status = ("Mail Subject:- " + lastEmail.getSubject());
				String url = getMessageContent(lastEmail);

				System.out.println("Reading eamil to get link");
				Configs.status =("Reading eamil to get link");
				List<String> extractedUrls = FindUrls.extractUrls(url);

				boolean findUrl = false;

				for (String x : extractedUrls) {
					if (findUrl == false) {
						if (x.contains("secure.runescape.com")) {

							urlCatch = x;
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
		}catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "Erro ao receber novo email2: "+e);
		}
	}


	private  String getMessageContent(Message message) throws MessagingException {
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


	public Test(String urlCatch) {
		super();
		this.urlCatch = urlCatch;
	}
	
	

}
