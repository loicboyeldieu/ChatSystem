package chatsystem.network;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;

import chatsystem.chatsystemTDa2.Message;

public class MessageHandler {
	
	private ChatNI ni;
	
	private DatagramPacket packetReceiver;
	
	
	
	public MessageHandler(DatagramPacket packetReceiver) {
		this.packetReceiver = packetReceiver;
	}

	
	public void handle() {
		try{
			ByteArrayInputStream bais = new ByteArrayInputStream(this.packetReceiver.getData());
			ObjectInputStream ois=new ObjectInputStream(bais);
			Message messageReceived = (Message)ois.readObject();
			System.out.println(messageReceived);
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
