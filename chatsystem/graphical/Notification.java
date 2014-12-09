package chatsystem.graphical;

import java.util.Date;

import chatsystem.User;

public class Notification {
	private User concernedUser;
	private boolean goodbye;
	private Date date;
	
	
	public Notification(User concernedUser, boolean goodbye) {
		super();
		this.concernedUser = concernedUser;
		this.goodbye = goodbye;
		this.date = new Date();
	}
	
	public String toString(){
		
		if (goodbye){
			return "<html><p style='color:#e32232;'>["+this.date+"] "+concernedUser+" has just left the ChatSystem</p></html>";
		} else{
			return "<html><p style='color:#20cc0a;'>["+this.date+"] "+concernedUser+" has just joined the ChatSystem</p></html>";
		}
	}
	
	

}
