package Negocios;


import Entidades.IP;
import Redes.Procura_Serv;
import Redes.Servidor_DNS;

public class Controle implements ControleDNS {

	private Servidor_DNS servidorDNS;

	
	
	public Controle() {
		
		try {
			servidorDNS = new Servidor_DNS();
			servidorDNS.start();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	@Override
	public void close() {
		
		try {
			servidorDNS.stop();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}


	@Override
	public String enviarIp() {
		
		if(servidorDNS.getIps().isEmpty()){
			String msgEnviada = servidorDNS.getIps().get(0).getIp() + ";" + servidorDNS.getIps().get(0).getPorta() + ";";
			return msgEnviada;
		}
		
		return null;
	}


	@Override
	public void pegarIp(IP a, int count) {
		
		
	 	if(count==0){
	 		this.servidorDNS.getIps().clear();
	 	}
	 	
	 	System.out.println(a.getIp() + "." + a.getPorta());
	 		this.servidorDNS.setIp(a);
	 		count++;
		
	}
	
	

}
