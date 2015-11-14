package Redes;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

import Entidades.IP;



public class Servidor_DNS implements Runnable{
	
	private DatagramSocket servidorSocket;
	private Procura_Serv procuraServ;
	 
	private DatagramPacket pkgRecebido;

	private boolean inicializado;
	private boolean executando;
	
	public static List<IP> ips;

	private Thread  thread;
	
	public Servidor_DNS() throws Exception {
		ips = new ArrayList<IP>();
		
		inicializado = false;
		executando   = false;

		open();
	}
	
	private void open()throws Exception{
		servidorSocket = new DatagramSocket(2526);
		
		procuraServ = new Procura_Serv(servidorSocket);
		procuraServ.start();
		
		inicializado = true;
	}
	
	private void close() {
		
		try {
			procuraServ.stop();
		} catch (Exception e) {
			System.out.println(e);
		}
	
		try {
			servidorSocket.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		
		servidorSocket = null;
		
		ips = null;
		
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
	
	public void stop() throws Exception{
		executando = false;
		
		if(thread != null){
		thread.join();
		}
	}
	
	@Override
	public void run() {
		
	System.out.println("Aguardadando pedido de conexao...");
	
	
		while(executando){
			
			byte[] dadosRecebidos = new byte[1024];
			pkgRecebido = new DatagramPacket(dadosRecebidos, dadosRecebidos.length);
			
			try {
	
			servidorSocket.setSoTimeout(6000);	
			servidorSocket.receive(pkgRecebido);
			System.out.println("Pedido recebido.");
			
			Despache_DNS despache_DNS = new Despache_DNS(servidorSocket, pkgRecebido);
			despache_DNS.start();
			
			}catch(SocketTimeoutException e){
				// ignorar
			}catch (Exception e) {
				System.out.println(e);
				return;
			}
			
		}
		
		close();
		
	}

	public List<IP> getIps() {
		return ips;
	}


}
