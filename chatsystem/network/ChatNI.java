package chatsystem.network;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import chatsystem.ChatController;
import chatsystem.User;
import chatsystem.chatsystemTDa2.Goodbye;
import chatsystem.chatsystemTDa2.Hello;
import chatsystem.chatsystemTDa2.HelloAck;
import chatsystem.chatsystemTDa2.Send;
import chatsystem.chatsystemTDa2.SendAck;

public class ChatNI implements ToRemote,FromRemote{
	
	// Constant relatives to the network
	public static final int MAX_SIZE_BUFFER=512;
	public static final int NUM_PORT = 12057;
	

	
	//Fields
	private UDPReceiver udpReceiver;
	private UDPSender udpSender;
	private ChatController controller;


	//Constructors
	public ChatNI(UDPReceiver udpReceiver, UDPSender udpSender) {
		this.udpReceiver = udpReceiver;
		udpReceiver.start();
		this.udpSender = udpSender;
		this.udpReceiver.setNi(this);
		this.udpSender.setNi(this);
	}
	
	
	//Getters and Setters
	
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
	
	public ChatController getController() {
		return controller;
	}


	public void setController(ChatController controller) {
		this.controller = controller;
	}
	
	
	


	//ToRemote Methods
	@Override
	public void sendHello(String nickname){
		try {
			Hello helloToSend=new Hello(nickname);
			byte[] buffer=objectToByteArray(helloToSend);
			 InetAddress broadcast=InetAddress.getByAddress(new byte[] 
						{(byte)255,(byte)255,(byte)255,(byte)255});
			udpSender.send(buffer, broadcast);
			
			System.out.println("ChatNI :Hello envoyé");
		} catch (Exception e){
			e.printStackTrace();
		}

	}
	

	@Override
	public void sendGoodbye(String nickname){
		try {
			Goodbye goodbye=new Goodbye(nickname);
			byte[] buffer=objectToByteArray(goodbye);
			udpSender.send(buffer, InetAddress.getLocalHost());
			System.out.println("ChatNI :Goodbye envoyé");
			
		} catch (Exception e){
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void sendSend(String msg,int id){
		try {
			Send send=new Send(msg,id);
			byte[] buffer=objectToByteArray(send);
			udpSender.send(buffer, InetAddress.getLocalHost());
			System.out.println("ChatNI :Send envoyé");
			
		} catch (Exception e){
			e.printStackTrace();
		}
		
		
	}
	
	
	@Override
	public void sendHelloACK(User remoteUser,User localUser) {
		try{
			HelloAck helloack=new HelloAck(localUser.getName());
			
			byte[] buffer=objectToByteArray(helloack);
			
			udpSender.send(buffer, remoteUser.getAddress());
			System.out.println("ChatNI :Hello ACK envoyé");
			
			
			
		}catch (Exception e){
			e.printStackTrace();
		}
		
		
	}



	@Override
	public void sendSendAck(int id) {
		try{
			SendAck sendack=new SendAck(id);
			byte[] buffer=objectToByteArray(sendack);
			udpSender.send(buffer, InetAddress.getLocalHost());
			System.out.println("DONE SENDING");
			
			
			
		}catch (Exception e){
			e.printStackTrace();
		}
		
		
	}
	

	//FromRemote Methods
	
	@Override
	public void onHelloReceived(User u) {
		controller.performHello(u);
	}



	@Override
	public void onHelloAckReceived(User u) {
		controller.performHelloAck(u);
		
	}

	
	//private Methods
	private byte[] objectToByteArray(Object o){
		try{
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(o);
			oos.flush();
			return baos.toByteArray();
		} catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}


	public static void main(String[] args) {
		
		try {
			byte[] buffer = new byte[ChatNI.MAX_SIZE_BUFFER];
			DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
			ChatNI ni= new ChatNI(new UDPReceiver(packet), new UDPSender(new DatagramSocket()));
			ChatController controller=new ChatController(ni);
			int id=22;
			String msg="salut";
			
		} catch (SocketException e) {
			e.printStackTrace();
		}
		
		
		

	}














}
