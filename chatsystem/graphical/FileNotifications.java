package chatsystem.graphical;

/*This class was made to be a graphical representation of the notifications when sending files 
 * 
 * @author Loïc Boyeldieu
 */

public class FileNotifications {

	private boolean endTransfer;
	
	public FileNotifications(boolean endTransfer){
		this.endTransfer=endTransfer;
	}
	
	@Override
	public String toString(){
		if (endTransfer){
			return "<html><p style='color:#20cc0a;'> File transfer complete</p></html>";
		}
		else{
			return "<html><p style='color:#20cc0a;'> File transfer begin </p></html>";
		}
		
	}
	
}
