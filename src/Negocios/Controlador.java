package Negocios;

import Redes.Procura_Serv;
import Redes.Servidor_DNS;
import Ui.ControlDNS;

public class Controlador implements ControlDNS{

	Servidor_DNS servidorDNS;
	Procura_Serv procuraServ;
	
	@Override
	public void inicializar() {
		try {
			
			System.out.println("Inicializando Servidor DNS...");
			servidorDNS = new Servidor_DNS();
			servidorDNS.start();
			procuraServ = new Procura_Serv();
			procuraServ.start();
			System.out.println("Servidor DNS inicializado!");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void parar() {
		
		try {
			
			System.out.println("Encerrando Servidor DNS...");
			procuraServ.stop();
			servidorDNS.stop();
			System.out.println("Servidor DNS encerrado!");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	
	
}
