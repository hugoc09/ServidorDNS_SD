package Ui;

import java.util.Scanner;

import Redes.Servidor_DNS;

public class Principal {	
	
	public static void main(String[] args) {
		
			try {
			Servidor_DNS servidorDNS = new Servidor_DNS();
			servidorDNS.start();
			
			System.out.println("PRESSIONE <ENTER> para encerrar o Servidor DNS.");
			new Scanner(System.in).nextLine();
			
			
			System.out.println("Encerrando servidor DNS.");
			servidorDNS.stop();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
