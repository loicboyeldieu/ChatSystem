package chatsystem.network;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;

import chatsystem.ChatController;
import chatsystem.User;
import chatsystemTDa2.Goodbye;
import chatsystemTDa2.Hello;
import chatsystemTDa2.HelloAck;
import chatsystemTDa2.Send;
import chatsystemTDa2.SendAck;

public class ChatNI implements ToRemote,FromRemote{
	
	// Constant relatives to the network
	public static final int MAX_SIZE_BUFFER=512;
	public static final int NUM_PORT = 12046;
	public static final byte[] BROADCAST =new byte[] {(byte)255,(byte)255,(byte)255,(byte)255};
	

	
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
	
	public ChatNI(){
		
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
			 //InetAddress broadcast=InetAddress.getByAddress(BROADCAST);
			InetAddress broadcast=this.getBroadcastAdress();
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
			//InetAddress broadcast=InetAddress.getByAddress(BROADCAST);
			InetAddress broadcast=this.getBroadcastAdress();
			udpSender.send(buffer, broadcast);
			System.out.println("ChatNI :Goodbye envoyé");
			
		} catch (Exception e){
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void sendSend(String msg,int id,User remote){
		try {
			Send send=new Send(msg,id);
			byte[] buffer=objectToByteArray(send);
			InetAddress adress=remote.getAddress();
			udpSender.send(buffer, adress);
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
	public void sendSendAck(int id,User remote) {
		try{
			SendAck sendack=new SendAck(id);
			byte[] buffer=objectToByteArray(sendack);
			udpSender.send(buffer, InetAddress.getLocalHost());
			System.out.println("Chat  NI : SendSendAck envoyé");
			
			
			
		}catch (Exception e){
			e.printStackTrace();
		}
		
		
	}
	

	//FromRemote Methods
	
	@Override
	public void onHelloReceived(User u) {
		System.out.println("J'ai reçu un hello de"+u.getName());
		controller.processHello(u);
	}



	@Override
	public void onHelloAckReceived(User u) {
		controller.processHelloAck(u);
		
	}
	
	@Override
	public void onGoodbyeReceived(User u){
		controller.processGoodbye(u);
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
	
	
	private InetAddress getBroadcastAdress(){
			InetAddress localHost;
			try {
				localHost = InetAddress.getLocalHost();
				NetworkInterface networkInterface = NetworkInterface.getByInetAddress(localHost);
				
				for (InterfaceAddress address : networkInterface.getInterfaceAddresses()) {
					if (address.getBroadcast()!=null) return address.getBroadcast();
					
					
				}
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
		return null;
	}


	public static void main(String[] args) {
		
		ChatNI ni=new ChatNI();
		InetAddress address=ni.getBroadcastAdress();
		System.out.println(address);


	}














}
