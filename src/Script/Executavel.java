package Script;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.mail.MessagingException;

import Interface.Gui;
import Interface.GuiLog;

public class Executavel {

	public static void main(String[] args) {
		Gui gui = new Gui();
		LoadUserFile loadUserFile = new LoadUserFile();
		GuiLog guiLog = new GuiLog();
		CheckEmails check = new CheckEmails(Configs.gmail, Configs.gmailPass);
		
		while(Configs.isRunning == false){
			System.out.println("Setting options");
			Configs.status = ("Setting options");
			try {
				Thread.sleep(2500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for (int i = 0; i < Configs.osrsEmail.size(); i++) {
			System.out.println(Configs.osrsEmail.get(i));
			System.out.println(Configs.osrsPassword.get(i+1));
			System.out.println(Configs.gmail);
			System.out.println(Configs.gmailPass);
			System.out.println(Configs.newPassword);
			try {
				Login2 login = new Login2(Configs.osrsEmail.get(i),Configs.osrsPassword.get(i+1),Configs.apiKey);
				login.loginRs();
				if (login.getLoginStatus() == 1) {
					CancelVerification cancelVerification = new CancelVerification(login.getSession());
					cancelVerification.cancelAction();

					ChangeEmail changeEmail = new ChangeEmail(login.getSession(), Configs.gmail);

					changeEmail.action();
					Thread.sleep(40000);
					Configs.status = "sleeping 40 seconds";
					check = new CheckEmails(Configs.gmail, Configs.gmailPass);
					check.checkAction();

					ValidadeNewEmail v = new ValidadeNewEmail(check.urlCatch);

					v.action();

					RequestPasswordChange requestPasswordChange = new RequestPasswordChange(login.getSession());

					requestPasswordChange.requestAction();
					Thread.sleep(40000);
					Configs.status = "sleeping 40 seconds";
					check = new CheckEmails(Configs.gmail, Configs.gmailPass);
					check.checkAction();
					ChangePassword changePassword = new ChangePassword(check.getUrlCatch(), Configs.newPassword);
					changePassword.changelAction();
					
					String diretorio = System.getProperty("user.dir");
					File file = new File(diretorio, "accsChanged.txt");
					if (!file.exists()) {
						file.createNewFile();
					}
					try (FileWriter fw = new FileWriter(file, true);
							BufferedWriter bw = new BufferedWriter(fw);
							PrintWriter out = new PrintWriter(bw)) {
						out.println(Configs.osrsEmail.get(i) + ":" + Configs.newPassword);
						Configs.accountsSetteds += 1;
					} catch (IOException e) {
						System.out.println("Error writing file");
						Configs.status = ("Error writing file");
					}
					
					

				} else if (login.getLoginStatus() == 2) {
					AppealAccount a = new AppealAccount();

					a.action();
					Thread.sleep(40000);
					Configs.status = "sleeping 40 seconds";
					check = new CheckEmails(Configs.gmail, Configs.gmailPass);
					check.checkAction();
					ValidadeNewEmail v = new ValidadeNewEmail(check.urlCatch);
					v.action();
					
					String diretorio = System.getProperty("user.dir");
					File file = new File(diretorio, "accsUnlockeds.txt");
					if (!file.exists()) {
						file.createNewFile();
					}
					try (FileWriter fw = new FileWriter(file, true);
							BufferedWriter bw = new BufferedWriter(fw);
							PrintWriter out = new PrintWriter(bw)) {
						out.println(Configs.osrsEmail.get(i) + ":" + Configs.newPassword);
						Configs.accountsSetteds += 1;
					} catch (IOException e) {
						System.out.println("Error writing file");
						Configs.status = ("Error writing file");
					}

				} else if (login.getLoginStatus() == 3) {
					String diretorio = System.getProperty("user.dir");
					File file = new File(diretorio, "accsFaileds.txt");
					if (!file.exists()) {
						file.createNewFile();
					}
					try (FileWriter fw = new FileWriter(file, true);
							BufferedWriter bw = new BufferedWriter(fw);
							PrintWriter out = new PrintWriter(bw)) {
						out.println(Configs.osrsEmail.get(i) + ":" + Configs.newPassword);
						Configs.accountsFailed += 1;
					} catch (IOException e) {
						System.out.println("Error writing file");
						Configs.status = ("Error writing file");
					}
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		

	}

}
