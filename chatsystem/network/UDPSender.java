

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UDPSender {

	//_____________________________Attributs__________________________________//
	//________________________________________________________________________//
		
	public static final int NUM_PORT = 12051;
	private DatagramSocket socketSender;
	private DatagramPacket packetSender;
		
	//____________________________Constructors________________________________//
	//________________________________________________________________________//
	public UDPSender(DatagramSocket socket, DatagramPacket packet){
		this.setPacketSender(packet);
		this.socketSender = socket;
	}
		
	//__________________________Getters & setters ____________________________//
	//________________________________________________________________________//
	
	public DatagramPacket getPacketSender() {
		return packetSender;
	}

	public void setPacketSender(DatagramPacket packetSender) {
		this.packetSender = packetSender;
	}

	public DatagramSocket getSocketSender() {
		return socketSender;
	}

	public void setSocketSender(DatagramSocket socketSender) {
		this.socketSender = socketSender;
	}
	
	//________________________________________________________________________//
	//________________________________________________________________________//
		
	

	public static void main(String[] args){
			
		
		
		try {
			DatagramSocket datagramSocket = new DatagramSocket();
			byte[] buffer = "salut lolo".getBytes();
			
			InetAddress address;
			address = InetAddress.getLocalHost();
			DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, NUM_PORT);
			datagramSocket.send(packet);
		} catch (UnknownHostException e) {
			System.out.println("Localhost is unreachable");
			e.printStackTrace();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}


}
