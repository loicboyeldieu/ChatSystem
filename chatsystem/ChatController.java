package chatsystem;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

import chatsystem.graphical.ChatGUI;
import chatsystem.network.ChatNI;

// TODO: Auto-generated Javadoc
/**
 * The Class ChatController.
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
	 * Instantiates a new chat controller.
	 */
	public ChatController(){
		this.userList = new ArrayList<User>();
	}
	

	

	//from NI
	/**
	 * Process hello ack.
	 *
	 * @param u the u
	 */
	public void processHelloAck(User u){
		gui.addToConnectedUserList(u);
		this.addNewToList(u);
	}
	
	/**
	 * Process hello.
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
	 * Process goodbye.
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
	 * Process send.
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
	 * Process file request.
	 *
	 * @param u the u
	 * @param name the name
	 */
	public void processFileRequest(User u,String name){
		waitingUser=u;
		this.gui.displayFileRequest(u,name);
	}
	
	/**
	 * Process file accepted.
	 *
	 * @param u the u
	 */
	public void processFileAccepted(User u){
		ni.sendFile(filetoSend,u);
		this.gui.displayStatusFile(true, u);
		this.gui.displayFileNotificationTransfer(false);
	}
	
	/**
	 * Process file refused.
	 *
	 * @param u the u
	 */
	public void processFileRefused(User u){
		this.gui.displayStatusFile(false, u);
	}
	
	/**
	 * Process file completed.
	 */
	public void processFileCompleted(){
		gui.displayFileNotificationTransfer(true);
	}
	
	
	//from GUI
	
	


	/**
	 * Process connect.
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
	 * Process disconnect.
	 */
	public void processDisconnect() {
		this.ni.sendGoodbye(ChatController.localUser.getName());
		this.ni.stopReception();
	}
	
	
	/**
	 * On selected user.
	 *
	 * @param u the u
	 */
	public void onSelectedUser(User u){
		this.remoteUser=u;
		System.out.println("Destinataire : "+u);
	}
	
	
	/**
	 * Perform send.
	 *
	 * @param message the message
	 */
	public void performSend(String message){
		if (remoteUser!=null){
			this.ni.sendSend(message, id, remoteUser);
			this.gui.displayMessage(localUser, remoteUser, message);
		}

	}
	
	/**
	 * Perform file request.
	 *
	 * @param name the name
	 */
	public void performFileRequest(String name){
		if (remoteUser!=null){
			ni.sendFileRequest(remoteUser, name);
		}
		
	}
	
	/**
	 * Perform response.
	 *
	 * @param name the name
	 * @param response the response
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
	 * Perform receiving file.
	 *
	 * @param location the location
	 * @param name the name
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
	 * Gets the user from ip.
	 *
	 * @param address the address
	 * @return the user from ip
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
