package Script;

import javax.swing.JLabel;
import javax.swing.JLayer;
import javax.swing.JTextArea;

public class ThreadLog extends Thread{
	JTextArea area;
	JLabel label1;
	JLabel label2;
	
	
	int i;
	
	
	public ThreadLog(JTextArea area,JLabel label1,JLabel label2) {
		this.label1 = label1;
		this.label2 = label2;
		this.area = area;
		start();
	}



	@Override
	public void run() {
		
		while (true) {
			try {
				label1.setText("Accounts setted: " + Configs.accountsSetteds);
				label2.setText("Accounts Failed: " + Configs.accountsFailed);
				area.setText(Configs.status + "\n");
				sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	
	
}
