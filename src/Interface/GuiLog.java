package Interface;

import java.awt.GridLayout;
import java.rmi.server.Operation;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

import Script.ThreadLog;

public class GuiLog {

	GridLayout gridLayout = new GridLayout(0,1);
	
	JFrame frame = new JFrame();
	JPanel panel = new JPanel(gridLayout);

	JTextArea area = new JTextArea();
	JLabel jLabel = new JLabel("Accounts setted:");
	JLabel accountsFailed = new JLabel("Accounts Failed:");
	ThreadLog log = new ThreadLog(area,jLabel,accountsFailed);
	

	public GuiLog() {
		frame.add(panel);
		frame.setSize(600, 100);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		panel.add(jLabel);
		panel.add(accountsFailed);
		panel.add(area);
		
		
		
		
		
		
		
	}
	
	
	
	
	
}
