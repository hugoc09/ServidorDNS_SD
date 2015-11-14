package Negocios;


import Entidades.IP;
import Servidor_DNS.Procura_Serv;
import Servidor_DNS.Servidor_DNS;

public class Controle implements ControleDNS {

	private Servidor_DNS servidorDNS;
	private Procura_Serv procuraServ;
	
	
	public Controle() {
		
		try {
			servidorDNS = new Servidor_DNS();
			servidorDNS.start();
			procuraServ = new Procura_Serv();
			procuraServ.start();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	@Override
	public void close() {
		
		try {
			servidorDNS.stop();
			
			procuraServ.stop();
			
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
