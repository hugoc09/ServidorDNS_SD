package Redes;

import java.net.DatagramPacket;

import Entidades.IP;
import Negocios.Pesquisa;

public class Guarda_Serv implements Runnable{
		 
	private DatagramPacket pkgRecebido;
	private Control controle;

	private boolean inicializado;

	private Thread  thread;
		
	public Guarda_Serv(DatagramPacket pkgRecebidoParamentro) {
		this.pkgRecebido = pkgRecebidoParamentro;
		this.controle = new Pesquisa();
			
		inicializado = false;
			
		open();
	}
		
	private void open(){
		inicializado = true;
			
	}

	private void close() {
			
		controle = null;
			
		inicializado = false;
			
		thread = null;
	}
		
	public void start() {
		if(!inicializado){ 
			return;
		}
			
		thread = new Thread(this);
		thread.start();
	}
		
	public void stop() throws Exception {
			
		if(thread!=null){
		thread.join();
		}
	}
		
	@Override
	public void run() {
			
		try {
			IP a = new IP(pkgRecebido.getAddress(), pkgRecebido.getPort());
		 	controle.pegarIp(a);	
		 	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		close();
	}

}
