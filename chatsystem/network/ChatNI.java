package chatsystem.network;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import chatsystem.chatsystemTDa2.Hello;

public class ChatNI {

	private UDPReceiver udpReceiver;
	private UDPSender udpSender;



	public ChatNI(UDPReceiver udpReceiver, UDPSender udpSender) {
		this.udpReceiver = udpReceiver;
		this.udpSender = udpSender;
	}



	public void sendHello(String nickname){
		try {
			Hello helloToSend=new Hello(nickname);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(helloToSend);
			oos.flush();
			
			byte[] buffer=baos.toByteArray();
			
			System.out.println("taille : "+buffer.length);
			
			udpSender.send(buffer, InetAddress.getLocalHost());
			System.out.println("DONE SENDING");
			
		} catch (Exception e){
			e.printStackTrace();
		}

	}
	
	



	public static void main(String[] args) {
		
		DatagramSocket socket;
		try {
			socket = new DatagramSocket();
			ChatNI ni= new ChatNI(null, new UDPSender(socket));
			ni.sendHello("Poulet");
		} catch (SocketException e) {
			e.printStackTrace();
		}
		
		
		

	}

}
