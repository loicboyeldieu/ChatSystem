package chatsystem.graphical;

import java.io.File;

// TODO: Auto-generated Javadoc
/**
 * The Interface FromUser.
 */
public interface FromUser {
	
	
	/**
	 * Select user.
	 */
	public void selectUser();
	
	/**
	 * Send message.
	 *
	 * @param message the message
	 */
	public void sendMessage(String message);
	
	/**
	 * Disconnect.
	 */
	public void disconnect();
	
	/**
	 * Connect.
	 */
	public void connect();
	
	/**
	 * Send file.
	 *
	 * @param file the file
	 */
	public void sendFile(File file);
	
	/**
	 * Accept file.
	 *
	 * @param name the name
	 * @param location the location
	 */
	public void acceptFile(String name,File location);
	
	/**
	 * Decline file.
	 *
	 * @param name the name
	 */
	public void declineFile(String name);
	
	/**
	 * Select file.
	 *
	 * @param file the file
	 */
	public void selectFile(File file);

}
