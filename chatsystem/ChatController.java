package chatsystem;

import java.net.InetAddress;
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
			ni.sendHello(localUser.getName());
		} catch (Exception e){
			e.printStackTrace();
		}
		
	}
	
	public ChatController(ChatNI ni,User u) {
		this.userList = new ArrayList<User>();
		this.ni=ni;
		this.localUser=u;
		ni.sendHello(localUser.getName());
	}
	

	public void performHelloAck(User u){
		this.userList.add(u);
		this.printList();
	}
	
	public void performHello(User u){
		this.userList.add(u);
		this.ni.sendHelloACK(u, localUser);
	}
	
	public void printList(){
		for (User u:userList){
			System.out.println(u);
		}
	}
	
	
	
	public static void main(String[] args) {
		
	}

}
