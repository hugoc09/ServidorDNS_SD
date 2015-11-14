package Servidor_DNS;

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
		
		//open();
	}
	
	private void open() {
		try {
			
			this.procServSocket = new DatagramSocket(); 
			this.procServSocket.setBroadcast(true);
	
			enviarMsg();
			
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
		open();
		
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
			String msgEnviada = "ola";
			pkgEnviado = new DatagramPacket(msgEnviada.getBytes(),msgEnviada.length(), addr, 2525);
			this.procServSocket.send(pkgEnviado);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void run() {
		int count=0 ;
		
		while(executando){
	
		byte[] recebeDados = new byte[1024];
		pkgRecebido = new DatagramPacket(recebeDados, recebeDados.length);
	 	
		try {
			
			procServSocket.setSoTimeout(5000);
		 	
		 	procServSocket.receive(pkgRecebido); 
		 	
		 	IP a = new IP(pkgRecebido.getAddress(), pkgRecebido.getPort());
		 	
		 	if(count==0){
		 		Servidor_DNS.ips.clear(); 
		 	}
		 	System.out.println(pkgRecebido.getAddress() + "." + pkgRecebido.getPort() );
		 	
		 	Servidor_DNS.ips.add(a);
		 	count++;	
		} catch (SocketTimeoutException g) {
			
			System.out.println("Servidores On: " + count);
			try {
				Thread.sleep(5000);
			} catch (Exception e) {
				e.printStackTrace();
			}
			enviarMsg();
		} catch (IOException e) {
			e.printStackTrace();
		}
			
	}
		
		close();	
	}

}
