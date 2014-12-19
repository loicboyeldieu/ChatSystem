package chatsystem.graphical.notification;

import java.util.Date;

import chatsystem.User;

// TODO: Auto-generated Javadoc
/**
 * The Class Notification.
 */
public class Notification extends NotificationDisplay{
	
	/** The concerned user. */
	private User concernedUser;
	
	/** The goodbye. */
	private boolean goodbye;
	
	
	
	/**
	 * Instantiates a new notification.
	 *
	 * @param concernedUser the concerned user
	 * @param goodbye the goodbye
	 */
	public Notification(User concernedUser, boolean goodbye) {
		super();
		this.concernedUser = concernedUser;
		this.goodbye = goodbye;
	}
	
	/* (non-Javadoc)
	 * @see chatsystem.graphical.notification.NotificationDisplay#toString()
	 */
	@Override
	public String toString(){
		
		if (goodbye){
			return "<html><p style='color:#e32232;'>["+date.toString().subSequence(11, 16)+"] "+concernedUser+" has just left the ChatSystem</p></html>";
		} else{
			return "<html><p style='color:#20cc0a;'>["+date.toString().subSequence(11, 16)+"] "+concernedUser+" has just joined the ChatSystem</p></html>";
		}
	}
	
	

}
