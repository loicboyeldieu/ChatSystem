package chatsystem.graphical.notification;

import java.util.Date;

/*This class was made to be a graphical representation of the notifications when sending files 
 * 
 * @author Loïc Boyeldieu
 */

public class FileNotifications extends NotificationDisplay{

	private boolean endTransfer;
	
	public FileNotifications(boolean endTransfer){
		super();
		this.endTransfer=endTransfer;
	}
	
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
