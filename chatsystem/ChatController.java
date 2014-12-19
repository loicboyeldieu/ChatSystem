package chatsystem;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

import chatsystem.graphical.ChatGUI;
import chatsystem.network.ChatNI;

// TODO: Auto-generated Javadoc
/**
 * The Class ChatController represents the Presenter of our system in the MVP design pattern
 */
public class ChatController {

	/** The ni. */
	private ChatNI ni;
	
	/** The gui. */
	private ChatGUI gui;
	
	/** The user list. */
	private ArrayList<User> userList;
	
	/** The local user. */
	private static User localUser;
	
	/** The remote user. */
	private User remoteUser;
	
	/** The waiting user. */
	private User waitingUser;
	
	/** The fileto send. */
	private File filetoSend;
	
	/** The id. */
	private static int id;
	
	

	//Constructors
	/**
	 * Instantiates a new chat controller.
	 *
	 * @param ni the ni
	 * @param gui the gui
	 */
	public ChatController(ChatNI ni,ChatGUI gui) {
		this.userList = new ArrayList<User>();
		this.ni=ni;
		this.gui=gui;
		ni.setController(this);
		gui.setController(this);

	}
	
	/**
	 * Debug constructor
	 * 
	 */
	public ChatController(){
		this.userList = new ArrayList<User>();
	}
	

	

	//from NI
	/**
	 * This method is invoked when a HelloAck is received
	 *
	 * @param u the u
	 */
	public void processHelloAck(User u){
		gui.addToConnectedUserList(u);
		this.addNewToList(u);
	}
	
	/**
	 * This method is invoked when a Hello is received
	 *
	 * @param u the u
	 */
	public void processHello(User u){
		if (!u.equals(localUser))
		{
			gui.addToConnectedUserList(u);
			this.addNewToList(u);
			this.ni.sendHelloACK(u, localUser);
			gui.displayNotification(u, false);
		}
	
	}
	
	/**
	 * This method is invoked when a Goodbye is received
	 *
	 * @param u the u
	 */
	public void processGoodbye(User u) {
		if (!u.equals(localUser)){
			this.gui.removeUser(u);
			this.userList.remove(u);
			this.gui.displayNotification(u, true);
		}
	
		
	}
	
	/**
	 * This method is invoked when a Send is received
	 *
	 * @param u the u
	 * @param id the id
	 * @param message the message
	 */
	public void processSend(User u,int id, String message){
		
			this.gui.displayMessage(u,localUser,message);
			this.ni.sendSendAck(id, u);

		
	}
	
	/**
	 * This method is invoked when a FileRequest is received
	 *
	 * @param u the u
	 * @param name the name
	 */
	public void processFileRequest(User u,String name){
		waitingUser=u;
		this.gui.displayFileRequest(u,name);
	}
	
	/**
	 * This method is invoked when a FileResponse is received with a true response.
	 *
	 * @param u the u
	 */
	public void processFileAccepted(User u){
		ni.sendFile(filetoSend,u);
		this.gui.displayStatusFile(true, u);
		this.gui.displayFileNotificationTransfer(false);
	}
	
	/**
	 * This method is invoked when a FileResponse is received with a false response.
	 *
	 * @param u the u
	 */
	public void processFileRefused(User u){
		this.gui.displayStatusFile(false, u);
	}
	
	/**
	 * This method is invoked when a the file transfer is done
	 */
	public void processFileCompleted(){
		gui.displayFileNotificationTransfer(true);
	}
	
	
	//from GUI
	
	


	/**
	 * This method is invoked when the User calls Connect 
	 *
	 * @param nickname the nickname
	 */
	public void processConnect(String nickname){
		
		try {
			localUser=new User(nickname, InetAddress.getLocalHost());
		} catch (UnknownHostException e) {
			localUser=new User(nickname, null);
		} finally {
			System.out.println("Bienvenue  "+localUser);
		}
		
		this.ni.startReception();
		this.ni.sendHello(nickname);
	}
	
	/**
	 * This method is invoked when the User calls Disconnect
	 */
	public void processDisconnect() {
		this.ni.sendGoodbye(ChatController.localUser.getName());
		this.ni.stopReception();
	}
	
	
	/**
	 * This method retrieves the selected User from the ChatGUI and is invoked whenever a User calls SelectUser
	 *
	 * @param u the u
	 */
	public void onSelectedUser(User u){
		this.remoteUser=u;
		System.out.println("Destinataire : "+u);
	}
	
	
	/**
	 * This method is invoked when a User wants to send a message
	 *
	 * @param message the message to be sent
	 */
	public void performSend(String message){
		if (remoteUser!=null){
			this.ni.sendSend(message, id, remoteUser);
			this.gui.displayMessage(localUser, remoteUser, message);
		}

	}
	
	/**
	 * This method is invoked when a User wants to send a file
	 *
	 * @param name the name of the file to send
	 */
	public void performFileRequest(String name){
		if (remoteUser!=null){
			ni.sendFileRequest(remoteUser, name);
		}
		
	}
	
	/**
	 * This method is invoked when a User respond to a FileRequest
	 *
	 * @param name the name of the file
	 * @param response the response to the request, can be yes or no.
	 */
	public void performResponse(String name,boolean response){
		ni.sendFileResponse(waitingUser,name,response);
		
	}
	
	/**
	 * Perform send file.
	 */
	public void performSendFile(){
		
	}
	
	/**
	 * This method is invoked when a User respond Yes to a FileRequest
	 *
	 *
	 * @param location a File Object that represents the folder to store the File
	 * @param name the name of the file
	 */
	public void performReceivingFile(File location,String name){
		ni.prepareTCPServer(location,name);
	}
	
	
	
	
	
	/**
	 * Gets the local user.
	 *
	 * @return the local user
	 */
	public static User getLocalUser() {
		return localUser;
	}


	
	/**
	 * Gets the fileto send.
	 *
	 * @return the fileto send
	 */
	public File getFiletoSend() {
		return filetoSend;
	}

	/**
	 * Sets the fileto send.
	 *
	 * @param filetoSend the new fileto send
	 */
	public void setFiletoSend(File filetoSend) {
		this.filetoSend = filetoSend;
	}

	//Useful functions
	/**
	 * Prints the list.
	 */
	public void printList(){
		for (User u:userList){
			System.out.println(u);
		}
	}
	
	/**
	 * Adds the new to list.
	 *
	 * @param u the u
	 */
	private void addNewToList(User u){
		int i;
		boolean appartient = false;
		for(i=0; i<userList.size(); i++){
			if (userList.get(i).equals(u)){ 
				appartient=true;
			}
		}
		if (!appartient) userList.add(u); 
	}
	
	/**
	 * Gets the user from his ip adress.
	 *
	 * @param address the address
	 * @return the user 
	 */
	public User getUserFromIp(InetAddress address){
		for (User u:userList){
			if (u.getAddress().equals(address)){
				return u;
			}
		}
		
		
		return null;
	}
	
	
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		
		try {
						
			byte [] bytes1={(byte)192,(byte)2,(byte)10,(byte)1};
			byte [] bytes2={(byte)193,(byte)3,(byte)10,(byte)1};

			User julien = new User("jul", InetAddress.getLocalHost());
			User aurel = new User("aurel", InetAddress.getLocalHost());
			User lolo= new User("lolo",InetAddress.getByAddress(bytes2));
			
			ChatController controller=new ChatController();
			
			controller.addNewToList(aurel);
			controller.addNewToList(julien);
			controller.addNewToList(lolo);
			
			controller.printList();
			
			
			//controller.processGoodbye(lolo);
			
			//controller.printList();
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		
	}








}
