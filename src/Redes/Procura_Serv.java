package Redes;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;

public class Procura_Serv implements Runnable{
	
	private DatagramSocket procServSocket;
	 
	private DatagramPacket pkgEnviado;
	private DatagramPacket pkgRecebido;
	
	private boolean inicializado;
	private boolean executando;

	private Thread  thread;
	
	public Procura_Serv() throws Exception{
		
		inicializado = false;
		executando   = false;
	
		open();
	}
	
	private void open() {
		try {
			this.procServSocket = new DatagramSocket(); 
			this.procServSocket.setBroadcast(true);
			
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
			InetAddress addr = InetAddress.getByName("255.255.255.255");
			byte[] msgEnviada = new byte[1024];
			pkgEnviado = new DatagramPacket(msgEnviada, msgEnviada.length, addr, 2525);
			this.procServSocket.send(pkgEnviado);
			
			System.out.println("Brodcast servidores feito!");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void run() {
		int cont=0;
		
		while(executando){
		
		byte[] recebeDados = new byte[1024];
		pkgRecebido = new DatagramPacket(recebeDados, recebeDados.length);
	 	
		try {
			procServSocket.setSoTimeout(6000); //botei mais tempo de espera
												// para tirar o sleep	
		 	procServSocket.receive(pkgRecebido); 
		 	
		 	Guarda_Serv guarda_Serv = new Guarda_Serv(pkgRecebido);
		 	guarda_Serv.start();
		 	
		 	cont++; 	
		} catch (SocketTimeoutException g) {
			
			Servidor_DNS.ips.clear();
			
				if(executando){
				enviarMsg();
				}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
			
	}
		close();	
	}

}
