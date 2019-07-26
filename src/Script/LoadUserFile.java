package Script;


import java.io.BufferedReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFileChooser;

public class LoadUserFile {

	
	public static ArrayList<String> user = new ArrayList<String>();
	public static ArrayList<String> password = new ArrayList<String>();
	public 	String[] grava;
	public  String linha = "";
	private Scanner ler;
	
	public LoadUserFile () {
		
	}

	public void lerArquivo() throws FileNotFoundException {

		// System.out.printf("Informe o nome de arquivo texto:\n");
		JFileChooser arquivo = new JFileChooser();
		//arquivo.showOpenDialog(arquivo);

		if (arquivo.showOpenDialog(arquivo) == JFileChooser.APPROVE_OPTION) {
			

			try {
				ler = new Scanner(arquivo.getSelectedFile().toString());
				String nome = ler.nextLine(); // ler primeira linha
				System.out.printf("\not countain text file:\n");
				FileReader arq = new FileReader(nome);
				BufferedReader lerArq = new BufferedReader(arq);
				
				
				
				 
				String line;
				while ((line = lerArq.readLine()) != null) {
					linha += ":" + line;
					//line = lerArq.readLine();		
					
				}


				arq.close();
			
				

			} catch (IOException e) {
				System.err.printf("Error Opening file: %s.\n", e.getMessage());
			}

		}
		
		
		//System.out.println(linha);
		grava = linha.split(":");
/*		for(String temp : grava) {
			System.out.println(temp);
		}*/
		
		int a = 0;
		int b = 1;
		int i = 0;
		do {
			
			user.add(grava[b]);
			//proxy1[i] = grava[b];
			//System.out.println(proxy1[i]);
			i++;
			b = b + 2;
			a = a +2;
		}while(b < grava.length);
		
			a = 0;
			i = 0;
		
			do {
			
			password.add(grava[a]);
			//port1[i] = grava[a];
			//System.out.println(port1[i]);
			i++;
			a = a +2;
		}while(a < grava.length);
		
		
		
	}

}
