package chatsystem.network;

import chatsystem.User;

// TODO: Auto-generated Javadoc
/**
 * The Interface FromRemote.
 */
public interface FromRemote {

	/**
	 * On Hello received.
	 *
	 * @param u the u
	 */
	public void onHelloReceived(User u);
	
	/**
	 * On Hello ack received.
	 *
	 * @param u the u
	 */
	public void onHelloAckReceived(User u);
	
	/**
	 * On Goodbye received.
	 *
	 * @param u the u
	 */
	public void onGoodbyeReceived(User u);
	
	/**
	 * On Send received.
	 *
	 * @param u the u
	 * @param message the message
	 * @param id the id
	 */
	public void onSendReceived(User u,String message,int id);
	
	/**
	 * On SendAck received.
	 *
	 * @param u the u
	 * @param id the id
	 */
	public void onSendAckReceived(User u,int id);
	
	/**
	 * On FileRequest received.
	 *
	 * @param u the u
	 * @param name the name
	 */
	public void onFileRequestReceived(User u, String name);
	
	/**
	 * On FileAccepted.
	 *
	 * @param u the u
	 */
	public void onFileAccepted(User u);
	
	/**
	 * On FileRefused.
	 *
	 * @param u the u
	 */
	public void onFileRefused(User u);
	

}
