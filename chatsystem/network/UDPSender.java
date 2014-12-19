package chatsystem.network;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

// TODO: Auto-generated Javadoc
/**
 * The Class UDPSender.
 */
public class UDPSender {

	//_____________________________Attributs__________________________________//
	//________________________________________________________________________//
		

	/** The socket sender. */
	private DatagramSocket socketSender;
	
	/** The ni. */
	private ChatNI ni;
		

	//____________________________Constructors________________________________//
	//________________________________________________________________________//
	/**
	 * Instantiates a new UDP sender.
	 *
	 * @param socket the socket
	 */
	public UDPSender(DatagramSocket socket){
		this.socketSender = socket;
	}
		
	//__________________________Getters & setters ____________________________//
	//________________________________________________________________________//


	/**
	 * Gets the socket sender.
	 *
	 * @return the socket sender
	 */
	public DatagramSocket getSocketSender() {
		return socketSender;
	}

	/**
	 * Sets the socket sender.
	 *
	 * @param socketSender the new socket sender
	 */
	public void setSocketSender(DatagramSocket socketSender) {
		this.socketSender = socketSender;
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

	
	//____________________________Methods________________________________//
	//________________________________________________________________________//
	
	
	/**
	 * Send.
	 *
	 * @param toSend the to send
	 * @param remoteAddress the remote address
	 */
	public void send(byte[] toSend,InetAddress remoteAddress){
		
		try {
		
		socketSender=new DatagramSocket();
		
		
		DatagramPacket packet=new DatagramPacket(toSend, toSend.length,remoteAddress,ChatNI.NUM_PORT);
		socketSender.send(packet);
		
		socketSender.close();
		} catch (Exception e){
			e.printStackTrace();
		} 
	}
	


	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
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
