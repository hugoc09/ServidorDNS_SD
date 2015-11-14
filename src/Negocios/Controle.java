package Negocios;



import Redes.Servidor_DNS;

public class Controle  {

	private Servidor_DNS servidorDNS;
	
	public Controle() {
		
		try {
			servidorDNS = new Servidor_DNS();
			servidorDNS.start();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	public void close() {
		
		try {
			servidorDNS.stop();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
