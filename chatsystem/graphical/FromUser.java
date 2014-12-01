package chatsystem.graphical;

import chatsystem.User;

public interface FromUser {
	
	
	public void selectUser();
	public void sendMessage(String message);
	public void disconnect();
	public void connect();

}
