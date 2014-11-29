package chatsystem.network;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;

import chatsystem.User;
import chatsystem.chatsystemTDa2.Hello;
import chatsystem.chatsystemTDa2.HelloAck;
import chatsystem.chatsystemTDa2.Message;

public class MessageHandler {
	
	private ChatNI ni;
	
	private DatagramPacket packetReceiver;
	
	
	
	public MessageHandler(DatagramPacket packetReceiver,ChatNI ni) {
		this.packetReceiver = packetReceiver;
		this.ni=ni;
	}

	
	public void handle() {
		try{
			ByteArrayInputStream bais = new ByteArrayInputStream(this.packetReceiver.getData());
			InetAddress address = this.packetReceiver.getAddress();
			ObjectInputStream ois=new ObjectInputStream(bais);
			Message messageReceived = (Message)ois.readObject();
			System.out.println(messageReceived);
			
			User u;
			
			if (messageReceived instanceof Hello)
			{
				Hello hello=(Hello)messageReceived;
				u=new User(hello.getNickname(), address);
				ni.onHelloReceived(u);
			} else if(messageReceived instanceof HelloAck){
				HelloAck helloAck=(HelloAck)messageReceived;
				u=new User(helloAck.getNickname(), address);
				ni.onHelloAckReceived(u);
			}

			
			
			
			
		}catch (Exception e){
			e.printStackTrace();
		}
		
	}


	public ChatNI getNi() {
		return ni;
	}


	public void setNi(ChatNI ni) {
		this.ni = ni;
	}
	
	
	
	
	
	



}
