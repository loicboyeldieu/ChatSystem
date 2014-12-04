package chatsystem.graphical;

import chatsystem.User;

/*This class was made to be a graphical representation of the messages received/sent 
 * 
 * @author Aurélien Tamas-Leloup
 */

public class MessageDisplay {
	
	private User fromUser;
	private User toUser;
	private String content;
	
	
	public MessageDisplay(User fromUser, User toUser, String content) {
		this.fromUser = fromUser;
		this.toUser = toUser;
		this.content = content;
	}
	
	
	public String toString(){
		return (fromUser+"->"+toUser+" : "+content);
	}
	
	
	


}
