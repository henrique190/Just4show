package Script;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.mail.MessagingException;

public class Test {

	public static void main(String[] args) throws FileNotFoundException, IOException, MessagingException {
		// TODO Auto-generated method stub
		
		CheckEmails checkEmails = new CheckEmails();
		checkEmails.checkAction();
		
		ValidadeNewEmail validadeNewEmail = new ValidadeNewEmail(checkEmails.getUrlCatch());
		validadeNewEmail.validadelAction();
	}

}
