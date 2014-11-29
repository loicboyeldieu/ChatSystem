package chatsystem.network;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import chatsystem.chatsystemTDa2.Goodbye;
import chatsystem.chatsystemTDa2.Hello;

public class ChatNI implements ToRemote{
	
	public static final int MAX_SIZE_BUFFER=500;
	public static final int NUM_PORT = 12055;

	private UDPReceiver udpReceiver;
	private UDPSender udpSender;


	public ChatNI(UDPReceiver udpReceiver, UDPSender udpSender) {
		this.udpReceiver = udpReceiver;
		udpReceiver.start();
		this.udpSender = udpSender;
	}


	@Override
	public void sendHello(String nickname){
		try {
			Hello helloToSend=new Hello(nickname);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(helloToSend);
			oos.flush();
			
			byte[] buffer=baos.toByteArray();
			
			
			udpSender.send(buffer, InetAddress.getLocalHost());
			System.out.println("ChatNI :fini d'envoyé");
			
		} catch (Exception e){
			e.printStackTrace();
		}

	}
	

	@Override
	public void sendGoodbye(String nickname){
		try {
			Goodbye goodbye=new Goodbye(nickname);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(goodbye);
			oos.flush();
			
			byte[] buffer=baos.toByteArray();
			
			udpSender.send(buffer, InetAddress.getLocalHost());
			System.out.println("DONE SENDING");
			
		} catch (Exception e){
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void sendSend(String nickname,int id){
		
	}
	
	
	@Override
	public void sendHelloACK(String nickname) {
		
		
	}



	@Override
	public void sendSendAck(int id) {
		
		
	}
	

	
	


	public static void main(String[] args) {
		
		try {
			byte[] buffer = new byte[ChatNI.MAX_SIZE_BUFFER];
			DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
			ChatNI ni= new ChatNI(new UDPReceiver(packet), new UDPSender(new DatagramSocket()));
			
			ni.sendHello("Poulet");
			ni.sendHello("Bebe");
		} catch (SocketException e) {
			e.printStackTrace();
		}
		
		
		

	}


	public UDPReceiver getUdpReceiver() {
		return udpReceiver;
	}


	public void setUdpReceiver(UDPReceiver udpReceiver) {
		this.udpReceiver = udpReceiver;
	}


	public UDPSender getUdpSender() {
		return udpSender;
	}


	public void setUdpSender(UDPSender udpSender) {
		this.udpSender = udpSender;
	}





}
