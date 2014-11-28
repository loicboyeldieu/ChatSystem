import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;



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
			socketReceiver = new DatagramSocket(UDPSender.NUM_PORT);
			byte[] buffer = new byte[10];
			DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
			socketReceiver.receive(packet);
			byte[] buffer2 = packet.getData(); 
			String strBuffer2 = new String(buffer2);
			System.out.println(strBuffer2);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
}
