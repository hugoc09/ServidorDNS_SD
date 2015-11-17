package Redes;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;

import Entidades.IP;
import Negocios.Pesquisa;

public class Procura_Serv implements Runnable{
	
	private DatagramSocket procServSocket;
	 
	private DatagramPacket pkgEnviado;
	private DatagramPacket pkgRecebido;
	private Control contole;
	
	private boolean inicializado;

	private boolean executando;

	private Thread  thread;
	
	public Procura_Serv() throws Exception{
		
		this.procServSocket = new DatagramSocket(); 
		this.procServSocket.setBroadcast(true);
		this.contole = new Pesquisa();
		
		inicializado = false;
		executando   = false;
	
		open();
	}
	
	private void open() {
		try {
			inicializado = true;
		}
		catch (Exception e) {
			close();
		}
	}
	
	private void close(){

		if (procServSocket != null) {
			try {
				procServSocket.close();
			}
			catch (Exception e){
				System.out.println(e);
			}
		}

		pkgEnviado   = null;
		pkgRecebido  = null;
		procServSocket = null;

		inicializado = false;
		executando   = false;

		thread = null;
		
	}
	
	public void start() {
		enviarMsg();
		
		if (!inicializado || executando) {
			return;
		}

		executando = true;
		thread = new Thread(this);
		thread.start();
	}

	public void stop() throws Exception{
		executando = false;
		
		if (thread != null) {
			thread.join();
		}
	}
	
	private void enviarMsg(){
		
		try {
			System.out.println("Msg enviada!");
			InetAddress addr = InetAddress.getByName("255.255.255.255");
			String msgEnviada = "ola";
			pkgEnviado = new DatagramPacket(msgEnviada.getBytes(),msgEnviada.length(), addr, 2525);
			this.procServSocket.send(pkgEnviado);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void run() {
		
		while(executando){
		
		
		byte[] recebeDados = new byte[1024];
		pkgRecebido = new DatagramPacket(recebeDados, recebeDados.length);
	 	
		try {
			
			procServSocket.setSoTimeout(3000);
		 	
		 	procServSocket.receive(pkgRecebido); 
		 	
		 	IP a = new IP(pkgRecebido.getAddress(), pkgRecebido.getPort());
		 	
		 	contole.pegarIp(a);
			
		} catch (SocketTimeoutException g) {
			
			try {
				System.out.println("Dormindo...");
				Thread.sleep(5000);
				enviarMsg();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
			
	}
		close();	
	}

}
