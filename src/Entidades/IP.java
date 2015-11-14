package Entidades;

import java.net.InetAddress;

public class IP {
	
	private InetAddress ip;
	private int porta;
	
	public IP(InetAddress ip, int porta) {
		this.ip = ip;
		this.porta = porta;
	}
	public IP(){
	}
	
	public InetAddress getIp() {
		return ip;	
	}
	public void setIp(InetAddress ip) {
		this.ip = ip;
	}
	public int getPorta() {
		return porta;
	}
	public void setPorta(int porta) {
		this.porta = porta;
	}
	
	

}
