package Script;

import TwoCaptchaApi.TwoCaptchaService;

import java.io.IOException;

import javax.mail.MessagingException;

public class Executavel {

	public static void main(String[] args) throws IOException, InterruptedException, MessagingException {
		Configs vars = new Configs();

		Login login = new Login("ha2lyoi4@gmail.com", "03111991Hr", "379ead4ad08eec6c7985ee62e62b56bf");
		login.loginRs();

		if (vars.loggedin == true) {
			CancelVerification cancelVerification = new CancelVerification(login.getSession());
			cancelVerification.cancelAction();
			
			ChangeEmail changeEmail = new ChangeEmail(login.getSession(),"henriquereissp@gmail.com");
			changeEmail.action();
			
			if (vars.emailchanged == true) {
				System.out.println("Sleeping 40 seconds");
				Thread.sleep(40000);
				CheckEmails checkEmails = new CheckEmails();
				checkEmails.checkAction();
				ValidadeNewEmail validadeNewEmail = new ValidadeNewEmail(checkEmails.getUrlCatch());
				validadeNewEmail.validadelAction();
		
				if(vars.validadeEmail == true) {
					RequestPasswordChange requestPasswordChange = new RequestPasswordChange(login.getSession());
					requestPasswordChange.requestAction();
					System.out.println("Sleeping 40 seconds");
					Thread.sleep(40000);
					checkEmails = new CheckEmails();
					checkEmails.checkAction();
					ChangePassword changePassword = new ChangePassword(checkEmails.getUrlCatch());
					changePassword.changelAction();
				}
			}
			
			
		}

		
		
		
	


	}
}
