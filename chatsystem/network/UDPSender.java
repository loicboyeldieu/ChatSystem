package chatsystem.network;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPSender {

	//_____________________________Attributs__________________________________//
	//________________________________________________________________________//
		

	private DatagramSocket socketSender;
	private ChatNI ni;
		

	//____________________________Constructors________________________________//
	//________________________________________________________________________//
	public UDPSender(DatagramSocket socket){
		this.socketSender = socket;
	}
		
	//__________________________Getters & setters ____________________________//
	//________________________________________________________________________//

	public ChatNI getNi() {
		return ni;
	}

	public void setNi(ChatNI ni) {
		this.ni = ni;
	}



	public DatagramSocket getSocketSender() {
		return socketSender;
	}

	public void setSocketSender(DatagramSocket socketSender) {
		this.socketSender = socketSender;
	}
	
	//____________________________Methods________________________________//
	//________________________________________________________________________//
	
	
	public void send(byte[] toSend,InetAddress remoteAddress){
		
		try {
		
		socketSender=new DatagramSocket();
		
		socketSender.send(new DatagramPacket(toSend, toSend.length,remoteAddress,ChatNI.NUM_PORT));
		
		socketSender.close();
		} catch (Exception e){
			e.printStackTrace();
		} 
	}
	


	public static void main(String[] args){
		
		DatagramSocket dSocket;
		try {
			dSocket = new DatagramSocket();
			UDPSender udpSender=new UDPSender(dSocket);
			String message ="salut";
			udpSender.send(message.getBytes(), InetAddress.getLocalHost());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	
			
		

//			DatagramSocket datagramSocket = new DatagramSocket();
//			byte[] buffer = "salut lolo".getBytes();
//			
//			InetAddress address;
//			address = InetAddress.getLocalHost();
//			DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, NUM_PORT);
//			datagramSocket.send(packet);
			
			
			

		
	}


}
