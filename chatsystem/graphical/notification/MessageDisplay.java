package chatsystem.graphical.notification;

import chatsystem.User;

// TODO: Auto-generated Javadoc
/*This class was made to be a graphical representation of the messages received/sent 
 * 
 * @author Aur�lien Tamas-Leloup
 */

/**
 * The Class MessageDisplay.
 */
public class MessageDisplay extends NotificationDisplay{
	
	
	/** The from user. */
	private User fromUser;
	
	/** The to user. */
	private User toUser;
	
	/** The content. */
	private String content;
	
	
	/**
	 * Instantiates a new message display.
	 *
	 * @param fromUser the from user
	 * @param toUser the to user
	 * @param content the content
	 */
	public MessageDisplay(User fromUser, User toUser, String content) {
		super();
		this.fromUser = fromUser;
		this.toUser = toUser;
		this.content = content;
	}
	
	
	/* (non-Javadoc)
	 * @see chatsystem.graphical.notification.NotificationDisplay#toString()
	 */
	@Override
	public String toString(){
		return ("<html><p>["+date.toString().subSequence(11, 16)+"] <span style='color:rgb(230, 126, 34)'> "+fromUser+" </span> -> <span style='color:rgb(52, 152, 219)'> "+toUser+" </span> : "+content+"</p></html>");
	}
	
	
	


}
