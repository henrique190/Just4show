package Script;

import Interface.Gui;
import Interface.GuiLog;
import Proxy.ProxyList;
import Proxy.VerificaProxy;
import TwoCaptchaApi.ProxyType;

import javax.mail.MessagingException;
import java.io.*;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;

public class Executavel2 {

	public static void main(String[] args) throws IOException, MessagingException, InterruptedException {

		CheckEmails check = new CheckEmails("henriquereissp@gmail.com","henrique190");
		check.checkAction();

		RecoverAccount recoverPassword = new RecoverAccount(check.getUrlCatch(), Configs.newPassword, "127.0.0.1", 8889);
		recoverPassword.changelAction();


	}

}
