package chatsystem.graphical;

import chatsystem.User;

/*This class was made to be a graphical representation of the messages received/sent 
 * 
 * @author Aur�lien Tamas-Leloup
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
		return ("<html><p>FROM <span style='color:rgb(230, 126, 34)'> "+fromUser+" </span> TO <span style='color:rgb(52, 152, 219)'> "+toUser+" </span> : "+content+"</p></html>");
	}
	
	
	


}
