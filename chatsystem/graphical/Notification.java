package chatsystem.graphical;

import java.util.Date;

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
		Date date = new Date();
		if (goodbye){
			return "["+date+"]"+concernedUser+" has just left the ChatSystem";
		} else{
			return "["+date+"]"+concernedUser+" has just joined the ChatSystem";
		}
	}
	
	

}
