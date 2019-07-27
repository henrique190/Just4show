package Script;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.mail.MessagingException;

import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;

import Interface.Gui;

@ScriptManifest(author = "Henrique190", category = Category.UTILITY, name = "Just4show", version = 1.0)
public class Main extends AbstractScript {
	Configs vars;
	Gui gui;
	int i = 0;

	@Override
	public void onStart() {
		//loadUserFile = new LoadUserFile();
		vars = new Configs();
		gui = new Gui();
		
		
	}

	@Override
	public int onLoop() {
	return 1000;
	}

}
