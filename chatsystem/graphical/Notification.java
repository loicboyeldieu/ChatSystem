package chatsystem.graphical;

import chatsystem.User;

public class Notification {
	private User concernedUser;
	private boolean goodbye;
	
	
	public Notification(User concernedUser, boolean goodbye) {
		super();
		this.concernedUser = concernedUser;
		this.goodbye = goodbye;
	}
	
	public String toString(){
		if (goodbye){
			return concernedUser+" has just left the ChatSystem";
		} else{
			return concernedUser+"has just joined the ChatSystem";
		}
	}
	
	

}
