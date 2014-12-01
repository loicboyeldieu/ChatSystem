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
		//ni.setController(this);
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
	

	public void processHelloAck(User u){
		this.addNewToList(u);
		this.printList();
	}
	
	public void processHello(User u){
		this.addNewToList(u);
		this.ni.sendHelloACK(u, localUser);
	}
	
	public void processGoodbye(User u) {
		for (User us : userList) 
		{
			if (us.compareTo(u)==0){
				userList.remove(us);
				break;
			}
		}
		
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
		
		try {
						
			byte [] bytes1={(byte)192,(byte)2,(byte)10,(byte)1};
			byte [] bytes2={(byte)193,(byte)3,(byte)10,(byte)1};

			User julien = new User("jul", InetAddress.getLocalHost());
			User aurel = new User("aurel", InetAddress.getByAddress(bytes1));
			User lolo= new User("lolo",InetAddress.getByAddress(bytes2));
			
			ChatController controller=new ChatController(null);
			
			controller.addNewToList(aurel);
			controller.addNewToList(julien);
			controller.addNewToList(lolo);
			
			controller.printList();
			
			
			controller.processGoodbye(lolo);
			
			controller.printList();
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		
	}



}
