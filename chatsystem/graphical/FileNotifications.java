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
			return "File transfer complete";
		}
		else{
			return "File transfer begin";
		}
		
	}
	
}
