package Interface;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import Script.Configs;
import Script.LoadUserFile;

public class Gui {
	LoadUserFile loadUserFile = new LoadUserFile();
	Configs vars = new Configs();
	
GridLayout gridLayout = new GridLayout(0,1);
	
JFrame frame = new JFrame();
JPanel panel = new JPanel(gridLayout);

JButton button = new JButton("Start");
JButton button2 = new JButton("Open");


JLabel jLabel = new JLabel("Load account file.");
JLabel jLabel2 = new JLabel("2Captcha apiKey: ");
JLabel jLabel3 = new JLabel("Set new osrs password: ");

JTextField textField = new JTextField("2Captcha api here");
JTextField textField2 = new JTextField("email@gmail.com");
JTextField textField3 = new JTextField("email password");
JTextField textField4 = new JTextField("your new pass here");

JRadioButton radioButton = new JRadioButton("Change email?");
JRadioButton radioButton2 = new JRadioButton("Change password?");

public Gui(){
	frame.add(panel);
	frame.setSize(300, 300);
	frame.setVisible(true);
	panel.add(jLabel);
	panel.add(button2);
	
	panel.add(jLabel2);
	panel.add(textField);
	
	panel.add(radioButton);
	panel.add(textField2);
	panel.add(textField3);
	textField2.setVisible(false);
	textField3.setVisible(false);
	
	panel.add(radioButton2);
	
	panel.add(jLabel3);
	panel.add(textField4);
	textField4.setVisible(false);
	
	panel.add(button);
	
	button2.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			try {
				loadUserFile.lerArquivo();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	});
	
	
	radioButton.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(radioButton.isSelected() == true) {
				textField2.setVisible(true);
				textField3.setVisible(true);
			}else {
				textField2.setVisible(false);
				textField3.setVisible(false);
			}
			
		}
	});
	
	
	radioButton2.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(radioButton2.isSelected() == true) {
				textField4.setVisible(true);
			}else {
				textField4.setVisible(false);
			}
		}
	});
	
	button.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			vars.isRunning = true;
			
			vars.osrsEmail = loadUserFile.user;
			vars.osrsPassword = loadUserFile.password;
			
			vars.apiKey = textField.getText();
			vars.gmail = textField2.getText();
			vars.gmailPass = textField3.getText();
			vars.newPassword = textField4.getText();
			
			
			System.out.println(radioButton.isSelected() + "\n");
			System.out.println(radioButton2.isSelected() + "\n");
			
			System.out.println(vars.apiKey + "\n");
			System.out.println(vars.gmail + "\n");
			System.out.println(vars.gmailPass + "\n");
			System.out.println(vars.newPassword + "\n");
		}
	});
	
}


}
