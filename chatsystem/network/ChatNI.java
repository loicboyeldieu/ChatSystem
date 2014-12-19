package chatsystem.network;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;

import chatsystem.ChatController;
import chatsystem.User;
import chatsystemTDa2.FileRequest;
import chatsystemTDa2.FileResponse;
import chatsystemTDa2.Goodbye;
import chatsystemTDa2.Hello;
import chatsystemTDa2.HelloAck;
import chatsystemTDa2.Send;
import chatsystemTDa2.SendAck;

// TODO: Auto-generated Javadoc
/**
 * The Class ChatNI.
 */
public class ChatNI implements ToRemote,FromRemote{

	// Constant relatives to the network
	/** The Constant MAX_SIZE_BUFFER. */
	public static final int MAX_SIZE_BUFFER=512;
	
	/** The Constant NUM_PORT. */
	public static final int NUM_PORT = 16050;
	
	/** The Constant BROADCAST. */
	public static final byte[] BROADCAST =new byte[] {(byte)255,(byte)255,(byte)255,(byte)255};



	//Fields
	/** The receiver. */
	private UDPReceiver receiver;
	
	/** The udp sender. */
	private UDPSender udpSender;

	/** The tcp busy. */
	private boolean tcpBusy;
	
	/** The controller. */
	private ChatController controller;


	//Constructors
	/**
	 * Instantiates a new chat ni.
	 *
	 * @param udpReceiver the udp receiver
	 * @param udpSender the udp sender
	 */
	public ChatNI(UDPReceiver udpReceiver, UDPSender udpSender) {
		this.receiver = udpReceiver;
		this.udpSender = udpSender;
		this.receiver.setNi(this);
		this.udpSender.setNi(this);
		this.tcpBusy=false;
	}
	
	
	/**
	 * Instantiates a new chat ni.
	 *
	 * @param udpSender the udp sender
	 */
	public ChatNI(UDPSender udpSender){
		this.udpSender=udpSender;
		this.udpSender.setNi(this);
		this.tcpBusy=false;
	}

	/**
	 * Instantiates a new chat ni.
	 */
	public ChatNI(){
		this.tcpBusy=false;

	}


	//Getters and Setters

	/**
	 * Gets the udp receiver.
	 *
	 * @return the udp receiver
	 */
	public UDPReceiver getUdpReceiver() {
		return receiver;
	}


	/**
	 * Sets the udp receiver.
	 *
	 * @param udpReceiver the new udp receiver
	 */
	public void setUdpReceiver(UDPReceiver udpReceiver) {
		this.receiver = udpReceiver;
	}


	/**
	 * Gets the udp sender.
	 *
	 * @return the udp sender
	 */
	public UDPSender getUdpSender() {
		return udpSender;
	}


	/**
	 * Sets the udp sender.
	 *
	 * @param udpSender the new udp sender
	 */
	public void setUdpSender(UDPSender udpSender) {
		this.udpSender = udpSender;
	}

	/**
	 * Gets the controller.
	 *
	 * @return the controller
	 */
	public ChatController getController() {
		return controller;
	}


	/**
	 * Sets the controller.
	 *
	 * @param controller the new controller
	 */
	public void setController(ChatController controller) {
		this.controller = controller;
	}





	//ToRemote Methods
	/* (non-Javadoc)
	 * @see chatsystem.network.ToRemote#sendHello(java.lang.String)
	 */
	@Override
	public void sendHello(String nickname){
		try {
			Hello helloToSend=new Hello(nickname);
			byte[] buffer=objectToByteArray(helloToSend);
			//InetAddress broadcast=InetAddress.getByAddress(BROADCAST);
			InetAddress broadcast=this.getBroadcastAdress();
			udpSender.send(buffer, broadcast);

			System.out.println("ChatNI :Hello envoy�");
		} catch (Exception e){
			e.printStackTrace();
		}

	}


	/* (non-Javadoc)
	 * @see chatsystem.network.ToRemote#sendGoodbye(java.lang.String)
	 */
	@Override
	public void sendGoodbye(String nickname){
		try {
			Goodbye goodbye=new Goodbye(nickname);
			byte[] buffer=objectToByteArray(goodbye);
			//InetAddress broadcast=InetAddress.getByAddress(BROADCAST);
			InetAddress broadcast=this.getBroadcastAdress();
			udpSender.send(buffer, broadcast);
			System.out.println("ChatNI :Goodbye envoy�");

		} catch (Exception e){
			e.printStackTrace();
		}

	}

	/* (non-Javadoc)
	 * @see chatsystem.network.ToRemote#sendSend(java.lang.String, int, chatsystem.User)
	 */
	@Override
	public void sendSend(String msg,int id,User remote){
		try {
			Send send=new Send(msg,id);
			byte[] buffer=objectToByteArray(send);
			InetAddress adress=remote.getAddress();
			udpSender.send(buffer, adress);
			System.out.println("ChatNI :Send envoy�");

		} catch (Exception e){
			e.printStackTrace();
		}


	}


	/* (non-Javadoc)
	 * @see chatsystem.network.ToRemote#sendHelloACK(chatsystem.User, chatsystem.User)
	 */
	@Override
	public void sendHelloACK(User remoteUser,User localUser) {
		try{
			HelloAck helloack=new HelloAck(localUser.getName());

			byte[] buffer=objectToByteArray(helloack);
			udpSender.send(buffer, remoteUser.getAddress());
			System.out.println("ChatNI :Hello ACK envoy�");
		}catch (Exception e){
			e.printStackTrace();
		}


	}



	/* (non-Javadoc)
	 * @see chatsystem.network.ToRemote#sendSendAck(int, chatsystem.User)
	 */
	@Override
	public void sendSendAck(int id,User remote) {
		
			SendAck sendack=new SendAck(id);
			byte[] buffer=objectToByteArray(sendack);
			udpSender.send(buffer, remote.getAddress());
			System.out.println("Chat  NI : SendSendAck envoyé");
	}
	
	/* (non-Javadoc)
	 * @see chatsystem.network.ToRemote#sendFileRequest(chatsystem.User, java.lang.String)
	 */
	@Override
	public void sendFileRequest(User remote, String name) {
		FileRequest fileRequest=new FileRequest(name);
		byte[] buffer=objectToByteArray(fileRequest);
		
		udpSender.send(buffer, remote.getAddress());

		
	}
	
	/* (non-Javadoc)
	 * @see chatsystem.network.ToRemote#sendFileResponse(chatsystem.User, java.lang.String, boolean)
	 */
	@Override
	public void sendFileResponse(User remote, String name, boolean response) {
		FileResponse fileResponse=new FileResponse(response, name);
		byte[] buffer=objectToByteArray(fileResponse);
		udpSender.send(buffer, remote.getAddress());

	}
	
	/* (non-Javadoc)
	 * @see chatsystem.network.ToRemote#sendFile(java.io.File, chatsystem.User)
	 */
	@Override
	public void sendFile(File file,User remote) {
		if (!tcpBusy){
			tcpBusy=true;
			new TCPClient(remote.getAddress(), file,this).start();
			tcpBusy=false;
		}
		
	}
	
	//FromRemote Methods






	/**
	 * Start the UDP Receiver Thread
	 */
	public void startReception(){
		//udpReceiver.start();
		byte[] buffer = new byte[ChatNI.MAX_SIZE_BUFFER];
		DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
		receiver=new UDPReceiver(packet);
		receiver.setNi(this);
		receiver.start();
	}
	
	
	/**
	 * Stop the UDP Receiver Thread.
	 */
	public void stopReception(){
		receiver.interrupt();
		receiver.stopSocket();
		
		receiver=null;
	}

	/* (non-Javadoc)
	 * @see chatsystem.network.FromRemote#onHelloReceived(chatsystem.User)
	 */
	@Override
	public void onHelloReceived(User u) {
		controller.processHello(u);
	}



	/* (non-Javadoc)
	 * @see chatsystem.network.FromRemote#onHelloAckReceived(chatsystem.User)
	 */
	@Override
	public void onHelloAckReceived(User u) {
		controller.processHelloAck(u);

	}

	/* (non-Javadoc)
	 * @see chatsystem.network.FromRemote#onGoodbyeReceived(chatsystem.User)
	 */
	@Override
	public void onGoodbyeReceived(User u){
		controller.processGoodbye(u);
	}


	/* (non-Javadoc)
	 * @see chatsystem.network.FromRemote#onSendReceived(chatsystem.User, java.lang.String, int)
	 */
	@Override
	public void onSendReceived(User u,String message, int id) {
		controller.processSend(u, id, message);
	}

	/* (non-Javadoc)
	 * @see chatsystem.network.FromRemote#onSendAckReceived(chatsystem.User, int)
	 */
	@Override
	public void onSendAckReceived(User u, int id) {
		// TODO Auto-generated method stub

	}
	
	
	/* (non-Javadoc)
	 * @see chatsystem.network.FromRemote#onFileRequestReceived(chatsystem.User, java.lang.String)
	 */
	@Override
	public void onFileRequestReceived(User u, String name) {
		controller.processFileRequest(u,name);
		
	}
	
	

	/* (non-Javadoc)
	 * @see chatsystem.network.FromRemote#onFileAccepted(chatsystem.User)
	 */
	@Override
	public void onFileAccepted(User u) {
		controller.processFileAccepted(u);
	}
	
	/* (non-Javadoc)
	 * @see chatsystem.network.FromRemote#onFileRefused(chatsystem.User)
	 */
	@Override
	public void onFileRefused(User u){
		controller.processFileRefused(u);
	}
	
	/**
	 * Prepare the the tcp server for the incoming file.
	 *
	 * @param location the location
	 * @param name the name
	 */
	public void prepareTCPServer(File location,String name){
		if (!tcpBusy){
			tcpBusy=true;
			new TCPServer(location,name,this).start();
			tcpBusy=false;
		}
		
	}
	
	
	/**
	 * called when the file transfer ends up
	 */
	public void onFileCompleted(){
		controller.processFileCompleted();
	}

	//private Methods
	/**
	 * Object to byte array.
	 *
	 * @param o the o
	 * @return the byte[]
	 */
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


	/**
	 * Gets the broadcast adress.
	 *
	 * @return the broadcast adress
	 */
	private InetAddress getBroadcastAdress(){
		InetAddress localHost;

		String OS=System.getProperty("os.name");

		if (OS.equals("Windows 7")){
			try {
				return InetAddress.getByAddress(BROADCAST);
			} catch (Exception e){
				e.printStackTrace();
			}
			
		}else {
			try {
				localHost = InetAddress.getLocalHost();
				NetworkInterface networkInterface = NetworkInterface.getByInetAddress(localHost);

				for (InterfaceAddress address : networkInterface.getInterfaceAddresses()) {
					if (address.getBroadcast()!=null) return address.getBroadcast();
					System.out.println(address);
				}


			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}

	/**
	 * Who is it ? 
	 *
	 * @param address the address
	 * @return the user
	 */
	public User whoIsIt(InetAddress address){
		return controller.getUserFromIp(address);
	}


	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {

		ChatNI ni=new ChatNI();
		String OS=System.getProperty("os.name");
		System.out.println(OS);
		InetAddress address=ni.getBroadcastAdress();
		System.out.println(address);


	}


















}
