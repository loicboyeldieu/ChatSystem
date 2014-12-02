package chatsystem.network;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

import chatsystemTDa2.Hello;
import chatsystemTDa2.Message;


// j.B : Je le baiserai mon MAC 14:30 2/12/14


public class UDPReceiver extends Thread {
	
	

	
	//_____________________________Attributs__________________________________//
	//________________________________________________________________________//
			
	private DatagramSocket socketReceiver;
	private DatagramPacket packetReceiver;
	private ChatNI ni;
			



	//____________________________Constructors________________________________//
	//________________________________________________________________________//
	public UDPReceiver(DatagramPacket packet){
		try{
			this.packetReceiver=packet;
			this.socketReceiver= new DatagramSocket(ChatNI.NUM_PORT);
		}catch (Exception e){
			e.printStackTrace();
		}
		
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
	
	public ChatNI getNi() {
		return ni;
	}

	public void setNi(ChatNI ni) {
		this.ni = ni;
	}

	


		
	//________________________________________________________________________//
	//________________________________________________________________________//
			
	@Override
	public void run() {
		super.run();
		System.out.println("La réception des messages a commencé");
		
		while(true)
		{
			try{
				this.socketReceiver.receive(this.packetReceiver);
				
				new MessageHandler(this.packetReceiver,this.ni).handle();
			}catch(Exception e){
				e.printStackTrace();
				System.exit(-1);
			}
			
		}
	}

	
	
	
	
	public static void main(String[] args){	
	
		try {
			byte[] buffer = new byte[ChatNI.MAX_SIZE_BUFFER];
			DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
			UDPReceiver udpReceiver=new UDPReceiver(packet);
			
			System.out.println("J'attend un packet");
			udpReceiver.getSocketReceiver().receive(packet);
			System.out.println("Packet reçu");
			
			
			ByteArrayInputStream bais = new ByteArrayInputStream(buffer);
			ObjectInputStream ois=new ObjectInputStream(bais);
			Message messageReceived = (Message)ois.readObject();
			
			if (messageReceived instanceof Hello) System.out.println("je suis un hello");
			System.out.println(messageReceived);
			

		} catch (Exception e){
			e.printStackTrace();
		}
		

	}


}
