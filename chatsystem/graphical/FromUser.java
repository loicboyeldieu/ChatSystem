package chatsystem.graphical;

import java.io.File;

public interface FromUser {
	
	
	public void selectUser();
	public void sendMessage(String message);
	public void disconnect();
	public void connect();
	public void sendFile(String name);
	public void acceptFile(String name);
	public void declineFile(String name);
	public void selectFile(File file);

}
