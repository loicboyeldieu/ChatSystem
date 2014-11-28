package chatsystem.network;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

import chatsystem.ChatSystem;
import chatsystem.chatsystemTDa2.Message;



public class UDPReceiver {

	
	//_____________________________Attributs__________________________________//
	//________________________________________________________________________//
			
	private DatagramSocket socketReceiver;
	private DatagramPacket packetReceiver;
			
	//____________________________Constructors________________________________//
	//________________________________________________________________________//
	public UDPReceiver(DatagramSocket socket, DatagramPacket packet){
		this.setPacketReceiver(packet);
		this.socketReceiver = socket;
	}
			
	//__________________________Getters & setters ____________________________//
	//________________________________________________________________________//
		
	public DatagramPacket getPacketReceiver() {
		return packetReceiver;
	}

	public void setPacketReceiver(DatagramPacket packetReceiver) {
		this.packetReceiver = packetReceiver;
	}

	public DatagramSocket getSocketReceiver() {
		return socketReceiver;
	}

	public void setSocketReceiver(DatagramSocket socketReceiver) {
		this.socketReceiver = socketReceiver;
	}
		
	//________________________________________________________________________//
	//________________________________________________________________________//
			
		

	public static void main(String[] args){	
	
		DatagramSocket socketReceiver;
		try {
			
			socketReceiver = new DatagramSocket(ChatSystem.NUM_PORT);
			byte[] buffer = new byte[150];
			DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
			System.out.println("J'attend un packet");
			socketReceiver.receive(packet);
			System.out.println("Packet reçu");
			ByteArrayInputStream bais = new ByteArrayInputStream(buffer);
			ObjectInputStream ois=new ObjectInputStream(bais);
			Message messageReceived = (Message)ois.readObject();
			System.out.println(messageReceived);
			

		} catch (Exception e){
			e.printStackTrace();
		}
		

	}
}
