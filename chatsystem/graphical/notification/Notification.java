package chatsystem.graphical.notification;

import java.util.Date;

import chatsystem.User;

public class Notification extends NotificationDisplay{
	private User concernedUser;
	private boolean goodbye;
	
	
	
	public Notification(User concernedUser, boolean goodbye) {
		super();
		this.concernedUser = concernedUser;
		this.goodbye = goodbye;
	}
	
	@Override
	public String toString(){
		
		if (goodbye){
			return "<html><p style='color:#e32232;'>["+date.toString().subSequence(11, 16)+"] "+concernedUser+" has just left the ChatSystem</p></html>";
		} else{
			return "<html><p style='color:#20cc0a;'>["+date.toString().subSequence(11, 16)+"] "+concernedUser+" has just joined the ChatSystem</p></html>";
		}
	}
	
	

}