package Ui;

import java.util.Scanner;

import Negocios.Controle;

public class Principal {

	public static void main(String[] args) {
		
			try {
			
			Controle con = new Controle();
			
			System.out.println("PRESSIONE <ENTER> para encerrar o Servidor DNS.");
			new Scanner(System.in).nextLine();
			
			
			System.out.println("Encerrando servidor DNS.");
			con.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
