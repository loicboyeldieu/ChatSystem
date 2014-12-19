package chatsystem.network;

import java.io.File;

import chatsystem.User;

// TODO: Auto-generated Javadoc
/**
 * The Interface ToRemote.
 */
public interface ToRemote {
	
	/**
	 * Send hello.
	 *
	 * @param nickname the nickname
	 */
	public void sendHello(String nickname);
	
	/**
	 * Send goodbye.
	 *
	 * @param nickname the nickname
	 */
	public void sendGoodbye(String nickname);
	
	/**
	 * Send hello ack.
	 *
	 * @param remote the remote
	 * @param local the local
	 */
	public void sendHelloACK(User remote,User local);
	
	/**
	 * Send send.
	 *
	 * @param msg the msg
	 * @param id the id
	 * @param remote the remote
	 */
	public void sendSend(String msg,int id,User remote);
	
	/**
	 * Send send ack.
	 *
	 * @param id the id
	 * @param remote the remote
	 */
	public void sendSendAck(int id,User remote);
	
	/**
	 * Send file request.
	 *
	 * @param remote the remote
	 * @param name the name
	 */
	public void sendFileRequest(User remote,String name);
	
	/**
	 * Send file response.
	 *
	 * @param remote the remote
	 * @param name the name
	 * @param response the response
	 */
	public void sendFileResponse(User remote,String name,boolean response);
	
	/**
	 * Send file.
	 *
	 * @param file the file
	 * @param remote the remote
	 */
	public void sendFile(File file,User remote);

}
