package chatsystem.network;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.lang.Thread.State;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

import chatsystemTDa2.Hello;
import chatsystemTDa2.Message;


// TODO: Auto-generated Javadoc
// j.B : Je le baiserai mon MAC 14:30 2/12/14


/**
 * The Class UDPReceiver.
 */
public class UDPReceiver extends Thread {
	
	

	
	//_____________________________Attributs__________________________________//
	//________________________________________________________________________//
			
	/** The socket receiver. */
	private DatagramSocket socketReceiver;
	
	/** The packet receiver. */
	private DatagramPacket packetReceiver;
	
	/** The ni. */
	private ChatNI ni;
			



	//____________________________Constructors________________________________//
	//________________________________________________________________________//
	/**
	 * Instantiates a new UDP receiver.
	 *
	 * @param packet the packet
	 */
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
		
	/**
	 * Gets the packet receiver.
	 *
	 * @return the packet receiver
	 */
	public DatagramPacket getPacketReceiver() {
		return packetReceiver;
	}

	/**
	 * Sets the packet receiver.
	 *
	 * @param packetReceiver the new packet receiver
	 */
	public void setPacketReceiver(DatagramPacket packetReceiver) {
		this.packetReceiver = packetReceiver;
	}

	/**
	 * Gets the socket receiver.
	 *
	 * @return the socket receiver
	 */
	public DatagramSocket getSocketReceiver() {
		return socketReceiver;
	}

	/**
	 * Sets the socket receiver.
	 *
	 * @param socketReceiver the new socket receiver
	 */
	public void setSocketReceiver(DatagramSocket socketReceiver) {
		this.socketReceiver = socketReceiver;
	}
	
	/**
	 * Gets the ni.
	 *
	 * @return the ni
	 */
	public ChatNI getNi() {
		return ni;
	}

	/**
	 * Sets the ni.
	 *
	 * @param ni the new ni
	 */
	public void setNi(ChatNI ni) {
		this.ni = ni;
	}

	


		
	//________________________________________________________________________//
	//________________________________________________________________________//
			
	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		super.run();
		System.out.println("La r�ception des messages a commenc�");
		
		while(!isInterrupted())
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
	
	/**
	 * Stop socket.
	 */
	public void stopSocket(){
		this.socketReceiver.close();
	}

	
	

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
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
