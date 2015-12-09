package Redes;

import java.net.DatagramPacket;

import Entidades.IP;
import Negocios.Pesquisa;

public class Guarda_Serv implements Runnable{
		 
	private DatagramPacket pkgRecebido;
	private Control controle;

	private boolean inicializado;
		//private boolean executando;

	private Thread  thread;
		
	public Guarda_Serv(DatagramPacket pkgRecebidoParamentro) {
		this.pkgRecebido = pkgRecebidoParamentro;
		this.controle = new Pesquisa();
			
		inicializado = false;
		//executando =false;
			
		open();
	}
		
	private void open(){
		inicializado = true;
			
	}

	private void close() {
			
		controle = null;
			
		inicializado = false;
		//executando = false;
			
		thread = null;
	}
		
	public void start() {
		if(!inicializado){  //executando
			return;
		}
			
			//executando = true;
		thread = new Thread(this);
		thread.start();
	}
		
	public void stop() throws Exception {
			
		//executando = false;
			
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
