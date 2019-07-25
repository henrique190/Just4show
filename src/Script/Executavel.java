package Script;

import TwoCaptchaApi.TwoCaptchaService;

import java.io.IOException;

import javax.mail.MessagingException;


public class Executavel {
    public static void main(String[] args) throws IOException, InterruptedException, MessagingException {

      Login login = new Login("55nctjku%40gmail.com","031119911Hr","379ead4ad08eec6c7985ee62e62b56bf");
      login.loginRs();
      
      
      
      // CancelVerification cancelVerification = new CancelVerification(login.getSession());
      //cancelVerification.cancelAction();
      //ChangeEmail changeEmail = new ChangeEmail(login.getSession());
     // changeEmail.action();
      //CheckEmails checkEmails = new CheckEmails();
      //checkEmails.checkAction();
      
      
      //RequestPasswordChange requestPasswordChange = new RequestPasswordChange(login.getSession());
      //requestPasswordChange.requestAction();
      
     // ChangePassword changePassword = new ChangePassword(checkEmails.getUrlCatch());
      //changePassword.changelAction();
    	
    	
    	
    }
}
