package Script;

import TwoCaptchaApi.TwoCaptchaService;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.mail.MessagingException;

import Interface.Gui;

public class Executavel {

	public static void main(String[] args) throws IOException, InterruptedException, MessagingException {
		Configs vars = new Configs();
		Gui gui = new Gui();
		LoadUserFile loadUserFile = new LoadUserFile();
		int i = 0;
		
		while(vars.isRunning == false){
			System.out.println("Setting options");
			Thread.sleep(2500);
		}

		for (String x : vars.osrsEmail) {
			
			System.out.println(vars.osrsEmail.get(i) + ":" + vars.osrsPassword.get(i+1));
			
			
			Login login = new Login(vars.osrsEmail.get(i), vars.osrsPassword.get(i+1), vars.apiKey);
			login.loginRs();
			
			
			if (vars.loggedin == true) {
				CancelVerification cancelVerification = new CancelVerification(login.getSession());
				cancelVerification.cancelAction();

				if(vars.checkedChangeEmail == true) {
					ChangeEmail changeEmail = new ChangeEmail(login.getSession(), vars.gmail);
					changeEmail.action();
					if (vars.requestedEmailChange == true) {
						System.out.println("Sleeping 40 seconds");
						Thread.sleep(40000);
						CheckEmails checkEmails = new CheckEmails(vars.gmail, vars.gmailPass);
						checkEmails.checkAction();
						ValidadeNewEmail validadeNewEmail = new ValidadeNewEmail(checkEmails.getUrlCatch());
						validadeNewEmail.validadelAction();
					}
				}
				
				if(vars.checkedChangePassword == true) {
					RequestPasswordChange requestPasswordChange = new RequestPasswordChange(login.getSession());
					requestPasswordChange.requestAction();
					System.out.println("Sleeping 40 seconds");
					Thread.sleep(40000);
					CheckEmails checkEmails = new CheckEmails(vars.gmail, vars.gmailPass);
					checkEmails.checkAction();
					ChangePassword changePassword = new ChangePassword(checkEmails.getUrlCatch(), vars.newPassword);
					changePassword.changelAction();
					
					if (vars.passwordChanged == true) {

						String diretorio = System.getProperty("user.dir");
						File file = new File(diretorio, "accsChanged.txt");
						if (!file.exists()) {
							file.createNewFile();
						}
						try (FileWriter fw = new FileWriter(file, true);
								BufferedWriter bw = new BufferedWriter(fw);
								PrintWriter out = new PrintWriter(bw)) {
							out.println(vars.osrsEmail.get(i) + ":" + vars.osrsEmail.get(i+1));
						} catch (IOException e) {
							System.out.println("Error writing file");
						}

					}
				
				}
				
					

					

			}else {
				String diretorio = System.getProperty("user.dir");
				File file = new File(diretorio, "FailedAccounts.txt");
				if (!file.exists()) {
					file.createNewFile();
				}
				try (FileWriter fw = new FileWriter(file, true);
						BufferedWriter bw = new BufferedWriter(fw);
						PrintWriter out = new PrintWriter(bw)) {
					out.println(vars.osrsEmail.get(i) + ":" + vars.osrsEmail.get(i+1));
				} catch (IOException e) {
					System.out.println("Error writing file");
				}
			}
			
			
			
			vars.loggedin = false;
			vars.verificanEmailcanceled = false;
			vars.requestedEmailChange = false;
			vars.passwordChanged = false;
			vars.emailChecked = false;
			vars.validadeEmail = false;
			i++;
		}
		
		
		
		
		

		

	}
}
