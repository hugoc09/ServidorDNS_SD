package Ui;

import java.util.Scanner;

import Negocios.Controle;
import Negocios.ControleDNS;

public class Principal {

	public static void main(String[] args) {
		
			try {
			
			ControleDNS con = new Controle();
			
			System.out.println("PRESSIONE <ENTER> para encerrar o Servidor.");
			new Scanner(System.in).nextLine();
			
			
			System.out.println("Encerrando servidor.");
			con.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
