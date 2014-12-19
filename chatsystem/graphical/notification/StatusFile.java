package chatsystem.graphical.notification;

import chatsystem.User;

// TODO: Auto-generated Javadoc
/**
 * The Class StatusFile.
 */
public class StatusFile extends NotificationDisplay{
	
	/** The status. */
	private boolean status;
	
	/** The concerned user. */
	private User concernedUser;
	
	/**
	 * Instantiates a new status file.
	 *
	 * @param status the status
	 * @param u the u
	 */
	public StatusFile(boolean status, User u){
		super();
		this.status = status;
		this.concernedUser = u;
	}
	
	/* (non-Javadoc)
	 * @see chatsystem.graphical.notification.NotificationDisplay#toString()
	 */
	@Override
	public String toString(){
		// status=true -> acceptation 
		if (status){
			return "<html><p style='color:#20cc0a'>["+date.toString().subSequence(11, 16)+"] " + concernedUser + " accepted to receive your file</p></html>";
		}
		else{
			return "<html><p style='color:#e32232;'>["+date.toString().subSequence(11, 16)+"] " + concernedUser + " refused to receive your file</p></html>";
		}
	}
	
	
}
