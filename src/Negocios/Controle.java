package Negocios;


import Entidades.IP;
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


	
	public String enviarIp() {
		
		if(servidorDNS.getIps().isEmpty()){
			String msgEnviada = servidorDNS.getIps().get(0).getIp() + ";" + servidorDNS.getIps().get(0).getPorta() + ";";
			return msgEnviada;
		}
		
		return null;
	}



	public static void pegarIp(IP a, int count) {
		
	 	if(count==0){
	 		Servidor_DNS.ips.clear();
	 	}
	 	
	 	System.out.println(a.getIp() + "." + a.getPorta());
	 	Servidor_DNS.ips.add(a);
	 		count++;
		
	}
	
	

}
