package Negocios;


import Entidades.IP;

public interface ControleDNS {
	
	public void close();
	public String enviarIp();
	public void pegarIp(IP a, int count);

}
