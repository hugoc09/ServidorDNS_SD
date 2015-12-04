package Ui;

import java.util.Scanner;

import Negocios.Controlador;

public class Principal {	
	
	public static void main(String[] args) {
		
		ControlDNS control = new Controlador();
		
		control.inicializar();
		
		System.out.println("PRESSIONE <ENTER> para encerrar o Servidor DNS.");
		new Scanner(System.in).nextLine();
				
		control.parar();
		
	}
	
}
