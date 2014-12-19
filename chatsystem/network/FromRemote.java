package chatsystem.network;

import chatsystem.User;

// TODO: Auto-generated Javadoc
/**
 * The Interface FromRemote.
 */
public interface FromRemote {

	/**
	 * On hello received.
	 *
	 * @param u the u
	 */
	public void onHelloReceived(User u);
	
	/**
	 * On hello ack received.
	 *
	 * @param u the u
	 */
	public void onHelloAckReceived(User u);
	
	/**
	 * On goodbye received.
	 *
	 * @param u the u
	 */
	public void onGoodbyeReceived(User u);
	
	/**
	 * On send received.
	 *
	 * @param u the u
	 * @param message the message
	 * @param id the id
	 */
	public void onSendReceived(User u,String message,int id);
	
	/**
	 * On send ack received.
	 *
	 * @param u the u
	 * @param id the id
	 */
	public void onSendAckReceived(User u,int id);
	
	/**
	 * On file request received.
	 *
	 * @param u the u
	 * @param name the name
	 */
	public void onFileRequestReceived(User u, String name);
	
	/**
	 * On file accepted.
	 *
	 * @param u the u
	 */
	public void onFileAccepted(User u);
	
	/**
	 * On file refused.
	 *
	 * @param u the u
	 */
	public void onFileRefused(User u);
	

}
