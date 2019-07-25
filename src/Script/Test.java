package Script;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.mail.MessagingException;

public class Test {

	public static void main(String[] args) throws FileNotFoundException, IOException, MessagingException {
		// TODO Auto-generated method stub
		LoadUserFile file = new LoadUserFile();
		
		int i = 0;
		for (String x : file.user) {
			System.out.println(file.user.get(i) + ":" + file.password.get(i+1));
			i++;
		}
		
		
		
		
		

	}

}
