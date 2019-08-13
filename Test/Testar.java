import java.io.FileNotFoundException;
import java.io.IOException;

import javax.mail.MessagingException;

import Script.CheckEmails;
import Script.Test;

public class Testar {

	public Testar() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws FileNotFoundException, IOException, MessagingException {
		// TODO Auto-generated method stub
		CheckEmails c = new CheckEmails("henriquereissp@gmail.com","henrique190");
		c.checkAction();
	}

}
