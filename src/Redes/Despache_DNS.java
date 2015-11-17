package Redes;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

import Negocios.Pesquisa;


public class Despache_DNS implements Runnable{
	
	private DatagramSocket servidorSocket;
	 
	private DatagramPacket pkgEnviado;
	private DatagramPacket pkgCliente;
	private Control controle;

	private boolean inicializado;
	private boolean executando;

	private Thread  thread;
	
	public Despache_DNS(DatagramSocket datagramSocket, DatagramPacket pkgRecebidoParamentro) {
		this.servidorSocket = datagramSocket;
		this.pkgCliente = pkgRecebidoParamentro;
		this.controle = new Pesquisa();
		
		inicializado = false;
		executando =false;
		
		open();
	}
	
	private void open(){
			
		inicializado = true;	
	}

	//Não está sendo usado
	private void close() {
		
		try {
			servidorSocket.close();
		} catch (Exception e) {
			System.out.println(e);			
		}
		
		servidorSocket = null;
		controle = null;
		
		inicializado = false;
		executando = false;
		
		thread = null;
	}
	
	public void start() {
		if(!inicializado || executando){
			return;
		}
		
		executando = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public void stop() throws Exception {
		
		executando = false;
		
		if(thread!=null){
		thread.join();
		}
	}
	
	@Override
	public void run() {
		
		try {
			String msgEnviada = controle.enviarIp();
			
			if(msgEnviada!=null){
			pkgEnviado = new DatagramPacket(msgEnviada.getBytes(),msgEnviada.length(), pkgCliente.getAddress(), pkgCliente.getPort());
			servidorSocket.send(pkgEnviado);
			}else{
				System.out.println("Nada enviado! <DNS>");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		//close();
	}

}
