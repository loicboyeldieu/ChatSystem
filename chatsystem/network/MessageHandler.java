package chatsystem.network;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;

import chatsystem.User;
import chatsystemTDa2.FileRequest;
import chatsystemTDa2.FileResponse;
import chatsystemTDa2.Goodbye;
import chatsystemTDa2.Hello;
import chatsystemTDa2.HelloAck;
import chatsystemTDa2.Message;
import chatsystemTDa2.Send;
import chatsystemTDa2.SendAck;

// TODO: Auto-generated Javadoc
/**
 * The Class MessageHandler.
 */
public class MessageHandler {
	
	/** The ni. */
	private ChatNI ni;
	
	/** The packet receiver. */
	private DatagramPacket packetReceiver;
	
	
	
	/**
	 * Instantiates a new message handler.
	 *
	 * @param packetReceiver the packet receiver
	 * @param ni the ni
	 */
	public MessageHandler(DatagramPacket packetReceiver,ChatNI ni) {
		this.packetReceiver = packetReceiver;
		this.ni=ni;
	}
	
	
	


	/**
	 * Handle.
	 */
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
			} else if(messageReceived instanceof Goodbye){
				Goodbye goodbye=(Goodbye)messageReceived;
				u=new User(goodbye.getNickname(), address);
				ni.onGoodbyeReceived(u);
			} else if (messageReceived instanceof Send){
				Send send=(Send)messageReceived;
				u=ni.whoIsIt(address);
				ni.onSendReceived(u, send.getMessage(),send.getID());
			}else if (messageReceived instanceof SendAck){
				
			} else if (messageReceived instanceof FileRequest){
				FileRequest fileRequest=(FileRequest)messageReceived;
				u=ni.whoIsIt(address);
				ni.onFileRequestReceived(u,fileRequest.getName());
			} else if (messageReceived instanceof FileResponse){
				FileResponse fileResponse=(FileResponse)messageReceived;
				u=ni.whoIsIt(address);
				if (fileResponse.getResponse()){
					ni.onFileAccepted(u);
				}
				else{	
					ni.onFileRefused(u);
				}
				
			}

			
			
			
			
		}catch (Exception e){
			e.printStackTrace();
		}
		
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
	
	
	
	
	
	



}
