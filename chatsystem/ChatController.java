package chatsystem;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

import chatsystem.graphical.ChatGUI;
import chatsystem.network.ChatNI;

public class ChatController {

	private ChatNI ni;
	private ChatGUI gui;
	
	private ArrayList<User> userList;
	
	private static User localUser;
	
	private User remoteUser;
	
	private User waitingUser;
	private File filetoSend;
	
	private static int id;
	
	

	//Constructors
	public ChatController(ChatNI ni,ChatGUI gui) {
		this.userList = new ArrayList<User>();
		this.ni=ni;
		this.gui=gui;
		ni.setController(this);
		gui.setController(this);

	}
	
	public ChatController(){
		this.userList = new ArrayList<User>();
	}
	

	

	//from NI
	public void processHelloAck(User u){
		gui.addToConnectedUserList(u);
		this.addNewToList(u);
	}
	
	public void processHello(User u){
		if (!u.equals(localUser))
		{
			gui.addToConnectedUserList(u);
			this.addNewToList(u);
			this.ni.sendHelloACK(u, localUser);
			gui.displayNotification(u, false);
		}
	
	}
	
	public void processGoodbye(User u) {
		if (!u.equals(localUser)){
			this.gui.removeUser(u);
			this.userList.remove(u);
			this.gui.displayNotification(u, true);
		}
	
		
	}
	
	public void processSend(User u,int id, String message){
		
			this.gui.displayMessage(u,localUser,message);
			this.ni.sendSendAck(id, u);

		
	}
	
	public void processFileRequest(User u,String name){
		waitingUser=u;
		this.gui.displayFileRequest(u,name);
	}
	
	public void processFileAccepted(User u){
		ni.sendFile(filetoSend,u);
	}
	
	
	//from GUI
	
	


	public void processConnect(String nickname){
		
		try {
			this.localUser=new User(nickname, InetAddress.getLocalHost());
		} catch (UnknownHostException e) {
			this.localUser=new User(nickname, null);
		} finally {
			System.out.println("Bienvenue  "+localUser);
		}
		
		
		this.ni.sendHello(nickname);
		this.ni.startReception();
	}
	
	public void processDisconnect() {
		this.ni.sendGoodbye(this.localUser.getName());
		this.ni.stopReception();
	}
	
	
	public void onSelectedUser(User u){
		this.remoteUser=u;
		System.out.println("Destinataire : "+u);
	}
	
	
	public void performSend(String message){
		if (remoteUser!=null){
			this.ni.sendSend(message, id, remoteUser);
			this.gui.displayMessage(localUser, remoteUser, message);
		}

	}
	
	public void performFileRequest(String name){
		if (remoteUser!=null){
			ni.sendFileRequest(remoteUser, name);
		}
		
	}
	
	public void performResponse(String name,boolean response){
		ni.sendFileResponse(waitingUser,name,response);
	}
	
	public void performSendFile(){
		
	}
	
	public void performReceivingFile(File location,String name){
		ni.prepareTCPServer(location,name);
	}
	
	
	
	
	
	public static User getLocalUser() {
		return localUser;
	}


	
	public File getFiletoSend() {
		return filetoSend;
	}

	public void setFiletoSend(File filetoSend) {
		this.filetoSend = filetoSend;
	}

	//Useful functions
	public void printList(){
		for (User u:userList){
			System.out.println(u);
		}
	}
	
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
	
	public User getUserFromIp(InetAddress address){
		for (User u:userList){
			if (u.getAddress().equals(address)){
				return u;
			}
		}
		
		
		return null;
	}
	
	
	
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
