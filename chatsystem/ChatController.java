package chatsystem;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

import chatsystem.network.ChatNI;

public class ChatController {

	private ChatNI ni;
	
	private ArrayList<User> userList;
	
	private User localUser;
	
	

	public ChatController(ChatNI ni) {
		this.userList = new ArrayList<User>();
		this.ni=ni;
		ni.setController(this);
		try{
			localUser=new User("User", InetAddress.getLocalHost());
			//ni.sendHello(localUser.getName());
		} catch (Exception e){
			e.printStackTrace();
		}
		
	}
	
	public ChatController(ChatNI ni,User u) {
		this.userList = new ArrayList<User>();
		this.ni=ni;
		this.localUser=u;
		//ni.sendHello(localUser.getName());
	}
	

	public void performHelloAck(User u){
		this.addNewToList(u);
		this.printList();
	}
	
	public void performHello(User u){
		this.addNewToList(u);
		this.ni.sendHelloACK(u, localUser);
	}
	
	public void printList(){
		for (User u:userList){
			System.out.println(u);
		}
	}
	
	public void addNewToList(User u){
		int i;
		boolean appartient = false;
		for(i=0; i<userList.size(); i++){
			if (userList.get(i).compareTo(u)==0){ 
				appartient=true;
			}
		}
		if (!appartient) userList.add(u); 
	}
	
	
	
	public static void main(String[] args) {
		ArrayList<User> list= new ArrayList<User>();
		try {
			User Julien = new User("jul", InetAddress.getLocalHost());
			User Aurel = new User("aurel", InetAddress.getLocalHost());
			ChatController cc = new ChatController(null);
			
			System.out.println("Ajout de Julien \n");
			cc.addNewToList(Julien);
			cc.printList();
			System.out.println("Ajout de Aurel \n");
			cc.addNewToList(Aurel);
			cc.printList();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
