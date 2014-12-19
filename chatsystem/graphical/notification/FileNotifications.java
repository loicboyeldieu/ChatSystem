package chatsystem.graphical.notification;

import java.util.Date;

// TODO: Auto-generated Javadoc
/*This class was made to be a graphical representation of the notifications when sending files 
 * 
 * @author Loïc Boyeldieu
 */

/**
 * The Class FileNotifications.
 */
public class FileNotifications extends NotificationDisplay{

	/** The end transfer. */
	private boolean endTransfer;
	
	/**
	 * Instantiates a new file notifications.
	 *
	 * @param endTransfer the end transfer
	 */
	public FileNotifications(boolean endTransfer){
		super();
		this.endTransfer=endTransfer;
	}
	
	/* (non-Javadoc)
	 * @see chatsystem.graphical.notification.NotificationDisplay#toString()
	 */
	@Override
	public String toString(){
		if (endTransfer){
			return "<html><p style='color:#20cc0a;'>["+date.toString().subSequence(11, 16)+"] File transfer complete</p></html>";
		}
		else{
			return "<html><p style='color:#20cc0a;'>["+date.toString().subSequence(11, 16)+"] File transfer begin </p></html>";
		}
		
	}
	
}
