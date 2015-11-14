package Negocios;

import Entidades.IP;
import Redes.Servidor_DNS;

public class Pesquisa {
	
	public String enviarIp() {
		
		if(Servidor_DNS.ips.isEmpty()){
			String msgEnviada = Servidor_DNS.ips.get(0).getIp() + ";" + Servidor_DNS.ips.get(0).getPorta() + ";";
			return msgEnviada;
		}
		
		return null;
	}

	public void pegarIp(IP a, int count) {
		
	 	if(count==0){
	 		Servidor_DNS.ips.clear();
	 	}
	 	
	 	System.out.println(a.getIp() + "." + a.getPorta());
	 	Servidor_DNS.ips.add(a);
	 		count++;
		
	}
	
}
